package accesos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoPersona {

    private Connection con;
    private Statement set;

    public void abrirConexion() {
        String urlBD = "jdbc:mysql://localhost:3307/pasatiempos?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(urlBD,
                    "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

//    public void insertarAlumno(String idAlumno, String apellidoPaterno,
//            String apellidoMaterno, String nombre,
//            String sexo) {
//        try {
//            set = con.createStatement();
//            set.executeUpdate("INSERT INTO alumno "
//                    + " (idAlumno, apellidoPaterno, apellidoMaterno, nombre,fechaNacimiento, sexo)"
//                    + " VALUES (" + idAlumno + ", '" + apellidoPaterno + "', '"
//                    + apellidoMaterno + "', '" + nombre + "', '"
//                    + sexo + "')");
//            set.close();
//        } catch (SQLException e) {
//            System.err.println("Error: " + e.getMessage());
//        }
//    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void insertarPersona(String idPersona, String nombres, String apellidoPaterno, String apellidoMaterno,
            String fechaNacimiento, String sexo) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO pasatiempos.persona (idPersona,nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo)"
                    + " VALUES (" + idPersona + ", '" + nombres + "', '" + apellidoPaterno + "', '"
                    + apellidoMaterno + "', '" + fechaNacimiento + "', '" + sexo + "')");
            System.out.println("INSERT INTO pasatiempos.persona (idPersona,nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo)"
                    + " VALUES (" + idPersona + ", '" + nombres + "', '" + apellidoPaterno + "', '"
                    + apellidoMaterno + "', '" + fechaNacimiento + "', '" + sexo + "')");
            set.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
