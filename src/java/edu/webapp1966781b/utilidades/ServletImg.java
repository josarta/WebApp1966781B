/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.webapp1966781b.utilidades;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author joseluissartaalvarez
 */
@WebServlet("/images/*")
public class ServletImg extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet imagenesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet imagenesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String filename = request.getPathInfo().substring(1);
        if(!filename.isEmpty()){
        String[] ruta = filename.split("/");
        File folder = new File("C:\\imgdashio\\Productos\\Categorias\\" + filename);
        System.out.println(folder.getAbsolutePath());
                
                
   //     File file = new File(rutaCarpeta+"/"+ruta[0] , ruta[1]);
        response.setHeader("Content-Type", getServletContext().getMimeType(ruta[1]));
        response.setHeader("Content-Length", String.valueOf(folder.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + ruta[1] + "\"");
        try {
               Files.copy(folder.toPath(), response.getOutputStream());
               processRequest(request, response);
        } catch (IOException | ServletException e) {
            System.out.println("--> " + e.getMessage());
            
        }
       }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


