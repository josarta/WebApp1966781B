/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.controlador;

import edu.webapp1966781b.entity.Categoria;
import edu.webapp1966781b.entity.Producto;
import edu.webapp1966781b.facade.CategoriaFacadeLocal;
import edu.webapp1966781b.facade.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Usuario
 */
@Named(value = "productosView")
@ViewScoped
public class ProductosView implements Serializable {
    @EJB
    ProductoFacadeLocal productoFacadeLocal;
    
    @EJB
    CategoriaFacadeLocal categoriaFacadeLocal;
    
    @Inject
    CategoriasView categoriasView;
    
    
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private Producto productoNuevo = new Producto();
    private  int id_categoria =0;
    
    
        
    /**
     * Creates a new instance of ProductosView
     */
    @PostConstruct
    public void cargaInicialProductos() {
        listaProductos.addAll(productoFacadeLocal.listaProdutosporcategoria(1));
    }
    
    public void nuevoProducto(){
        try {
            Categoria cateIn = categoriaFacadeLocal.find(id_categoria);
            productoNuevo.setFkCategoria(cateIn);
            productoNuevo.setImagenRuta(categoriasView.getRutaImg());
            productoFacadeLocal.create(productoNuevo);
        } catch (Exception e) {
            System.out.println("edu.webapp1966781b.controlador.ProductosView.nuevoProducto() " + e.getMessage());
        }
         PrimeFaces.current().executeScript("$('#myModal').hide();");      
    }

    public ProductosView() {
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProductoNuevo() {
        return productoNuevo;
    }

    public void setProductoNuevo(Producto productoNuevo) {
        this.productoNuevo = productoNuevo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

}
