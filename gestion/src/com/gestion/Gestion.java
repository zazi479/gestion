package com.gestion;

import java.sql.Connection;

public class Gestion {
    public static void main(String[] args) {
        // Probar la conexión a la base de datos
        Connection conexion = ConexionBD.getConnection();
        if (conexion != null) {
            System.out.println("¡Conexión establecida correctamente!");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}
