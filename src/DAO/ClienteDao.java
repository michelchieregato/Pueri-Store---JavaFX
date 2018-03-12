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
import Models.Cliente;
import java.sql.PreparedStatement;
  
public class ClienteDao {  
   // Configura essas variáveis de acordo com o seu banco  
   private Connection conexao;  
   private Statement comando;  
   
   private PreparedStatement pst = null;
  
   public void apagar(String id) {  
      conectar();
      try {  
         comando.executeUpdate("DELETE FROM tbcliente WHERE Id = '" + id  
                     + "';");  
      } catch (SQLException e) {  
         imprimeErro("Erro ao apagar clientes", e.getMessage());  
      } finally {  
         fechar();  
      }  
   }  
  
   public Vector<Cliente> buscarTodos() {  
      conectar();  
      Vector<Cliente> resultados = new Vector<Cliente>();  
      ResultSet rs;  
      try {  
        pst = conexao.prepareStatement("select * from tbcliente");
        rs = pst.executeQuery();  
        while (rs.next()) {  
            Cliente temp = new Cliente(1, "Nome", "Telefone", "Email", "Cpf", "Cep", "Endereco");  
           // pega todos os atributos da cliente  
            temp.setId(rs.getInt("Id"));  
            temp.setNome(rs.getString("Nome"));  
            temp.setTelefone(rs.getString("Telefone"));  
            temp.setEmail(rs.getString("Email")); 
            temp.setCpf(rs.getString("Cpf"));
            temp.setCep(rs.getString("CEP"));  
            temp.setEndereco("Endereco");  
            resultados.add(temp);  
        }  
        return resultados;  
      } catch (Exception e) {  
         JOptionPane.showMessageDialog(null, e);
         imprimeErro("Erro ao buscar clientes", e.getMessage());  
         return null;  
      }  
   }  
  
   public void atualizar(Cliente cliente) {  
      conectar();  
      String com = "UPDATE tbcliente SET Nome = '" + cliente.getNome()  
            + "', Telefone =" + cliente.getTelefone()+ ", Email = '"  
            + cliente.getEmail()+ "', Cpf ='" + cliente.getCpf() +  ", Endereco = '"  
            + cliente.getEndereco() + "' WHERE  rg = '" + cliente.getId() + "';";  
      System.out.println("Atualizada!");  
      try {  
         comando.executeUpdate(com);  
      } catch (SQLException e) {  
         e.printStackTrace();  
      } finally {  
         fechar();  
      }  
   }  
  
   public Vector<Cliente> buscar(String rg) {  
      conectar();  
      Vector<Cliente> resultados = new Vector<Cliente>();  
      ResultSet rs;  
      try {  
         rs = comando.executeQuery("SELECT * FROM tbcliente WHERE rg LIKE '"  
               + rg + "%';");  
         while (rs.next()) {  
            Cliente temp = new Cliente(1, "Nome", "Telefone", "Email", "Cpf", "Cep", "Endereco");  
            // pega todos os atributos da cliente  
            temp.setId(rs.getInt("Id"));  
            temp.setNome(rs.getString("Nome"));  
            temp.setCpf(rs.getString("Cpf"));
            temp.setTelefone(rs.getString("Telefone"));  
            temp.setEmail(rs.getString("Email"));  
            temp.setCep(rs.getString("CEP"));  
            temp.setEndereco("Endereco");  
            resultados.add(temp);  
         }  
         return resultados;  
      } catch (SQLException e) {  
         imprimeErro("Erro ao buscar cliente", e.getMessage());  
         return null;  
      }  
  
   }  
  
   public void insere(Cliente cliente){  
      conectar();  
      try {  
         comando.executeUpdate("INSERT INTO tbcliente VALUES('"  
               + cliente.getNome()+ "', '" + cliente.getTelefone()+ "',"  
               + cliente.getEmail()+ ",'" + cliente.getCpf()+ "','"  
               + cliente.getCep()+ "','"  
               + cliente.getEndereco()+ "')");  
         System.out.println("Inserida!");  
      } catch (SQLException e) {  
         imprimeErro("Erro ao inserir Cliente", e.getMessage());  
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
