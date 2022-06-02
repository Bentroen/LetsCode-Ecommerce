package br.com.letscode.vendaapp.service;

import br.com.letscode.vendaapp.domain.Carrinho;
import br.com.letscode.vendaapp.domain.CarrinhoProdutoRequest;
import br.com.letscode.vendaapp.gateway.ProdutoGateway;
import br.com.letscode.vendaapp.gateway.UsuarioGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CarrinhoService {

    private final UsuarioGateway usuarioGateway;
    private final ProdutoGateway produtoGateway;

    public CarrinhoService(UsuarioGateway usuarioGateway, ProdutoGateway produtoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.produtoGateway = produtoGateway;
    }

    public Mono<Carrinho> verificarUsuario(Carrinho c) {
        return usuarioGateway.getUsuario(c.getUsuarioId()).map(t -> c);
    }

    public Mono<CarrinhoProdutoRequest> verificarProduto(CarrinhoProdutoRequest cpr) {
        return Mono.just(cpr).flatMap(request -> produtoGateway.getProduto(cpr.getIdProduto())
        ).map(t -> cpr);
    }

}
