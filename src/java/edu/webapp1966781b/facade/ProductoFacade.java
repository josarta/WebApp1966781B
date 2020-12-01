/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.facade;

import edu.webapp1966781b.entity.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

    @PersistenceContext(unitName = "WebApp1966781BPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    @Override
    public List<Producto> listaProdutosporcategoria(int fk_categoria){
        try {
            Query qt = em.createQuery("SELECT p FROM Producto p WHERE p.fkCategoria.idcategorias = :fk_categoria");
            qt.setParameter("fk_categoria", fk_categoria);
            return   qt.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
}
