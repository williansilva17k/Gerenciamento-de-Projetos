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
public class Login {

    private Integer id_login;
    private String nome_user;
    private String senha_user;
    private Integer permission;

    public Login() {
    }

    public Login(Integer id_login, String nome_user, String senha_user, Integer permission) {
        this.id_login = id_login;
        this.nome_user = nome_user;
        this.senha_user = senha_user;
        this.permission = permission;
    }

    public Integer getId_login() {
        return id_login;
    }

    public void setId_login(Integer id_login) {
        this.id_login = id_login;
    }

    public String getNome_user() {
        return nome_user;
    }

    public void setNome_user(String nome_user) {
        this.nome_user = nome_user;
    }

    public String getSenha_user() {
        return senha_user;
    }

    public void setSenha_user(String senha_user) {
        this.senha_user = senha_user;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

}
