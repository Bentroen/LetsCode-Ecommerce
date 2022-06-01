package br.com.letscode.vendaapp.gateway;

import feign.FeignException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UsuarioGateway {

    private final UsuarioReactiveFeignClient usuarioReactiveFeignClient;

    public UsuarioGateway(UsuarioReactiveFeignClient usuarioReactiveFeignClient) {
        this.usuarioReactiveFeignClient = usuarioReactiveFeignClient;
    }

    public Mono<String> getUsuario(Long usuarioId) {
        return usuarioReactiveFeignClient.getUsuario(usuarioId)
                .onErrorResume(FeignException.NotFound.class, erro ->
                        Mono.empty()
                );
    }
}