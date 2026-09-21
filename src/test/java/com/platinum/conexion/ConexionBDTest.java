package com.platinum.conexion;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConexionBDTest {

    @Test
    @DisplayName("Prueba de Conexión Exitosa a MySQL Cuentas_clientes")
    public void testConexionExitosa() {
        try (Connection conn = ConexionBD.getConexion()) {
            
            // 1. Verificamos que el objeto Connection no sea nulo
            assertNotNull(conn, "La conexión no debería ser nula");
            
            // 2. Verificamos que la conexión esté abierta y lista
            assertFalse(conn.isClosed(), "La conexión a la base de datos debe estar abierta");
            
            System.out.println("✔ Prueba Unitaria Exitosa: Conexión establecida con MySQL");
            
        } catch (SQLException e) {
            fail("Error al intentar conectar a la Base de Datos: " + e.getMessage());
        }
    }
}
