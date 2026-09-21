package com.platinum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.platinum.conexion.ConexionBD;

public class TransaccionDAO {

    public boolean realizarTransferencia(String rutCliente, int idCuenta, double monto, String cuentaDestino, String tipoCuenta) {
        String sqlTransaccion = "INSERT INTO Transaccion (rutCliente, idCuenta, montoTransferencia, cuentaTransferencia, TipoCuenta) VALUES (?, ?, ?, ?, ?)";
        String sqlDescuento = "UPDATE CtaCorriente SET monto = monto - ? WHERE idCuenta = ? AND monto >= ?";

        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false); // Inicia transacción para seguridad

            // 1. Descontar saldo
            try (PreparedStatement stmtDesc = conn.prepareStatement(sqlDescuento)) {
                stmtDesc.setDouble(1, monto);
                stmtDesc.setInt(2, idCuenta);
                stmtDesc.setDouble(3, monto);
                int filasAfectadas = stmtDesc.executeUpdate();

                if (filasAfectadas == 0) {
                    conn.rollback(); // Falla si no hay saldo suficiente
                    return false;
                }
            }

            // 2. Registrar transferencia
            try (PreparedStatement stmtTrans = conn.prepareStatement(sqlTransaccion)) {
                stmtTrans.setString(1, rutCliente);
                stmtTrans.setInt(2, idCuenta);
                stmtTrans.setDouble(3, monto);
                stmtTrans.setString(4, cuentaDestino);
                stmtTrans.setString(5, tipoCuenta);
                stmtTrans.executeUpdate();
            }

            conn.commit(); // Guarda los cambios en MySQL
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
