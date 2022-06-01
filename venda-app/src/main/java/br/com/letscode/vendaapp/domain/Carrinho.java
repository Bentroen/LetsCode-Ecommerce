package br.com.letscode.vendaapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document("carrinho")
public class Carrinho {

    @Id
    private String id;
    private Long usuarioId;
    private ArrayList<Long> listaDeProdutos = new ArrayList<Long>();
    private Date data = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ArrayList<Long> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(ArrayList produtos) {
        this.listaDeProdutos = produtos;
    }

    public void addProduto(Long produto) {
        this.listaDeProdutos.add(produto);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
