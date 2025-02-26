package com.gestion.articulos;

import com.gestion.ConexionBD;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificarArticulo extends JPanel {

    private JTextField txtCodigo, txtDescripcion, txtFamilia, txtCoste, txtMargen, txtPVP, txtProveedor, txtStock;
    private JTextArea txtObservaciones;
    private JButton btnGuardar;
    private int idArticulo; // ID del artículo a modificar

    public ModificarArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
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
        
        btnGuardar = new JButton("Guardar Cambios");

        // Agregar etiquetas y campos en el formulario
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

        // Cargar datos del artículo
        cargarDatosArticulo();

        // Acción del botón Guardar
        btnGuardar.addActionListener(e -> modificarArticulo());
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

    private void cargarDatosArticulo() {
        String sql = "SELECT * FROM articulos WHERE idArticulo = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idArticulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                txtCodigo.setText(rs.getString("codigoArticulo"));
                txtDescripcion.setText(rs.getString("descripcionArticulo"));
                txtFamilia.setText(String.valueOf(rs.getInt("familiaArticulo")));
                txtCoste.setText(String.valueOf(rs.getDouble("costeArticulo")));
                txtMargen.setText(String.valueOf(rs.getDouble("margenComercialArticulo")));
                txtPVP.setText(String.valueOf(rs.getDouble("pvpArticulo")));
                txtProveedor.setText(String.valueOf(rs.getInt("proveedorArticulo")));
                txtStock.setText(String.valueOf(rs.getDouble("stockArticulo")));
                txtObservaciones.setText(rs.getString("observacionesArticulo"));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el artículo con ID: " + idArticulo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del artículo: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void modificarArticulo() {
        String sql = "UPDATE articulos SET codigoArticulo=?, descripcionArticulo=?, familiaArticulo=?, costeArticulo=?, margenComercialArticulo=?, pvpArticulo=?, proveedorArticulo=?, stockArticulo=?, observacionesArticulo=? WHERE idArticulo=?";
        try (Connection conn = ConexionBD.getConnection();
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
            stmt.setInt(10, idArticulo);

            int filasModificadas = stmt.executeUpdate();
            if (filasModificadas > 0) {
                JOptionPane.showMessageDialog(this, "Artículo actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el artículo.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar el artículo: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
