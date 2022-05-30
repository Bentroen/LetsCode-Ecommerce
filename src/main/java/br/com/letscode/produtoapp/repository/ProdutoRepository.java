package br.com.letscode.produtoapp.repository;

import br.com.letscode.produtoapp.domain.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
}
