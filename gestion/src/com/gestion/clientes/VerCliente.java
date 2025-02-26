package com.gestion.clientes;

import com.gestion.ConexionBD;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.JTableHeader;

/**
 *
 * @author zazi1
 */


public class VerCliente extends JPanel {

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    public VerCliente() {
        setLayout(new BorderLayout());

        // 🔹 Crear modelo de la tabla con nombres de columnas
        String[] columnas = {"ID", "Nombre", "Dirección", "Código Postal", "Población", "Provincia", "País", "CIF", "Teléfono", "Email", "IBAN"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        cargarDatosClientes();

        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarDatosClientes() {
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                        rs.getInt("id"),
                        rs.getString("nombreCliente"),
                        rs.getString("direccionCliente"),
                        rs.getString("cpCliente"),
                        rs.getString("poblacionCliente"),
                        rs.getString("provinciaCliente"),
                        rs.getString("paisCliente"),
                        rs.getString("cifCliente"),
                        rs.getString("telCliente"),
                        rs.getString("emailCliente"),
                        rs.getString("ibanCliente")
                };
                modeloTabla.addRow(fila);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los clientes: " + ex.getMessage());
        }
    }
}
