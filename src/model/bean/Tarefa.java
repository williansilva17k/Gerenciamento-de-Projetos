/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author grs
 */
public class Tarefa {

    private Integer id_tarefa;
    private String nome_tarefa;
    private String nome_projeto;
    private String data_ini;
    private String data_fin;
    private String nome_func;
    private String nome_status;
    private String descricao;

    public Tarefa() {
    }

    public Tarefa(Integer id_tarefa, String nome_tarefa, String nome_projeto, String data_ini, String data_fin, String nome_func, String nome_status, String descricao) {
        this.id_tarefa = id_tarefa;
        this.nome_tarefa = nome_tarefa;
        this.nome_projeto = nome_projeto;
        this.data_ini = data_ini;
        this.data_fin = data_fin;
        this.nome_func = nome_func;
        this.nome_status = nome_status;
        this.descricao = descricao;
    }

    public Integer getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(Integer id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getNome_tarefa() {
        return nome_tarefa;
    }

    public void setNome_tarefa(String nome_tarefa) {
        this.nome_tarefa = nome_tarefa;
    }

    public String getNome_projeto() {
        return nome_projeto;
    }

    public void setNome_projeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public String getData_ini() {
        return data_ini;
    }

    public void setData_ini(String data_ini) {
        this.data_ini = data_ini;
    }

    public String getData_fin() {
        return data_fin;
    }

    public void setData_fin(String data_fin) {
        this.data_fin = data_fin;
    }

    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public String getNome_status() {
        return nome_status;
    }

    public void setNome_status(String nome_status) {
        this.nome_status = nome_status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
