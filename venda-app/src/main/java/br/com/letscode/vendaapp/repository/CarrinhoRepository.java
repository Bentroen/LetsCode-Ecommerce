package br.com.letscode.vendaapp.repository;

import br.com.letscode.vendaapp.domain.Carrinho;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CarrinhoRepository extends ReactiveMongoRepository<Carrinho, String> {
}
