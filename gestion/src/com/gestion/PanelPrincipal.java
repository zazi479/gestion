package com.gestion;

import com.gestion.articulos.CrearArticulo;
import com.gestion.articulos.ModificarArticulo;
import com.gestion.articulos.VerArticulo;
import com.gestion.clientes.CrearClientes;
import com.gestion.clientes.ModificarClientes;
import com.gestion.clientes.VerCliente;




import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JPanel panelNavegacion;
    private JPanel panelSubmenu;
    private boolean panelClientesVisible = false;
    private boolean panelArticulosVisible = false;
    private final Dimension buttonSize = new Dimension(200, 40); // Tamaño estándar para botones
    private final Dimension smallButtonSize = new Dimension(100, 30); // Tamaño más pequeño para "Salir"

    public PanelPrincipal() {
        setLayout(new BorderLayout());

        // 🟢 Barra de navegación principal
        panelNavegacion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelNavegacion.setBackground(Color.LIGHT_GRAY);

        JButton btnClientes = crearBoton("Clientes", buttonSize);
        JButton btnArticulos = crearBoton("Artículos", buttonSize);
        JButton btnFacturas = crearBoton("Facturas", buttonSize);
        JButton btnProveedores = crearBoton("Proveedores", buttonSize);

        panelNavegacion.add(btnClientes);
        panelNavegacion.add(btnArticulos);
        panelNavegacion.add(btnFacturas);
        panelNavegacion.add(btnProveedores);

        // 🟢 Segunda barra de navegación (Submenú) inicialmente vacía
        panelSubmenu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSubmenu.setBackground(Color.DARK_GRAY);
        panelSubmenu.setVisible(false);

        // 🟢 Panel principal de contenido con CardLayout
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        JPanel panelInicio = new JPanel();
        panelInicio.add(new JLabel("<html><h2>Bienvenido al Sistema de Gestión</h2></html>"));

        panelContenido.add(panelInicio, "Inicio");

        // Eventos de botones de navegación principal
        btnClientes.addActionListener(e -> toggleSubmenu("Clientes"));
        btnArticulos.addActionListener(e -> toggleSubmenu("Artículos"));

        // 🟢 Agregar los paneles al diseño principal
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.add(panelNavegacion, BorderLayout.NORTH);
        panelSuperior.add(panelSubmenu, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
    }

    /**
     * Muestra u oculta la barra de navegación secundaria según la categoría seleccionada.
     */
    private void toggleSubmenu(String categoria) {
        panelSubmenu.removeAll(); // Limpiar opciones previas

        boolean estabaAbierto = (categoria.equals("Clientes") && panelClientesVisible) ||
                                (categoria.equals("Artículos") && panelArticulosVisible);

        if (estabaAbierto) {
            cerrarSubmenu();
        } else {
            if (categoria.equals("Clientes")) {
                agregarBotonSubmenu("Crear Cliente", () -> mostrarPanelConVolver(new CrearClientes(), "CrearClientes"));
                agregarBotonSubmenu("Modificar Cliente", this::modificarCliente);
                agregarBotonSubmenu("Ver Clientes", () -> mostrarPanelConVolver(new VerCliente(), "VerClientes"));
            } else if (categoria.equals("Artículos")) {
                agregarBotonSubmenu("Crear Artículo", () -> mostrarPanelConVolver(new CrearArticulo(), "CrearArticulo"));
                agregarBotonSubmenu("Modificar Artículo", this::modificarArticulo);
                agregarBotonSubmenu("Ver Artículos", () -> mostrarPanelConVolver(new VerArticulo(), "VerArticulos"));
            }

            // Agregar botón "Salir" más pequeño
            agregarBotonSalir();

            panelSubmenu.setVisible(true);
            panelClientesVisible = categoria.equals("Clientes");
            panelArticulosVisible = categoria.equals("Artículos");
        }

        panelSubmenu.revalidate();
        panelSubmenu.repaint();
    }

    /**
     * Agrega un botón al submenú de navegación.
     */
    private void agregarBotonSubmenu(String titulo, Runnable action) {
        JButton boton = crearBoton(titulo, buttonSize);
        boton.addActionListener(e -> action.run());
        panelSubmenu.add(boton);
    }

    /**
     * Agrega un botón "Salir" más pequeño para cerrar la barra secundaria.
     */
    private void agregarBotonSalir() {
        JButton botonSalir = crearBoton("Salir", smallButtonSize);
        botonSalir.setBackground(Color.RED);
        botonSalir.setForeground(Color.WHITE);
        botonSalir.addActionListener(e -> cerrarSubmenu());
        panelSubmenu.add(botonSalir);
    }

    /**
     * Cierra el submenú de la barra de navegación secundaria.
     */
    private void cerrarSubmenu() {
        panelSubmenu.setVisible(false);
        panelClientesVisible = false;
        panelArticulosVisible = false;
        cardLayout.show(panelContenido, "Inicio");
    }

    /**
     * Crea un botón con tamaño personalizado.
     */
    private JButton crearBoton(String texto, Dimension size) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(size);
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        return boton;
    }

    /**
     * Método para abrir el panel de modificación de clientes con "Volver".
     */
    private void modificarCliente() {
        String idStr = JOptionPane.showInputDialog(null, "Ingrese ID del cliente a modificar:");
        if (idStr != null) {
            try {
                int idCliente = Integer.parseInt(idStr);
                mostrarPanelConVolver(new ModificarClientes(idCliente), "ModificarClientes");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido. Debe ser un número.");
            }
        }
    }

    /**
     * Método para abrir el panel de modificación de artículos con "Volver".
     */
    private void modificarArticulo() {
        String idStr = JOptionPane.showInputDialog(null, "Ingrese ID del artículo a modificar:");
        if (idStr != null) {
            try {
                int idArticulo = Integer.parseInt(idStr);
                mostrarPanelConVolver(new ModificarArticulo(idArticulo), "ModificarArticulo");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido. Debe ser un número.");
            }
        }
    }

    /**
     * Muestra un panel dentro del CardLayout con un botón "Volver".
     */
    private void mostrarPanelConVolver(JPanel panel, String panelName) {
        JPanel panelConVolver = new JPanel(new BorderLayout());
        JButton botonVolver = crearBoton("Volver", buttonSize);
        botonVolver.addActionListener(e -> cardLayout.show(panelContenido, "Inicio"));

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonVolver);
        
        panelConVolver.add(panel, BorderLayout.CENTER);
        panelConVolver.add(panelBoton, BorderLayout.SOUTH);

        panelContenido.add(panelConVolver, panelName);
        cardLayout.show(panelContenido, panelName);
    }
}
