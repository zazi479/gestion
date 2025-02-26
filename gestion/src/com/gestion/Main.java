

package com.gestion;

import com.gestion.clientes.CrearClientes;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando la aplicación...");

        SwingUtilities.invokeLater(() -> {
            System.out.println("Creando la ventana de clientes...");
            CrearClientes ventana = new CrearClientes();
            System.out.println("Ventana de clientes creada.");
            ventana.setVisible(true);
            System.out.println("Ventana debería ser visible ahora.");
        });
       
    }
    
   
}

