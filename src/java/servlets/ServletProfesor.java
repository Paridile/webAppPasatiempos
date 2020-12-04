/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import accesos.AccesoProfesor;
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
public class ServletProfesor extends HttpServlet {

   private AccesoProfesor bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new AccesoProfesor();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String idProfesor = (String)request.getParameter("idProfesor");
        String apellidoPaterno = (String)request.getParameter("apellidoPaterno");
        String apellidoMaterno = (String)request.getParameter("apellidoMaterno");
        String nombres = (String)request.getParameter("nombres");
        String fechaNacimiento=(String)request.getParameter("fechaNacimiento");
        String sexo = (String)request.getParameter("sexo");
        String calle=(String)request.getParameter("calle");
        String numeroExterior=(String)request.getParameter("numeroExterior");
        String coloniaLocalidad=(String)request.getParameter("coloniaLocalidad");
        String municipioAlcaldia=(String)request.getParameter("municipioAlcaldia");
        String entidadFederativa=(String)request.getParameter("entidadFederativa");
        String codigoPostal=(String)request.getParameter("codigoPostal");
      
        String telefonoFijo=(String)request.getParameter("telefonoFijo");
        String telefonoMovil=(String)request.getParameter("telefonoMovil");
        String correoElectronico=(String)request.getParameter("correoElectronico");
        String categoria=(String)request.getParameter("categoria");
        String salario=(String)request.getParameter("salario");
        
        
        bd.insertarProfesor(idProfesor,nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo,calle,numeroExterior,coloniaLocalidad,municipioAlcaldia,entidadFederativa,codigoPostal,telefonoFijo,telefonoMovil,correoElectronico,categoria,salario);
        devolverPaginaHTML(response);        
    }
    
    public void devolverPaginaHTML(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Respuesta de inserción del Profesor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Los datos del profesor han sido registrados correctamente</h1>");
            out.println("<a href=\"reporteProfesores.jsp\" >Ver reporte de los Profesores</a><p>");
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
        return "Servlet para la inserción de datos básicos de la carrera";
    }
    
    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
