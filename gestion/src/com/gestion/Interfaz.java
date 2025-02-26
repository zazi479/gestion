
package com.gestion;
import javax.swing.*;

public class Interfaz extends JFrame{
    
    
    public Interfaz(){
        setTitle("Sistema de Gestión");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setContentPane(new PanelPrincipal());  // ✅ CORRECTO: Usa setContentPane para un JFrame dentro de otro

        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        
        SwingUtilities.invokeLater(Interfaz::new);
    }
    
    
    
    
    
}
