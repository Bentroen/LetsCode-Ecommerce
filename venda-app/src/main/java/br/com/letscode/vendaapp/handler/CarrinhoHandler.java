package br.com.letscode.vendaapp.handler;

import br.com.letscode.vendaapp.domain.Carrinho;
import br.com.letscode.vendaapp.domain.CarrinhoProdutoRequest;
import br.com.letscode.vendaapp.repository.CarrinhoRepository;
import br.com.letscode.vendaapp.service.CarrinhoService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class CarrinhoHandler {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoService carrinhoService;

    public CarrinhoHandler(CarrinhoRepository carrinhoRepository, CarrinhoService carrinhoService) {
        this.carrinhoRepository = carrinhoRepository;
        this.carrinhoService = carrinhoService;
    }

    public Mono<ServerResponse> criarCarrinho(ServerRequest request) {
        return request.bodyToMono(Carrinho.class)
                .flatMap(carrinhoService::verificarUsuario)
                .flatMap(carrinhoRepository::save)
                .flatMap(carrinho -> ServerResponse
                        .created(URI.create(String.format("/carrinho/%s", carrinho.getId())))
                        .bodyValue(carrinho)
                )
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Usuário inválido; por favor verifique!"));

    }

    @RequestMapping()
    public Mono<ServerResponse> adicionarProdutoAoCarrinho(ServerRequest request) {
        return request.bodyToMono(CarrinhoProdutoRequest.class)
                .flatMap(carrinhoService::verificarProduto) // Verificar se o produto é válido
                .flatMap(carrinhoProdutoRequest -> {
                    return this.carrinhoRepository.findById(carrinhoProdutoRequest.getIdCarrinho())
                            .flatMap(carrinho -> {
                                carrinho.addProduto(carrinhoProdutoRequest.getIdProduto()); // Adicionar produto ao carrinho
                                return Mono.just(carrinho);
                            })
                            .flatMap(carrinhoRepository::save) // Salvar carrinho atualizado no repositório
                            .flatMap(carrinho -> ServerResponse
                                    .created(URI.create(String.format("/carrinho/%s", carrinho.getId())))
                                    .bodyValue(carrinho)
                            )
                            .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Carrinho inválido; por favor verifique!"));
                })
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Produto inválido; por favor verifique!"));
    }

    public Mono<ServerResponse> buscarCarrinho(ServerRequest request) {
        String idCarrinho = request.pathVariable("idCarrinho");
        return ServerResponse.ok()
                .body(BodyInserters.fromValue(carrinhoRepository.findById(idCarrinho)))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Carrinho inválido; por favor verifique!"));
    }

}
