package br.edu.puc.vendas.modelo;

public class Fornecedor {

    private int idFornecedor;
    private String nome;
    private String telefone;

    public Fornecedor() {
    }

    public Fornecedor(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Fornecedor(int idFornecedor, String nome, String telefone) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "idFornecedor=" + idFornecedor +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}