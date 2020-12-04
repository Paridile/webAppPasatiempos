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
public class AccesoProfesor {
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

 

    public void insertarProfesor(String idProfesor, String nombres, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String sexo, String calle, String numeroExterior, String coloniaLocalidad, String municipioAlcaldia, String entidadFederativa, String codigoPostal, String telefonoFijo, String telefonoMovil, String correoElectronico, String categoria, String salario) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO profesor (idProfesor,nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo,calle,numeroExterior,coloniaLocalidad,municipioAlcaldia,entidadFederativa,codigoPostal,telefonoFijo,telefonoMovil,correoElectronico,categoria,salario)"
                    + " VALUES (" + idProfesor + ", '" + nombres + "', '" + apellidoPaterno + "', '"
                    + apellidoMaterno + "', '" + fechaNacimiento + "', '" + sexo +"', '" + calle + "', "
                    + numeroExterior + ", '" + coloniaLocalidad +  "', '" + municipioAlcaldia +  "', '"
                    + entidadFederativa + "', "  + codigoPostal + ", '" + telefonoFijo  + "', '" 
                    + telefonoMovil + "', '"+ correoElectronico+"', '" + categoria  +"', '" + salario + "')");
            set.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
