/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author grs
 */
public class Projeto {
    private Integer id_projeto;
    private String nome_projeto;
    private String status;
    private String descricao;

    public Projeto() {
    }
    
    public Projeto(Integer id_projeto, String nome_projeto, String status, String descricao) {
        this.id_projeto = id_projeto;
        this.nome_projeto = nome_projeto;
        this.status = status;
        this.descricao = descricao;
    }

    public Integer getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(Integer id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getNome_projeto() {
        return nome_projeto;
    }

    public void setNome_projeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
