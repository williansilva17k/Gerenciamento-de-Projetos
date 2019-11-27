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
public class Status {

    private Integer id_status;
    private String nome_status;

    public Status(Integer id_status, String nome_status) {
        this.id_status = id_status;
        this.nome_status = nome_status;
    }

    public Status() {
    }

    public Integer getId_status() {
        return id_status;
    }

    public void setId_status(Integer id_status) {
        this.id_status = id_status;
    }

    public String getNome_status() {
        return nome_status;
    }

    public void setNome_status(String nome_status) {
        this.nome_status = nome_status;
    }

}
