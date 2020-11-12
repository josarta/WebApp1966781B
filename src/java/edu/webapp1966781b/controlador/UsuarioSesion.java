/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.controlador;

import edu.webapp1966781b.entity.Usuario;
import edu.webapp1966781b.facade.UsuarioFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Usuario
 */
@Named(value = "usuarioSesion")
@SessionScoped
public class UsuarioSesion implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private String correoIn;
    private String clave;
    private Usuario usuLogin = new Usuario();

    /**
     * Creates a new instance of UsuarioSesion
     */
    public UsuarioSesion() {
    }

    public void inicioSession() {
        String mensajeAlerta = "";
        try {
            usuLogin = usuarioFacadeLocal.loginUsuario(correoIn, clave);
            if (usuLogin.getId() == null) {
                mensajeAlerta = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.getExternalContext().redirect("usuarios/index.xhtml?faces-redirect=true");
            }

        } catch (Exception e) {
            System.out.println("Error UsuarioSesion:inicioSession " + e.getMessage());
            mensajeAlerta = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeAlerta);
    }

    public void cerraSesion() {
        usuLogin = null;
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ext.getRequestContextPath();

        try {
            ((HttpSession) ext.getSession(false)).invalidate();
            ext.redirect(ctxPath + "/index.xhtml");
        } catch (IOException e) {
            System.out.println("Error UsuarioSesion:cerraSesion " + e.getMessage());
        }

    }
    
    public void actualizarMisDatos(){
         String mensajeAlerta = "";
        try {
           usuarioFacadeLocal.edit(usuLogin);
             mensajeAlerta = "swal('Datos actualizados', 'Exitosamente !!!', 'success');";
        } catch (Exception e) {
             mensajeAlerta = "swal('Problemas actualizando', 'sus datos', 'error');";
        }
          PrimeFaces.current().executeScript(mensajeAlerta);
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(Usuario usuLogin) {
        this.usuLogin = usuLogin;
    }

}
