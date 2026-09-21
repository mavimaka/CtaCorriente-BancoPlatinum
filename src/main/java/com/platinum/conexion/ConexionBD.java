package com.platinum.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Parámetros de conexión a MySQL local
    private static final String URL = "jdbc:mysql://localhost:3306/Cuentas_clientes?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String CLAVE = "admin123"; 

    public static Connection getConexion() throws SQLException {
        try {
            // Carga del Driver JDBC de MySQL agregado previamente en el pom.xml
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado en pom.xml");
        }
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
