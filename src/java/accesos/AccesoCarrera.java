/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alex RomHer
 */
public class AccesoCarrera {
     private Connection con;
    private Statement set;

    public void abrirConexion() {
        String urlBD = "jdbc:mysql://localhost:3307/cu_uaem_vm2?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(urlBD,
                    "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void insertarCarrera(String idCarrera, String nombre, String siglas, String creditos, String duracionMinima, String duracionMaxima, String fechaDeCreacion) {
            try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO carrera "
                    + " (idCarrera,nombre, siglas, creditos, duracionMinima, duracionMaxima,fechaDeCreacion)"
                    + " VALUES (" + idCarrera + ", '" + nombre + "', '" + siglas + "', " + creditos + ", "
                    + duracionMinima +", " + duracionMaxima +", '" + fechaDeCreacion + "')");
            set.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    
}
