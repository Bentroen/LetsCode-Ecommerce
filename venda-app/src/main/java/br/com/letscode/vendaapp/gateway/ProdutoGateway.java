package br.com.letscode.vendaapp.gateway;

import feign.FeignException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProdutoGateway {

    private final ProdutoReactiveFeignClient produtoReactiveFeignClient;

    public ProdutoGateway(ProdutoReactiveFeignClient produtoReactiveFeignClient) {
        this.produtoReactiveFeignClient = produtoReactiveFeignClient;
    }

    public Mono<String> getProduto(Long produtoId) {
        return produtoReactiveFeignClient.getProduto(produtoId)
                .onErrorResume(FeignException.NotFound.class, erro ->
                        Mono.empty()
                );
    }
}
