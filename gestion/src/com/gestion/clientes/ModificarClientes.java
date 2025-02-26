package com.gestion.clientes;

import com.gestion.ConexionBD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificarClientes extends JPanel {

    private JTextField txtNombre, txtDireccion, txtCP, txtPoblacion, txtProvincia, txtPais, txtCIF, txtTelefono, txtEmail, txtIBAN;
    private JButton btnGuardar;
    private int clienteID; // ID del cliente a modificar

    public ModificarClientes(int clienteID) {
        this.clienteID = clienteID;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Margen alrededor del formulario

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // 🔹 Crear los campos de texto
        txtNombre = new JTextField(15);
        txtDireccion = new JTextField(15);
        txtCP = new JTextField(10);
        txtPoblacion = new JTextField(15);
        txtProvincia = new JTextField(15);
        txtPais = new JTextField(15);
        txtCIF = new JTextField(15);
        txtTelefono = new JTextField(15);
        txtEmail = new JTextField(15);
        txtIBAN = new JTextField(15);

        btnGuardar = new JButton("Guardar Cambios");

        // 🔹 Agregar etiquetas y campos en el panel
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

        // 🔹 Botón Guardar
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        // 🔹 Cargar datos del cliente
        cargarDatosCliente(clienteID);

        // 🔹 Acción botón Guardar
        btnGuardar.addActionListener(e -> modificarCliente());
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

    private void cargarDatosCliente(int clienteID) {
        System.out.println("Cargando datos para el cliente ID: " + clienteID);
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                txtNombre.setText(rs.getString("nombreCliente"));
                txtDireccion.setText(rs.getString("direccionCliente"));
                txtCP.setText(rs.getString("cpCliente"));
                txtPoblacion.setText(rs.getString("poblacionCliente"));
                txtProvincia.setText(rs.getString("provinciaCliente"));
                txtPais.setText(rs.getString("paisCliente"));
                txtCIF.setText(rs.getString("cifCliente"));
                txtTelefono.setText(rs.getString("telCliente"));
                txtEmail.setText(rs.getString("emailCliente"));
                txtIBAN.setText(rs.getString("ibanCliente"));
                System.out.println("Datos cargados correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el cliente con ID: " + clienteID);
                System.out.println("No se encontraron datos.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del cliente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void modificarCliente() {
        String sql = "UPDATE clientes SET nombreCliente=?, direccionCliente=?, cpCliente=?, poblacionCliente=?, provinciaCliente=?, paisCliente=?, cifCliente=?, telCliente=?, emailCliente=?, ibanCliente=? WHERE id=?";
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
            stmt.setInt(11, clienteID);

            int filasModificadas = stmt.executeUpdate();
            if (filasModificadas > 0) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el cliente.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar el cliente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
