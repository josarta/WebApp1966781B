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
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

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
    private int id_categoria = 0;

    /**
     * Creates a new instance of ProductosView
     */
    @PostConstruct
    public void cargaInicialProductos() {
        listaProductos.addAll(productoFacadeLocal.listaProdutosporcategoria(1));
    }

    public void nuevoProducto() {
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

    public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                Producto newP = new Producto();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);                   
                    switch (filasContador) {
                        case 0:
                            newP.setSerial(hssfCell.toString());
                            filasContador++;
                            break;
                        case 1:
                            newP.setNombre(hssfCell.toString());
                            filasContador++;
                            break;
                        case 2:
                            newP.setImagenRuta(hssfCell.toString());
                            filasContador++;
                            break;
                        case 3:
                            newP.setCantidad(hssfCell.toString());
                            filasContador++;
                            break;
                        case 4:
                            newP.setValorCompra(hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 5:
                            newP.setValorVenta(hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 6:
                            Categoria nueva = categoriaFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setFkCategoria(nueva);
                            productoFacadeLocal.create(newP);
                            filasContador = 0;
                            break;
                    }

                }
            }

        } catch (Exception e) {
        }
    }

    public void cargaListaProductos(FileUploadEvent event) throws IOException {
        InputStream input = event.getFile().getInputStream();
        List cellData = new ArrayList();
        try {
            XSSFWorkbook workBook = new XSSFWorkbook(input);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTemp = new ArrayList();
                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTemp.add(hssfCell);
                }
                cellData.add(cellTemp);
            }
            insertarXLS(cellData);
        } catch (Exception e) {
            System.out.println("edu.webapp1966781b.controlador.ProductosView.cargaListaProductos() " + e.getMessage());
            PrimeFaces.current().executeScript("swal('Problemas ingresando el archivo' , 'error');");
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("index.xhtml");
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
