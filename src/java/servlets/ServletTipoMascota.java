/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import accesos.AccesoTipoMascota;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex RomHer
 */
public class ServletTipoMascota extends HttpServlet {

 private AccesoTipoMascota bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new AccesoTipoMascota();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTipoMascota = (String)request.getParameter("idTipoMascota");        
        String descripcion = (String)request.getParameter("descripcion");        

        bd.insertarTipoMascota(idTipoMascota,descripcion);
        devolverPaginaHTML(response);        
    }
    
    public void devolverPaginaHTML(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Respuesta de inserción del tipo de mascota</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Los datos del tipo de mascota han sido registrados correctamente</h1>");
            out.println("<a href=\"reporteTipoMascota.jsp\" >Ver reporte de los tipos mascotas</a><p>");
            out.println("<a href =\"index.html\">Ir al inicio</a>");
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la inserción de datos básicos de la mascota";
    }
    
    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
