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

import Models.FormaPagamento;
import DAO.Conexao;
import Models.FormaPagamento;
import java.sql.PreparedStatement;

public class FormaPagamentoDAO {
    // Configura essas variáveis de acordo com o seu banco  

    private Connection conexao;
    private Statement comando;

    private PreparedStatement pst = null;

    public void apagar(String IdVenda) {
        conectar();
        try {
            comando.executeUpdate("DELETE FROM tbformapagamento WHERE IdVenda = '" + IdVenda
                    + "';");
        } catch (SQLException e) {
            imprimeErro("Erro ao apagar ar formapagamento", e.getMessage());
        } finally {
            fechar();
        }
    }

    public Vector<FormaPagamento> buscarTodos() {
        conectar();
        Vector<FormaPagamento> resultados = new Vector<FormaPagamento>();
        ResultSet rs;
        try {
            pst = conexao.prepareStatement("select * from tbformapagamento");
            rs = pst.executeQuery();
            while (rs.next()) {
                FormaPagamento temp = new FormaPagamento(1, "Forma_Pagamento", "Dinheiro", "Dia");
                temp.setIdVenda(rs.getInt("IdVenda"));
                temp.setForma_Pagamento(rs.getString("Forma_Pagamento"));
                temp.setDinheiro(rs.getString("Dinheiro"));
                temp.setDia(rs.getString("Dia"));
            }
            return resultados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            imprimeErro("Erro ao buscar clientes", e.getMessage());
            return null;
        }
    }

    public void atualizar(FormaPagamento formapagamento) {
        conectar();
        System.out.println("Atualizada!");
        try {
            String sql = "update tbformapagamento set IdVenda=?, Forma_Pagamento=?, Dinheiro=?, Dia=?";
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, formapagamento.getIdVenda());
            pst.setString(2, formapagamento.getForma_Pagamento());
            pst.setString(3, formapagamento.getDinheiro());
            pst.setString(4, formapagamento.getDia());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fechar();
        }
    }

    public Vector<FormaPagamento> buscar(String Codigo) {
        conectar();
        Vector<FormaPagamento> resultados = new Vector<FormaPagamento>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM tbformapagamento WHERE Codigo LIKE '"
                    + Codigo + "%';");
            while (rs.next()) {
                FormaPagamento temp = new FormaPagamento(1, "Forma_Pagamento", "Dinheiro", "Dia");
                temp.setIdVenda(rs.getInt("IdVenda"));
                temp.setForma_Pagamento(rs.getString("Forma_Pagamento"));
                temp.setDinheiro(rs.getString("Dinheiro"));
                temp.setDia(rs.getString("Dia"));
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar formapagamento", e.getMessage());
            return null;
        }

    }

    public void insere(FormaPagamento formapagamento) {
        conectar();
        try {
            String sql = "INSERT INTO tbvendas VALUES (?,?,?,?) WHERE IdVenda = formapagamento.getIdVenda();";

            pst.setInt(1, formapagamento.getIdVenda());
            pst.setString(2, formapagamento.getForma_Pagamento());
            pst.setString(3, formapagamento.getDinheiro());
            pst.setString(4, formapagamento.getDia());
            System.out.println("Inserida!");
        } catch (SQLException e) {
            imprimeErro("Erro ao inserir FormaPagamento", e.getMessage());
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
