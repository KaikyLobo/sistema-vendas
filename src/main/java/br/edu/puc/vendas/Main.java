package br.edu.puc.vendas;

import br.edu.puc.vendas.dao.FornecedorDAO;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try {

            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            fornecedorDAO.remover(1);

            System.out.println("Fornecedor removido com sucesso!");

        } catch (SQLException e) {

            System.out.println("Não foi possível remover o fornecedor.");
            System.out.println("Existem produtos vinculados a esse fornecedor.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}