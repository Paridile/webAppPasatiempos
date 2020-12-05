/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import accesos.AccesoPersonaPasatiempo;
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
public class ServletPersonaPasatiempo extends HttpServlet {

 private AccesoPersonaPasatiempo bd;
 String idPersona;
 String idPasatiempo ;
 String añosPasatiempo;
 String horasSemana;
    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new AccesoPersonaPasatiempo();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        idPersona = (String)request.getParameter("IdPersona");
        idPasatiempo = (String)request.getParameter("idPasatiempo");
        añosPasatiempo = (String)request.getParameter("anosPasatiempo");
        horasSemana = (String)request.getParameter("horasSemana");
        bd.insertarPersonaPasatiempo(idPersona,idPasatiempo, añosPasatiempo,horasSemana);
        devolverPaginaHTML(response);        
    }
    
    public void devolverPaginaHTML(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {            
            /* TODO output your page here. You may use following sample code. */            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Respuesta de inserción de la relación persona-pasatiempo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Los datos de la relacion persona-pasatiempo han sido registrados correctamente</h1>");
            out.println("<a href=\"reportePersonaPasatiempo.jsp\" >Ver reporte de las relaciones persona pasatiempo</a><p>");
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
        return "Servlet para la inserción de datos básicos de la relacion persona pasatiempo";
    }
    
    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
