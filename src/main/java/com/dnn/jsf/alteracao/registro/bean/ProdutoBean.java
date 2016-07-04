/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnn.jsf.alteracao.registro.bean;

import com.dnn.jsf.alteracao.registro.HibernateUtil;
import com.dnn.jsf.alteracao.registro.model.Log;
import com.dnn.jsf.alteracao.registro.model.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author deivid
 */
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

    private Produto p;
    private List<Produto> ps;
     private List<Log> ls;

    @PostConstruct
    public void init() {
        System.out.println(HibernateUtil.getSessionFactory().getCurrentSession());
        p = new Produto();
        listar();
    }

    public void novo() {
        p = new Produto();
        p.setDescricao("");
        RequestContext.getCurrentInstance().reset("form");
    }

    public void salvar() {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = s.beginTransaction();
            s.saveOrUpdate(p);
            s.save(new Log(p.getId(), "produto", p.toString()));
            tx.commit();
            listar();
            ls = new ArrayList<>();
            p = new Produto();
            p.setDescricao("");
            RequestContext.getCurrentInstance().reset("form");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e);
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void listar() {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = s.beginTransaction();
            ps = s.createQuery("select vo from Produto vo order by vo.id desc").list();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
    
    public void listarlog(Produto u) {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = s.beginTransaction();
            ls = s.createQuery("select vo from Log vo"
                    + " where vo.tabela='produto'"
                    + " and vo.id_tabela="+u.getId()+""
                    + " order by vo.id desc").list();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void setar(Produto o) {
        p = o;
        ls = new ArrayList<>();
        RequestContext.getCurrentInstance().reset("form");
    }
    


    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public List<Produto> getPs() {
        return ps;
    }

    public void setPs(List<Produto> ps) {
        this.ps = ps;
    }

    public List<Log> getLs() {
        return ls;
    }

    public void setLs(List<Log> ls) {
        this.ls = ls;
    }
    
    

}
