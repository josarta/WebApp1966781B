/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.controlador;

import edu.webapp1966781b.entity.Usuario;
import edu.webapp1966781b.facade.UsuarioFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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
    private Usuario usuarioSelect = new Usuario();
    private Usuario usuReg = new Usuario();
    
    @Inject
    UsuarioSesion usuarioSesion;
    

    @PostConstruct
    public void cargaUsuarios() {
        listaUsuarios.addAll(usuarioFacadeLocal.findAll());
    }

//    public void removerUsuario(Usuario usuRemov) {
//        String mensajeAlerta = "";
//        try {
//            usuarioFacadeLocal.remove(usuRemov);
//            listaUsuarios.remove(usuRemov);
//            mensajeAlerta = "swal('Removido el usuario', '" + usuRemov.getNombres() + ' ' + usuRemov.getApellidos() + "', 'success');";
//        } catch (Exception e) {
//            mensajeAlerta = "swal('Problemas eliminando a ', '" + usuRemov.getNombres() + ' ' + usuRemov.getApellidos() + "', 'error');";
//        }
//        PrimeFaces.current().executeScript(mensajeAlerta);
//
//    }
    public void removerUsuario(Usuario usuRemov) {
        String mensajeAlerta = "";
        try {
            usuarioFacadeLocal.removerUsuario(usuRemov.getId());
            listaUsuarios.remove(usuRemov);
            mensajeAlerta = "swal('Removido el usuario', '" + usuRemov.getNombres() + ' ' + usuRemov.getApellidos() + "', 'success');";
        } catch (Exception e) {
            mensajeAlerta = "swal('Problemas eliminando a ', '" + usuRemov.getNombres() + ' ' + usuRemov.getApellidos() + "', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeAlerta);

    }

    public void usuarioSelecionado(Usuario usuSelect) {
        usuarioSelect = usuSelect;
    }

    public void acutalizarUsuario() {
        String mensajeAlerta = "";
        try {
            usuarioFacadeLocal.edit(usuarioSelect);
            listaUsuarios.clear();
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());

            mensajeAlerta = "swal('Actualizado el usuario', '" + usuarioSelect.getNombres() + ' ' + usuarioSelect.getApellidos() + "', 'success');";
        } catch (Exception e) {
            mensajeAlerta = "swal('Problemas Actualizando a ', '" + usuarioSelect.getNombres() + ' ' + usuarioSelect.getApellidos() + "', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeAlerta);

    }

    public void registrarUsuario() {
        String mensajeRequest = "";
        try {
            usuReg.setFechaRegistro(new Date());
            usuarioFacadeLocal.create(usuReg);
            listaUsuarios.clear();
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
            mensajeRequest = "swal('Registro', 'Exitoso !!!!', 'success');";
        } catch (Exception e) {
            System.out.println("Error RegistroRequest:registrarUsuario " + e.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        usuReg = new Usuario();
    }

    public void descargaListado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("UsuarioReporte", usuarioSesion.getUsuLogin().getNombres() + " " +usuarioSesion.getUsuLogin().getApellidos());
            parametro.put("RutaImagen", context.getRealPath("/img/logoSena.jpg"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dashio", "root", "root");
           
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/webapp1966781b/reportes/ListaUsuarios.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Certificado.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }
    
    
        public void descargaCertificado( String idUsuario) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("idUsuario",idUsuario);
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dashio", "root", "root");
           
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/webapp1966781b/reportes/ReporteUsuario.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=CertificadoIndividual.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.webapp1966781b.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

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

    public Usuario getUsuarioSelect() {
        return usuarioSelect;
    }

    public void setUsuarioSelect(Usuario usuarioSelect) {
        this.usuarioSelect = usuarioSelect;
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

}
