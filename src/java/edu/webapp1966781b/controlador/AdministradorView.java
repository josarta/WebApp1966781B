/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.controlador;

import edu.webapp1966781b.entity.Usuario;
import edu.webapp1966781b.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Usuario
 */
@Named(value = "administradorView")
@ViewScoped
public class AdministradorView implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    @PostConstruct
    public void cargaUsuarios() {
        listaUsuarios.addAll(usuarioFacadeLocal.findAll());
    }

    public void removerUsuario(Usuario usuRemov) {
        String mensajeAlerta = "";
        try {
            usuarioFacadeLocal.remove(usuRemov);
            listaUsuarios.remove(usuRemov);
            mensajeAlerta = "swal('Removido el usuario', '" + usuRemov.getNombres() + ' ' + usuRemov.getApellidos() + "', 'success');";
        } catch (Exception e) {
            mensajeAlerta = "swal('Problemas eliminando a ', '" + usuRemov.getNombres() + ' ' + usuRemov.getApellidos() + "', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeAlerta);

    }

    /**
     * Creates a new instance of AdministradorView
     */
    public AdministradorView() {
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

}
