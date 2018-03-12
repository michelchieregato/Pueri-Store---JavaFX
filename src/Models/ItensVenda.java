/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author miche
 */
public class ItensVenda extends RecursiveTreeObject<ItensVenda>{

    public SimpleIntegerProperty IdVenda;
    public SimpleStringProperty Quantidade;
    public SimpleIntegerProperty Idproduto;
    public SimpleStringProperty Total;
    public Produto produto;

    public ItensVenda(int IdVenda, String Quantidade, int Idproduto, String Total) {
        this.IdVenda = new SimpleIntegerProperty(IdVenda);
        this.Quantidade = new SimpleStringProperty(Quantidade);
        this.Idproduto = new SimpleIntegerProperty(Idproduto);
        this.Total = new SimpleStringProperty(Total);
    }

    public int getIdVenda() {
        return IdVenda.get();
    }

    public void setIdVenda(int _IdVenda) {
        IdVenda.set(_IdVenda);
    }

    public String getQuantidade() {
        return Quantidade.get();
    }

    public void setQuantidade(String _Quantidade) {
        Quantidade.set(_Quantidade);
    }

    public int getIdproduto() {
        return Idproduto.get();
    }

    public void setIdproduto(int _Idproduto) {
        Idproduto.set(_Idproduto);
    }

    public String getTotal() {
        return Total.get();
    }

    public void setTotal(String _Total) {
        Total.set(_Total);
    }
}
