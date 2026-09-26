package br.edu.puc.vendas;

import br.edu.puc.vendas.conexao.ConnectionFactory;
import br.edu.puc.vendas.dao.FornecedorDAO;
import br.edu.puc.vendas.modelo.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteAtividadeEstruturada1 {

    public static void main(String[] args) {

        try {

            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            Fornecedor fornecedor = new Fornecedor(
                    "Fornecedor Atividade",
                    "62977777777"
            );

            fornecedorDAO.cadastrar(fornecedor);

            System.out.println("Fornecedor cadastrado: "
                    + fornecedor.getIdFornecedor());

            String sql = """
                    INSERT INTO produtos
                    (sku, nome, preco, estoque, id_fornecedor)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            try (Connection conexao =
                         ConnectionFactory.getInstancia().getConnection();
                 PreparedStatement stmt =
                         conexao.prepareStatement(sql)) {

                stmt.setString(1, "ATV001");
                stmt.setString(2, "Produto Atividade");
                stmt.setDouble(3, 50.00);
                stmt.setInt(4, 10);
                stmt.setInt(5, fornecedor.getIdFornecedor());

                stmt.executeUpdate();
            }

            System.out.println("Produto cadastrado!");

            System.out.println("Produtos do fornecedor:");

            fornecedorDAO
                    .listarProdutosPorFornecedor(
                            fornecedor.getIdFornecedor())
                    .forEach(System.out::println);

            try {

                fornecedorDAO.remover(
                        fornecedor.getIdFornecedor());

                System.out.println("Fornecedor removido!");

            } catch (SQLException e) {

                System.out.println(
                        "Não foi possível remover o fornecedor."
                );

                System.out.println(
                        "Existem produtos vinculados a esse fornecedor."
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}