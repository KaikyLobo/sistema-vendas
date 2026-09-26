package br.edu.puc.vendas.dao;

import br.edu.puc.vendas.conexao.ConnectionFactory;
import br.edu.puc.vendas.modelo.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornecedorDAO {

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstancia().getConnection();
    }

    private Fornecedor mapearFornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
        fornecedor.setNome(rs.getString("nome"));
        fornecedor.setTelefone(rs.getString("telefone"));

        return fornecedor;
    }

    public void cadastrar(Fornecedor fornecedor) throws SQLException {

        String sql = """
                INSERT INTO fornecedor (nome, telefone)
                VALUES (?, ?)
                """;

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    fornecedor.setIdFornecedor(rs.getInt(1));
                }
            }
        }
    }

    public Optional<Fornecedor> buscarPorId(int idFornecedor)
            throws SQLException {

        String sql = """
                SELECT *
                FROM fornecedor
                WHERE id_fornecedor = ?
                """;

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearFornecedor(rs));
                }
            }
        }

        return Optional.empty();
    }

    public List<Fornecedor> listarTodos() throws SQLException {

        String sql = """
                SELECT *
                FROM fornecedor
                ORDER BY id_fornecedor
                """;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                fornecedores.add(mapearFornecedor(rs));
            }
        }

        return fornecedores;
    }

    public void atualizar(Fornecedor fornecedor) throws SQLException {

        String sql = """
                UPDATE fornecedor
                SET nome = ?, telefone = ?
                WHERE id_fornecedor = ?
                """;

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setInt(3, fornecedor.getIdFornecedor());

            stmt.executeUpdate();
        }
    }

    public void remover(int idFornecedor) throws SQLException {

        String sql = """
                DELETE FROM fornecedor
                WHERE id_fornecedor = ?
                """;

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);

            stmt.executeUpdate();
        }
    }

    public List<String> listarProdutosPorFornecedor(int idFornecedor)
            throws SQLException {

        String sql = """
                SELECT p.nome
                FROM produtos p
                INNER JOIN fornecedor f
                    ON p.id_fornecedor = f.id_fornecedor
                WHERE f.id_fornecedor = ?
                """;

        List<String> produtos = new ArrayList<>();

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(rs.getString("nome"));
                }
            }
        }

        return produtos;
    }
}