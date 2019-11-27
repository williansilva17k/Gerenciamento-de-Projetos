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
public class Cargo {

    private Integer id_cargo;
    private String nome_cargo;

    public Cargo(){
    }
    
    public Cargo(Integer id_cargo, String nome_cargo) {
        this.id_cargo = id_cargo;
        this.nome_cargo = nome_cargo;
    }

    public Integer getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Integer id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNome_cargo() {
        return nome_cargo;
    }

    public void setNome_cargo(String nome_cargo) {
        this.nome_cargo = nome_cargo;
    }

    @Override
    public String toString() {
        return getNome_cargo();
    }

    
}
