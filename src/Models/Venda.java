/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Venda {

    public SimpleIntegerProperty IdVenda = new SimpleIntegerProperty();
    public SimpleStringProperty Dia = new SimpleStringProperty();
    public SimpleStringProperty Preco= new SimpleStringProperty(); 
    public SimpleStringProperty idcliente = new SimpleStringProperty();
    public SimpleStringProperty Vendedor = new SimpleStringProperty();
    public SimpleStringProperty Unidade= new SimpleStringProperty(); 

    public Venda(int IdVenda, String Dia, String Preco, String idcliente, String Vendedor, String Unidade) {
        this.IdVenda = new SimpleIntegerProperty(IdVenda);
        this.Dia = new SimpleStringProperty(Dia);
        this.Preco = new SimpleStringProperty(Preco);
        this.idcliente = new SimpleStringProperty(idcliente);
        this.Vendedor = new SimpleStringProperty(Vendedor);
        this.Unidade = new SimpleStringProperty(Unidade);
    }
    
    public Venda() {

    }

    public int getIdVenda() {
        return IdVenda.get();
    }

    public void setIdVenda(int _IdVenda) {
        IdVenda.set(_IdVenda);
    }

    public String getDia() {
        return Dia.get();
    }

    public void setDia(String _Dia) {
        Dia.set(_Dia);
    }

    public String getPreco() {
        return Preco.get();
    }

    public void setPreco(String _Preco) {
        Preco.set(_Preco);
    }

    public String getidcliente() {
        return idcliente.get();
    }

    public void setidcliente(String _idcliente) {
        idcliente.set(_idcliente);
    }

    public String getVendedor() {
        return Vendedor.get();
    }

    public void setVendedor(String _Vendedor) {
        Vendedor.set(_Vendedor);
    }

    public String getUnidade() {
        return Unidade.get();
    }

    public void setUnidade(String _Unidade) {
        Unidade.set(_Unidade);
    }

}
