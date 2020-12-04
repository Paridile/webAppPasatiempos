package servlets;

import accesos.AccesoPersona;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletPersona extends HttpServlet {
    private AccesoPersona bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new AccesoPersona();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idPersona = (String)request.getParameter("idPersona");
        String apellidoPaterno = (String)request.getParameter("apellidoPaterno");
        String apellidoMaterno = (String)request.getParameter("apellidoMaterno");
        String nombre = (String)request.getParameter("nombres");
        String fechaNacimiento=(String)request.getParameter("fechaNacimiento");
        String sexo = (String)request.getParameter("sexo");
        bd.insertarPersona(idPersona,nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo);
        devolverPaginaHTML(response);        
    }
    
    public void devolverPaginaHTML(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Respuesta de inserción de la persona</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Los datos de la persona han sido registrados correctamente</h1>");
            out.println("<a href=\"reportePersonas.jsp\" >Ver reporte de personas</a><p>");
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
        return "Servlet para la inserción de datos básicos de una persona";
    }
    
    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}