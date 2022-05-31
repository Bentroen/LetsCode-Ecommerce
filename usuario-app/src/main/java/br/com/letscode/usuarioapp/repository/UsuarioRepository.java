package br.com.letscode.usuarioapp.repository;

import br.com.letscode.usuarioapp.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
