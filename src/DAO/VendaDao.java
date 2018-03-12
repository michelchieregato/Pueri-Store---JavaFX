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

import Models.Venda;
import DAO.Conexao;
import Models.Venda;
import controllers.TelaAdministradorController;
import controllers.TelaAlertaController;
import java.sql.PreparedStatement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VendaDao {
    // Configura essas variáveis de acordo com o seu banco  

    private Connection conexao;
    private Statement comando;

    private PreparedStatement pst = null;

    public void apagar(String IdVenda) {
        conectar();
        try {
            comando.executeUpdate("DELETE FROM tbvenda WHERE IdVenda = '" + IdVenda
                    + "';");
        } catch (SQLException e) {
            imprimeErro("Erro ao apagar venda", e.getMessage());
        } finally {
            fechar();
        }
    }

    public Vector<Venda> buscarTodos() {
        conectar();
        Vector<Venda> resultados = new Vector<Venda>();
        ResultSet rs;
        try {
            pst = conexao.prepareStatement("select * from tbvenda");
            rs = pst.executeQuery();
            while (rs.next()) {
                Venda temp = new Venda(1, "Dia", "Preco", "idcliente", "Vendedor", "Unidade");
                // pega todos os atributos da venda  
                temp.setIdVenda(rs.getInt("IdVenda"));
                temp.setDia(rs.getString("Dia"));
                temp.setPreco(rs.getString("Preco"));
                temp.setidcliente(rs.getString("idcliente"));
                temp.setVendedor(rs.getString("Vendedor"));
                temp.setUnidade(rs.getString("Unidade"));
                resultados.add(temp);
            }
            return resultados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            imprimeErro("Erro ao buscar clientes", e.getMessage());
            return null;
        }
    }

    public void atualizar(Venda venda) {
        conectar();
        String sql = "update tbvenda set Dia=?, Preco=?, idcliente=?, Vendedor=?, Unidade=? WHERE IdVenda=?";
        System.out.println("Atualizada!");
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, venda.getDia());
            pst.setString(2, venda.getPreco());
            pst.setString(3, venda.getidcliente());
            pst.setString(4, venda.getVendedor());
            pst.setString(5, venda.getUnidade());
            pst.setInt(6, venda.getIdVenda());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fechar();
        }
    }

    public Vector<Venda> buscar(String Codigo) {
        conectar();
        Vector<Venda> resultados = new Vector<Venda>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM tbvenda WHERE Codigo LIKE '"
                    + Codigo + "%';");
            while (rs.next()) {
                Venda temp = new Venda(1, "Dia", "Preco", "idcliente", "Vendedor", "Unidade");
                // pega todos os atributos da venda  
                temp.setIdVenda(rs.getInt("IdVenda"));
                temp.setDia(rs.getString("Dia"));
                temp.setPreco(rs.getString("Preco"));
                temp.setidcliente(rs.getString("idcliente"));
                temp.setVendedor(rs.getString("Vendedor"));
                temp.setUnidade(rs.getString("Unidade"));
                resultados.add(temp);
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar venda", e.getMessage());
            return null;
        }

    }

    public void insere(Venda venda) {
        conectar();
        try {
            String sql = "INSERT INTO tbvenda VALUES (?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, venda.getIdVenda());
            pst.setString(2, venda.getDia());
            pst.setString(3, venda.getPreco());
            pst.setString(4, venda.getidcliente());
            pst.setString(5, venda.getVendedor());
            pst.setString(6, venda.getUnidade());
            pst.executeUpdate();
            System.out.println("Inserida!");
        } catch (SQLException e) {
            imprimeErro("Erro ao inserir Venda", e.getMessage());
        } finally {
            fechar();
        }
    }

    public void insere_vazio(Venda venda) {
        conectar();
        try {
            String sql = "INSERT INTO tbvenda(Preco) VALUES (0);";
            comando.executeUpdate(sql);
            System.out.println("Inserida!");
        } catch (SQLException e) {
            imprimeErro("Erro ao inserir Venda", e.getMessage());
        } finally {
            System.out.println("Oi1");
            fechar();
        }
    }

    public Venda busca_ultimo() {
        conectar();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM tbvenda ORDER BY IdVenda DESC LIMIT 1;");
            Venda temp = new Venda();
            while (rs.next()) {
                // pega todos os atributos da venda  
                System.out.println(rs.getInt("IdVenda"));
                temp.setIdVenda(rs.getInt("IdVenda"));
                temp.setDia(rs.getString("Dia"));
//                temp.setPreco(rs.getString("Preco"));
//                temp.setidcliente(rs.getString("idcliente"));
//                temp.setVendedor(rs.getString("Vendedor"));
//                temp.setUnidade(rs.getString("Unidade"));
            }
            return temp;
        } catch (SQLException e) {
            imprimeErro("Erro ao inserir Venda", e.getMessage());
            return null;
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
