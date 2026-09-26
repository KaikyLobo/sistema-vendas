package br.edu.puc.vendas.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instancia;

    private final String URL = "jdbc:postgresql://localhost:5432/systema_vendas";
    private final String USUARIO = "postgres";
    private final String SENHA = "1234";

    private ConnectionFactory() {
    }

    public static ConnectionFactory getInstancia() {

        if (instancia == null) {
            instancia = new ConnectionFactory();
        }

        return instancia;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}