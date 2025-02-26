package com.gestion.articulos;

import com.gestion.ConexionBD;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerArticulo extends JPanel {

    private JTable tablaArticulos;
    private JButton btnVolver;
    private final Dimension smallButtonSize = new Dimension(120, 30); // Tamaño pequeño para botón

    public VerArticulo() {
        setLayout(new BorderLayout());

        // 🔹 Crear la tabla
        String[] columnNames = {"ID", "Código", "Descripción", "Familia", "Coste", "Margen", "PVP", "Proveedor", "Stock", "Observaciones"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaArticulos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaArticulos);

        // 🔹 Panel de botones (solo uno)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnVolver = crearBoton("Volver", Color.GRAY);
        panelBotones.add(btnVolver);

        // 🔹 Agregar elementos a la interfaz
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH); // ✅ Solo se agrega aquí, no en otro lugar

        // 🔹 Cargar artículos en la tabla
        cargarArticulos();

        // 🔹 Acción del botón Volver
        btnVolver.addActionListener(e -> {
            Container parent = getParent();
            while (parent != null && !(parent instanceof JPanel && parent.getLayout() instanceof CardLayout)) {
                parent = parent.getParent();
            }

            if (parent instanceof JPanel) {
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show((JPanel) parent, "Inicio"); // Regresar al menú principal
            } else {
                JOptionPane.showMessageDialog(this, "No se puede regresar al menú principal.");
            }
        });
    }

    /**
     * Cargar artículos desde la base de datos y mostrarlos en la tabla.
     */
    private void cargarArticulos() {
        String sql = "SELECT * FROM articulos";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tablaArticulos.getModel();
            model.setRowCount(0); // Limpiar tabla antes de cargar datos

            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("idArticulo"),
                        rs.getString("codigoArticulo"),
                        rs.getString("descripcionArticulo"),
                        rs.getInt("familiaArticulo"),
                        rs.getDouble("costeArticulo"),
                        rs.getDouble("margenComercialArticulo"),
                        rs.getDouble("pvpArticulo"),
                        rs.getInt("proveedorArticulo"),
                        rs.getDouble("stockArticulo"),
                        rs.getString("observacionesArticulo")
                };
                model.addRow(rowData);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los artículos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Crea un botón con tamaño pequeño y color personalizado.
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(smallButtonSize);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        return boton;
    }
}
