package br.com.letscode.produtoapp.controller;

import br.com.letscode.produtoapp.domain.Produto;
import br.com.letscode.produtoapp.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(path = "/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewProduto(@RequestParam String nome, @RequestParam BigDecimal valor) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setValor(valor);
        produtoRepository.save(produto);
        return "Produto salvo";
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable String id) {
        Optional<Produto> produto = produtoRepository.findById(Integer.valueOf(id));

        if (produto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto.get());
    }

}
