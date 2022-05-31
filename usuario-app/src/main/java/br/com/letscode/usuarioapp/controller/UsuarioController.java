package br.com.letscode.usuarioapp.controller;

import br.com.letscode.usuarioapp.domain.Usuario;
import br.com.letscode.usuarioapp.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewAluno(@RequestParam String nome, @RequestParam String email) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuarioRepository.save(usuario);
        return "O usuário " + usuario.getNome() + " foi salvo!";
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioRepository.findById(Integer.valueOf(id));

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario.get());
    }
}
