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

import Models.ItensVenda;
import DAO.Conexao;
import Models.ItensVenda;
import Models.ItensVenda;
import java.sql.PreparedStatement;

public class ItensVendaDAO {
    // Configura essas variáveis de acordo com o seu banco  

    private Connection conexao = null;
    private Statement comando = null;

    private PreparedStatement pst = null;

    public void apagar(String IdVenda) {
        conectar();
        try {
            comando.executeUpdate("DELETE FROM tbitens_venda WHERE IdVenda = '" + IdVenda
                    + "';");
        } catch (SQLException e) {
            imprimeErro("Erro ao apagar itensvenda", e.getMessage());
        } finally {
            fechar();
        }
    }

    public Vector<ItensVenda> buscarTodos() {
        conectar();
        Vector<ItensVenda> resultados = new Vector<ItensVenda>();
        ResultSet rs;
        try {
            pst = conexao.prepareStatement("select * from tbitens_venda");
            rs = pst.executeQuery();
            while (rs.next()) {
                ItensVenda temp = new ItensVenda(1, "Quantidade", 1, "Total");
                temp.setIdVenda(rs.getInt("IdVenda"));
                temp.setQuantidade(rs.getString("Quantidade"));
                temp.setIdproduto(rs.getInt("Idproduto"));
                temp.setTotal(rs.getString("Total"));
                resultados.add(temp);
            }
            return resultados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            imprimeErro("Erro ao buscar clientes", e.getMessage());
            return null;
        }
    }

    public void atualizar(ItensVenda itensvenda) {
        conectar();
        System.out.println("Atualizada!");
        try {
            String sql = "update tbitens_venda set IdVenda=?, Quantidade=?, Idproduto=?, Total=?";
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, itensvenda.getIdVenda());
            pst.setString(2, itensvenda.getQuantidade());
            pst.setInt(3, itensvenda.getIdproduto());
            pst.setString(4, itensvenda.getTotal());
            System.out.println(pst.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fechar();
        }
    }

    public Vector<ItensVenda> buscar(String Codigo) {
        conectar();
        Vector<ItensVenda> resultados = new Vector<ItensVenda>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM tbitens_venda WHERE Codigo LIKE '"
                    + Codigo + "%';");
            while (rs.next()) {
                ItensVenda temp = new ItensVenda(1, "Quantidade", 1, "Total");
                temp.setIdVenda(rs.getInt("IdVenda"));
                temp.setQuantidade(rs.getString("Quantidade"));
                temp.setIdproduto(rs.getInt("Idproduto"));
                temp.setTotal(rs.getString("Total"));
                resultados.add(temp);
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar itensvenda", e.getMessage());
            return null;
        }

    }

    public void insere(ItensVenda itensvenda) {
        conectar();
        try {
            String sql = "INSERT INTO tbitens_venda VALUES (?,?,?,?);";
            
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, itensvenda.getIdVenda());
            pst.setString(2, itensvenda.getQuantidade());
            pst.setInt(3, itensvenda.getIdproduto());
            pst.setString(4, itensvenda.getTotal());
            System.out.println(pst.toString());
            System.out.println(pst.executeUpdate());
            
            System.out.println("Inserida!");
        } catch (SQLException e) {
            imprimeErro("Erro ao inserir ItensVenda", e.getMessage());
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
            if (pst != null)
                pst.close();
            if (comando != null)
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
