/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnn.jsf.alteracao.registro.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author deivid
 */
@Entity
public class Log implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_tabela;
    @Column(length = 50)
    private String tabela;
    @Column(length = 10000)
    private String dados;

    public Log() {
    }

    public Log(int id_tabela, String tabela, String dados) {
        this.id_tabela = id_tabela;
        this.tabela = tabela;
        this.dados = dados;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tabela() {
        return id_tabela;
    }

    public void setId_tabela(int id_tabela) {
        this.id_tabela = id_tabela;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
