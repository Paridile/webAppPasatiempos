/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import accesos.AccesoMascota;
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
public class ServletMascota extends HttpServlet {

 private AccesoMascota bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new AccesoMascota();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idMascota = (String)request.getParameter("idMascota");
        String nombre = (String)request.getParameter("nombre");
        String descripcion = (String)request.getParameter("descripcion");
        String idTipoMascota = (String)request.getParameter("idTipoMascota");

        bd.insertarMascota(idMascota,nombre, descripcion, idTipoMascota);
        devolverPaginaHTML(response);        
    }
    
    public void devolverPaginaHTML(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!doctype html>");
            out.println("<html lang=\"es\">");
            out.println(" <head>");
            out.println("   <!-- Required meta tags -->");
            out.println("   <meta charset=\"utf-8\">");
            out.println("   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            out.println("    <!-- Bootstrap CSS -->");
            out.println("   <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("    <title>Respuesta de inserción de la mascota</title>");
            out.println(" </head>");
            out.println(" <body style=\"background-color: #eeeeee;\">");
            out.println("     <section>");
            out.println("         <h2 class=\"display-4 container mb-3 pt-5 text-success text-center\">Los datos de la mascota han sido registrados correctamente</h2>");
            out.println("         <div class=\"text-center\">");
            out.println("           <img src=\"https://images.vexels.com/media/users/3/157931/isolated/preview/604a0cadf94914c7ee6c6e552e9b4487-curved-check-mark-circle-icon-by-vexels.png\" class=\"resp\" alt=\"Incersión exitosa\">");
            out.println("         </div>          ");
            out.println("         <div class=\"row justify-content-center pt-3 pb-5\">");
            out.println("           <div class=\"col-4\">");
            out.println("               <a href=\"reporteMascotas.jsp\" >");
            out.println("                   <button type=\"button\" class=\"btn btn-info btn-lg btn-block\">Ver reporte de mascotas</button>");
            out.println("               </a>                                    ");
            out.println("           </div>");
            out.println("           <div class=\"col-4\">");
            out.println("               <a href=\"index.html\">");
            out.println("                   <button type=\"button\" class=\"btn btn-danger btn-lg btn-block\">Regresar al menu principal</button>");
            out.println("               </a>");
            out.println("           </div>");
            out.println("         </div>");
            out.println("  ");
            out.println("</section>");
            out.println("   <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>");
            out.println("   <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>");
            out.println("   <style>");
            out.println("       .resp {");
            out.println("           width: 100%;");
            out.println("           max-width: 300px;");
            out.println("           height: auto;");
            out.println("       }");
            out.println("   </style>");
            out.println(" </body>");
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
