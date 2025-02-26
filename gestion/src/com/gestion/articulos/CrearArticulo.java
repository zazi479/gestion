package com.gestion.articulos;

import com.gestion.ConexionBD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearArticulo extends JPanel {
    
    private JTextField txtCodigo, txtDescripcion, txtFamilia, txtCoste, txtMargen, txtPVP, txtProveedor, txtStock;
    private JTextArea txtObservaciones;
    private JButton btnGuardar;

    public CrearArticulo() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Agrega margen alrededor del formulario
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Crear campos de texto
        txtCodigo = new JTextField(15);
        txtDescripcion = new JTextField(15);
        txtFamilia = new JTextField(10);
        txtCoste = new JTextField(10);
        txtMargen = new JTextField(10);
        txtPVP = new JTextField(10);
        txtProveedor = new JTextField(10);
        txtStock = new JTextField(10);
        txtObservaciones = new JTextArea(3, 15);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        
        btnGuardar = new JButton("Guardar");

        // Agregar etiquetas y campos al formulario
        int row = 0;
        addComponent(new JLabel("Código:"), txtCodigo, row++, gbc);
        addComponent(new JLabel("Descripción:"), txtDescripcion, row++, gbc);
        addComponent(new JLabel("Familia:"), txtFamilia, row++, gbc);
        addComponent(new JLabel("Coste:"), txtCoste, row++, gbc);
        addComponent(new JLabel("Margen Comercial:"), txtMargen, row++, gbc);
        addComponent(new JLabel("PVP:"), txtPVP, row++, gbc);
        addComponent(new JLabel("Proveedor:"), txtProveedor, row++, gbc);
        addComponent(new JLabel("Stock:"), txtStock, row++, gbc);
        
        // Observaciones con scroll
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Observaciones:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JScrollPane(txtObservaciones), gbc);
        row++;

        // Botón "Guardar"
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        // Acción del botón Guardar
        btnGuardar.addActionListener(this::guardarArticulo);
    }

    private void addComponent(JLabel label, JTextField field, int row, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(field, gbc);
    }

    private void guardarArticulo(ActionEvent e) {
        String sql = "INSERT INTO articulos (codigoArticulo, descripcionArticulo, familiaArticulo, costeArticulo, margenComercialArticulo, pvpArticulo, proveedorArticulo, stockArticulo, observacionesArticulo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, txtCodigo.getText());
            stmt.setString(2, txtDescripcion.getText());
            stmt.setInt(3, Integer.parseInt(txtFamilia.getText()));
            stmt.setDouble(4, Double.parseDouble(txtCoste.getText()));
            stmt.setDouble(5, Double.parseDouble(txtMargen.getText()));
            stmt.setDouble(6, Double.parseDouble(txtPVP.getText()));
            stmt.setInt(7, Integer.parseInt(txtProveedor.getText()));
            stmt.setDouble(8, Double.parseDouble(txtStock.getText()));
            stmt.setString(9, txtObservaciones.getText());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Artículo guardado correctamente.");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el artículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
