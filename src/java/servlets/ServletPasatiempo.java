/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import accesos.AccesoPasatiempo;
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
public class ServletPasatiempo extends HttpServlet {

 private AccesoPasatiempo bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new AccesoPasatiempo();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idPasatiempo = (String)request.getParameter("idPasatiempo");
        String nombre = (String)request.getParameter("nombre");
        String descripcion = (String)request.getParameter("descripcion");
        bd.insertarPasatiempo(idPasatiempo,nombre, descripcion);
        devolverPaginaHTML(response);        
    }
    
    public void devolverPaginaHTML(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Respuesta de inserción del pasatiempo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Los datos del pasatiempo han sido registrados correctamente</h1>");
            out.println("<a href=\"reportePasatiempos.jsp\" >Ver reporte de los pasatiempos</a><p>");
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
        return "Servlet para la inserción de datos básicos de los pasatiempos";
    }
    
    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
