/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.controlador;

import edu.webapp1966781b.entity.Usuario;
import edu.webapp1966781b.facade.UsuarioFacadeLocal;
import edu.webapp1966781b.utilidades.Email;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Usuario
 */
@Named(value = "registroRequest")
@RequestScoped
public class RegistroRequest implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private Usuario usuReg = new Usuario();
    private String correoIn = "";

    /**
     * Creates a new instance of RegistroRequest
     */
    public RegistroRequest() {

    }

    public void registrarUsuario() {
        String mensajeRequest = "";
        try {
            usuReg.setFechaRegistro(new Date());
            usuarioFacadeLocal.create(usuReg);
            mensajeRequest = "swal('Registro', 'Exitoso !!!!', 'success');";
        } catch (Exception e) {
            System.out.println("Error RegistroRequest:registrarUsuario " +e.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        usuReg = new Usuario();
    }
    
    public void recuperarClave(){
      String mensajeRequest = "";
      Usuario usuRec = new Usuario();
        try {
            usuRec =  usuarioFacadeLocal.recuperarContrasenia(correoIn);
            int claveNew = (int) (Math.random()*100000);
            usuRec.setClave("RC-"+claveNew);
            usuarioFacadeLocal.edit(usuRec);
            mensajeRequest = "swal('Su clave', 'Se envio al correo registrado !!!!', 'success');";
            Email.sendClaves(usuRec.getCorreo(),
                              usuRec.getNombres() + " " +usuRec.getApellidos(),
                              correoIn, "RC-"+claveNew);
        } catch (Exception e) {
            System.out.println("Error RegistroRequest:recuperarClave " +e.getMessage());
            mensajeRequest = "swal('Correo NO', 'registrado', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        correoIn ="";
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

}
