package br.com.letscode.vendaapp.config;

import br.com.letscode.vendaapp.handler.CarrinhoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(CarrinhoHandler carrinhoHandler) {
        return RouterFunctions
                .route(POST("/carrinho/add").and(contentType(APPLICATION_JSON)), carrinhoHandler::criarCarrinho)
                .andRoute(POST("/carrinho/addProduto").and(contentType(APPLICATION_JSON)), carrinhoHandler::adicionarProdutoAoCarrinho)
                .andRoute(GET("/carrinho/{carrinhoId}").and(contentType(APPLICATION_JSON)), carrinhoHandler::buscarCarrinho);
    }
}
