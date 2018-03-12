/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import Models.Produto;
import DAO.Conexao;
import Models.Produto;
import java.sql.PreparedStatement;

public class ProdutoDao {
    // Configura essas variáveis de acordo com o seu banco  

    private Connection conexao;
    private Statement comando;

    private PreparedStatement pst = null;

    public void apagar(String Codigo) {
        conectar();
        try {
            comando.executeUpdate("DELETE FROM tbprodutos WHERE Codigo = '" + Codigo
                    + "';");
        } catch (SQLException e) {
            imprimeErro("Erro ao apagar clientes", e.getMessage());
        } finally {
            fechar();
        }
    }

    public Vector<Produto> buscarTodos() {
        conectar();
        Vector<Produto> resultados = new Vector<Produto>();
        ResultSet rs;
        try {
            pst = conexao.prepareStatement("select * from tbprodutos");
            rs = pst.executeQuery();
            while (rs.next()) {
                Produto temp = new Produto(1, "Descricao", "Sigla", "Preco_Custo", "Preco_Venda", "Tamanho", "Estoque_Central", "Estoque_Aldeia", "Estoque_Itaim", "Estoque_Aclimacao", "Estoque_Verbo");
                // pega todos os atributos da produto  
                temp.setCodigo(rs.getInt("Codigo"));
                temp.setDescricao(rs.getString("Descricao"));
                temp.setSigla(rs.getString("Sigla"));
                temp.setPrecoCusto(rs.getString("Preco_Custo"));
                temp.setPrecoVenda(rs.getString("Preco_Venda"));
                temp.setTamanho(rs.getString("Tamanho"));
                temp.setEstoqueCentral(rs.getString("Estoque_Central"));
                temp.setEstoqueAldeia(rs.getString("Estoque_Aldeia"));
                temp.setEstoqueItaim(rs.getString("Estoque_Itaim"));
                temp.setEstoqueAclimacao(rs.getString("Estoque_Aclimacao"));
                temp.setEstoqueVerbo(rs.getString("Estoque_Verbo"));
                resultados.add(temp);
            }
            return resultados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            imprimeErro("Erro ao buscar clientes", e.getMessage());
            return null;
        }
    }

    public void atualizar(Produto produto) {
        conectar();
        String com = "UPDATE tbprodutos SET Descricao = '" + produto.getDescricao() + "', Sigla =" + produto.getSigla() + "', Preco_Custo =" + produto.getPrecoCusto() + "', Preco_Venda =" + produto.getPrecoVenda() + ", Tamanho = '" + produto.getTamanho() + "', Estoque_Central ='" + produto.getEstoqueCentral() + "', Estoque_Aldeia ='" + produto.getEstoqueAldeia() + "', Estoque_Itaim ='" + produto.getEstoqueItaim() + "', Estoque_Aclimacao ='" + produto.getEstoqueAclimacao() + "', Estoque_Verbo ='" + produto.getEstoqueVerbo() + "' WHERE  Codigo = '" + produto.getCodigo() + "';";
        System.out.println("Atualizada!");
        try {
            comando.executeUpdate(com);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fechar();
        }
    }

    public Vector<Produto> buscar(String Codigo) {
        conectar();
        Vector<Produto> resultados = new Vector<Produto>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM tbprodutos WHERE Codigo LIKE '"
                    + Codigo + "%';");
            while (rs.next()) {
                Produto temp = new Produto(1, "Descricao", "Sigla", "Preco_Custo", "Preco_Venda", "Tamanho", "Estoque_Central", "Estoque_Aldeia", "Estoque_Itaim", "Estoque_Aclimacao", "Estoque_Verbo");
                temp.setCodigo(rs.getInt("Codigo"));
                temp.setDescricao(rs.getString("Descricao"));
                temp.setSigla(rs.getString("Sigla"));
                temp.setPrecoCusto(rs.getString("Preco_Custo"));
                temp.setPrecoVenda(rs.getString("Preco_Venda"));
                temp.setTamanho(rs.getString("Tamanho"));
                temp.setEstoqueCentral(rs.getString("Estoque_Central"));
                temp.setEstoqueAldeia(rs.getString("Estoque_Aldeia"));
                temp.setEstoqueItaim(rs.getString("Estoque_Itaim"));
                temp.setEstoqueAclimacao(rs.getString("Estoque_Aclimacao"));
                temp.setEstoqueVerbo(rs.getString("Estoque_Verbo"));
                resultados.add(temp);
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar produto", e.getMessage());
            return null;
        }

    }

    public void insere(Produto produto) {
        conectar();
        try {
            comando.executeUpdate("INSERT INTO tbprodutos VALUES('" + produto.getDescricao() + "', " + produto.getSigla() + "', " + produto.getPrecoCusto() + "', " + produto.getPrecoVenda() + ", '" + produto.getTamanho() + "', '" + produto.getEstoqueCentral() + "', '" + produto.getEstoqueAldeia() + "', '" + produto.getEstoqueItaim() + "', '" + produto.getEstoqueAclimacao() + "', '" + produto.getEstoqueVerbo() + "' WHERE  Codigo = '" + produto.getCodigo() + "';");
            System.out.println("Inserida!");
        } catch (SQLException e) {
            imprimeErro("Erro ao inserir Produto", e.getMessage());
        } finally {
            fechar();
        }
    }

    private void conectar() {
        try {
            conexao = Conexao.conector();
            comando = conexao.createStatement();
        } catch (SQLException e) {
            imprimeErro("Erro ao conectar", e.getMessage());
        }

    }

    private void fechar() {
        try {
            comando.close();
            conexao.close();
            System.out.println("Conexão Fechada");
        } catch (SQLException e) {
            imprimeErro("Erro ao fechar conexão", e.getMessage());
        }
    }

    private void imprimeErro(String msg, String msgErro) {
        JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);
        System.err.println(msg);
        System.out.println(msgErro);
        System.exit(0);
    }
}
