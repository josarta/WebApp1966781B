/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.facade;

import edu.webapp1966781b.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "WebApp1966781BPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario recuperarContrasenia(String correoIn) {
        try {
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correoIn ");
            qt.setParameter("correoIn", correoIn);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            return new Usuario();
        }

    }

    @Override
    public Usuario loginUsuario(String correoIn, String claveIn) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correoIn AND u.clave = :claveIn");
            q.setParameter("correoIn", correoIn);
            q.setParameter("claveIn", claveIn);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return new Usuario();
        }
    }

    @Override
    public boolean removerUsuario(int id) {
        boolean retorno = false;
        try {
            Query qt = em.createQuery("DELETE FROM Usuario u WHERE u.id = :id");
            qt.setParameter("id", id);
            int salida = qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return retorno;
        }
    }
}
