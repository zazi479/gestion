package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion"; // Reemplázalo con tu base de datos
    private static final String USER = "root"; // Tu usuario de MySQL
    private static final String PASSWORD = ""; // Tu contraseña de MySQL

    /**
     * Método para obtener la conexión a la base de datos.
     */
    public static Connection getConnection() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegura que el driver esté cargado
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se pudo cargar el controlador JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: No se pudo conectar a la base de datos");
            e.printStackTrace();
        }
        return conexion;
    }

    /**
     * Método corregido para obtener la conexión sin excepciones no soportadas.
     */
    public static Connection obtenerConexion() {
        return getConnection(); // Ahora devuelve una conexión válida
    }
}
