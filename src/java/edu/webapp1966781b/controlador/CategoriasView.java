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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Usuario
 */
@Named(value = "categoriasView")
@ViewScoped
public class CategoriasView implements Serializable{
    @EJB
    private CategoriaFacadeLocal categoriaFacadeLocal;
    @EJB
    private ProductoFacadeLocal productoFacadeLocal;
    
    
    private ArrayList<Categoria> listaCategorias = new ArrayList<>();
    private Categoria categoriaSelect = new  Categoria();
    
    
    /**
     * Creates a new instance of CategoriasView
     */
    public CategoriasView() {
    }

   @PostConstruct
   public void cargaCategorias(){
       try {
           listaCategorias.addAll( categoriaFacadeLocal.findAll());
           categoriaSelect = categoriaFacadeLocal.find(1);
       } catch (Exception e) {
           System.out.println("edu.webapp1966781b.controlador.CategoriasView.cargaCategorias() " + e.getMessage());
       }
   }
   
   public void seleccionCategoria(int id){
       try {
           categoriaSelect = categoriaFacadeLocal.find(id);
       } catch (Exception e) {
           System.out.println("edu.webapp1966781b.controlador.CategoriasView.seleccionCategoria() " + e.getMessage() );
       }
   }
   
   public int catidadproductoscategoria(int fk_produto){
       try {
           return categoriaFacadeLocal.catidadProductoCategoria(fk_produto);
       } catch (Exception e) {
           System.out.println("edu.webapp1966781b.controlador.CategoriasView.catidadproductoscategoria() " + e.getMessage() ); 
           return 0;
       }
   }
   
   public List<Producto> listaProductos(){
       try {
           return productoFacadeLocal.listaProdutosporcategoria(categoriaSelect.getIdcategorias());
       } catch (Exception e) {
           System.out.println("edu.webapp1966781b.controlador.CategoriasView.listaProductos() " + e.getMessage());
           return  new ArrayList<>();
       }
   }
   
    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public CategoriaFacadeLocal getCategoriaFacadeLocal() {
        return categoriaFacadeLocal;
    }

    public void setCategoriaFacadeLocal(CategoriaFacadeLocal categoriaFacadeLocal) {
        this.categoriaFacadeLocal = categoriaFacadeLocal;
    }

    public Categoria getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(Categoria categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }
    
}
