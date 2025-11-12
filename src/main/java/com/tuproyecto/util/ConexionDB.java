package com.tuproyecto.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/crudphp";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASS = "robert3ro";
    
    private static Connection instance = null;

    private ConexionDB() {
    }

    public static Connection getConexion() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                instance = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Error: Driver no encontrado", e);
            }
        }
        return instance;
    }

    public static void cerrarConexion() throws SQLException {
        if (instance != null && !instance.isClosed()) {
            instance.close();
        }
    }
}
