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
public class Pessoa {

    private String nome_func;
    private String cpf;

    public Pessoa() {
    }

    public Pessoa(String nome_func, String cpf) {
        this.nome_func = nome_func;
        this.cpf = cpf;
    }

    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
