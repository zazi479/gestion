

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearFactura {
    private JFrame frame;
    private JTextField clienteField, codigoField, nombreField, cantidadField, precioField;
    private JTextArea observacionesField;
    private JTable table;
    private DefaultTableModel model;
    private JLabel subtotalLabel, ivaLabel, totalLabel;
    private double subtotal = 0.0, iva = 0.0, total = 0.0;

    public CrearFactura() {
        frame = new JFrame("Generador de Facturas");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel superior (Cabecera)
        JPanel headerPanel = new JPanel(new GridLayout(3, 2));
        headerPanel.setBorder(BorderFactory.createTitledBorder("Datos de la Factura"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        JLabel facturaLabel = new JLabel("Factura Nº: " + (int) (Math.random() * 10000));
        JLabel fechaLabel = new JLabel("Fecha: " + sdf.format(new Date()));
        JLabel clienteLabel = new JLabel("Cliente:");
        clienteField = new JTextField();
        headerPanel.add(facturaLabel);
        headerPanel.add(fechaLabel);
        headerPanel.add(clienteLabel);
        headerPanel.add(clienteField);

        // Panel central (Tabla de productos)
        String[] columnas = {"Código", "Producto", "Cantidad", "Precio", "Importe"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel inferior (Entrada de productos)
        JPanel inputPanel = new JPanel(new GridLayout(2, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Agregar Artículo"));
        codigoField = new JTextField();
        nombreField = new JTextField();
        cantidadField = new JTextField();
        precioField = new JTextField();
        JButton addButton = new JButton("Agregar");

        inputPanel.add(new JLabel("Código:"));
        inputPanel.add(new JLabel("Producto:"));
        inputPanel.add(new JLabel("Cantidad:"));
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(new JLabel(""));
        inputPanel.add(codigoField);
        inputPanel.add(nombreField);
        inputPanel.add(cantidadField);
        inputPanel.add(precioField);
        inputPanel.add(addButton);

        // Panel Totales
        JPanel totalPanel = new JPanel(new GridLayout(3, 2));
        totalPanel.setBorder(BorderFactory.createTitledBorder("Totales"));
        subtotalLabel = new JLabel("Subtotal: 0.00");
        ivaLabel = new JLabel("IVA (21%): 0.00");
        totalLabel = new JLabel("Total: 0.00");
        totalPanel.add(subtotalLabel);
        totalPanel.add(new JLabel(""));
        totalPanel.add(ivaLabel);
        totalPanel.add(new JLabel(""));
        totalPanel.add(totalLabel);
        totalPanel.add(new JLabel(""));

        // Observaciones
        JPanel observacionesPanel = new JPanel(new BorderLayout());
        observacionesPanel.setBorder(BorderFactory.createTitledBorder("Observaciones"));
        observacionesField = new JTextArea(3, 20);
        observacionesPanel.add(new JScrollPane(observacionesField), BorderLayout.CENTER);

        // Agregar funcionalidad al botón de agregar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        // Agregar paneles a la ventana
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(totalPanel, BorderLayout.EAST);
        frame.add(observacionesPanel, BorderLayout.WEST);

        frame.setVisible(true);
    }

    private void agregarProducto() {
        try {
            String codigo = codigoField.getText();
            String nombre = nombreField.getText();
            int cantidad = Integer.parseInt(cantidadField.getText());
            double precio = Double.parseDouble(precioField.getText());
            double importe = cantidad * precio;

            model.addRow(new Object[]{codigo, nombre, cantidad, precio, importe});

            subtotal += importe;
            iva = subtotal * 0.21;
            total = subtotal + iva;

            subtotalLabel.setText("Subtotal: " + String.format("%.2f", subtotal));
            ivaLabel.setText("IVA (21%): " + String.format("%.2f", iva));
            totalLabel.setText("Total: " + String.format("%.2f", total));

            // Limpiar campos
            codigoField.setText("");
            nombreField.setText("");
            cantidadField.setText("");
            precioField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void mostrarFactura() {
        SwingUtilities.invokeLater(CrearFactura::new);
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearFactura {
    private JFrame frame;
    private JTextField clienteField, codigoField, nombreField, cantidadField, precioField;
    private JTextArea observacionesField;
    private JTable table;
    private DefaultTableModel model;
    private JLabel subtotalLabel, ivaLabel, totalLabel;
    private double subtotal = 0.0, iva = 0.0, total = 0.0;

    public CrearFactura() {
        frame = new JFrame("Generador de Facturas");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel superior (Cabecera)
        JPanel headerPanel = new JPanel(new GridLayout(3, 2));
        headerPanel.setBorder(BorderFactory.createTitledBorder("Datos de la Factura"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        JLabel facturaLabel = new JLabel("Factura Nº: " + (int) (Math.random() * 10000));
        JLabel fechaLabel = new JLabel("Fecha: " + sdf.format(new Date()));
        JLabel clienteLabel = new JLabel("Cliente:");
        clienteField = new JTextField();
        headerPanel.add(facturaLabel);
        headerPanel.add(fechaLabel);
        headerPanel.add(clienteLabel);
        headerPanel.add(clienteField);

        // Panel central (Tabla de productos)
        String[] columnas = {"Código", "Producto", "Cantidad", "Precio", "Importe"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel inferior (Entrada de productos)
        JPanel inputPanel = new JPanel(new GridLayout(2, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Agregar Artículo"));
        codigoField = new JTextField();
        nombreField = new JTextField();
        cantidadField = new JTextField();
        precioField = new JTextField();
        JButton addButton = new JButton("Agregar");

        inputPanel.add(new JLabel("Código:"));
        inputPanel.add(new JLabel("Producto:"));
        inputPanel.add(new JLabel("Cantidad:"));
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(new JLabel(""));
        inputPanel.add(codigoField);
        inputPanel.add(nombreField);
        inputPanel.add(cantidadField);
        inputPanel.add(precioField);
        inputPanel.add(addButton);

        // Panel Totales
        JPanel totalPanel = new JPanel(new GridLayout(3, 2));
        totalPanel.setBorder(BorderFactory.createTitledBorder("Totales"));
        subtotalLabel = new JLabel("Subtotal: 0.00");
        ivaLabel = new JLabel("IVA (21%): 0.00");
        totalLabel = new JLabel("Total: 0.00");
        totalPanel.add(subtotalLabel);
        totalPanel.add(new JLabel(""));
        totalPanel.add(ivaLabel);
        totalPanel.add(new JLabel(""));
        totalPanel.add(totalLabel);
        totalPanel.add(new JLabel(""));

        // Observaciones
        JPanel observacionesPanel = new JPanel(new BorderLayout());
        observacionesPanel.setBorder(BorderFactory.createTitledBorder("Observaciones"));
        observacionesField = new JTextArea(3, 20);
        observacionesPanel.add(new JScrollPane(observacionesField), BorderLayout.CENTER);

        // Agregar funcionalidad al botón de agregar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        // Agregar paneles a la ventana
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(totalPanel, BorderLayout.EAST);
        frame.add(observacionesPanel, BorderLayout.WEST);

        frame.setVisible(true);
    }

    private void agregarProducto() {
        try {
            String codigo = codigoField.getText();
            String nombre = nombreField.getText();
            int cantidad = Integer.parseInt(cantidadField.getText());
            double precio = Double.parseDouble(precioField.getText());
            double importe = cantidad * precio;

            model.addRow(new Object[]{codigo, nombre, cantidad, precio, importe});

            subtotal += importe;
            iva = subtotal * 0.21;
            total = subtotal + iva;

            subtotalLabel.setText("Subtotal: " + String.format("%.2f", subtotal));
            ivaLabel.setText("IVA (21%): " + String.format("%.2f", iva));
            totalLabel.setText("Total: " + String.format("%.2f", total));

            // Limpiar campos
            codigoField.setText("");
            nombreField.setText("");
            cantidadField.setText("");
            precioField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void mostrarFactura() {
        SwingUtilities.invokeLater(CrearFactura::new);
    }
}
