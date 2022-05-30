package br.com.letscode.produtoapp;

import br.com.letscode.produtoapp.controller.ProdutoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = ProdutoController.class)
public class EcommerceProdutoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceProdutoAppApplication.class, args);
    }

}
