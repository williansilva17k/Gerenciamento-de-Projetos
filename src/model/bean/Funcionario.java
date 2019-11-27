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
public class Funcionario extends Pessoa {

    private Integer id_func;
    private String nome_login;
    private Cargo cargo;

    public Funcionario() {
        super();
    }

    public Funcionario(Integer id_func, String nome_login, Cargo cargo, String nome_func, String cpf) {
        super(nome_func, cpf);
        this.id_func = id_func;
        this.nome_login = nome_login;
        this.cargo = cargo;
    }

    public Integer getId_func() {
        return id_func;
    }

    public void setId_func(Integer id_func) {
        this.id_func = id_func;
    }

    public String getNome_login() {
        return nome_login;
    }

    public void setNome_login(String nome_login) {
        this.nome_login = nome_login;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}
