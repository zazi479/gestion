package com.gestion.clientes;

import com.gestion.ConexionBD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearClientes extends JPanel {

    private JTextField txtNombre, txtDireccion, txtCP, txtPoblacion, txtProvincia, txtPais, txtCIF, txtTelefono, txtEmail, txtIBAN;
    private JButton btnGuardar;

    public CrearClientes() {
        setLayout(new GridBagLayout()); // Cambiamos a GridBagLayout para mejor control del diseño
        setBorder(BorderFactory.createEmptyBorder(20, 90, 20, 80)); // Margen a los lados y arriba/abajo

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        txtNombre = new JTextField(15);
        txtDireccion = new JTextField(15);
        txtCP = new JTextField(15);
        txtPoblacion = new JTextField(15);
        txtProvincia = new JTextField(15);
        txtPais = new JTextField(15);
        txtCIF = new JTextField(15);
        txtTelefono = new JTextField(15);
        txtEmail = new JTextField(15);
        txtIBAN = new JTextField(15);

        btnGuardar = new JButton("Guardar Cliente");

        // Añadir componentes con GridBagLayout
        int row = 0;
        addComponent(new JLabel("Nombre:"), txtNombre, row++, gbc);
        addComponent(new JLabel("Dirección:"), txtDireccion, row++, gbc);
        addComponent(new JLabel("Código Postal:"), txtCP, row++, gbc);
        addComponent(new JLabel("Población:"), txtPoblacion, row++, gbc);
        addComponent(new JLabel("Provincia:"), txtProvincia, row++, gbc);
        addComponent(new JLabel("País:"), txtPais, row++, gbc);
        addComponent(new JLabel("CIF:"), txtCIF, row++, gbc);
        addComponent(new JLabel("Teléfono:"), txtTelefono, row++, gbc);
        addComponent(new JLabel("Email:"), txtEmail, row++, gbc);
        addComponent(new JLabel("IBAN:"), txtIBAN, row++, gbc);

        // Botón "Guardar Cliente"
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        btnGuardar.addActionListener(this::guardarCliente);
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

    private void guardarCliente(ActionEvent e) {
        String sql = "INSERT INTO clientes (nombreCliente, direccionCliente, cpCliente, poblacionCliente, provinciaCliente, paisCliente, cifCliente, telCliente, emailCliente, ibanCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtNombre.getText());
            stmt.setString(2, txtDireccion.getText());
            stmt.setString(3, txtCP.getText());
            stmt.setString(4, txtPoblacion.getText());
            stmt.setString(5, txtProvincia.getText());
            stmt.setString(6, txtPais.getText());
            stmt.setString(7, txtCIF.getText());
            stmt.setString(8, txtTelefono.getText());
            stmt.setString(9, txtEmail.getText());
            stmt.setString(10, txtIBAN.getText());

            int filasModificadas = stmt.executeUpdate();
            if (filasModificadas > 0) {
                JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente: " + ex.getMessage());
        }
    }
}
