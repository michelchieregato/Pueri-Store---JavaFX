/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author miche
 */
public class FormaPagamento {

    SimpleIntegerProperty IdVenda;
    SimpleStringProperty Forma_Pagamento;
    SimpleStringProperty Dinheiro;
    SimpleStringProperty Dia;

    public FormaPagamento(int IdVenda, String Forma_Pagamento, String Dinheiro, String Dia) {
        this.IdVenda = new SimpleIntegerProperty(IdVenda);
        this.Forma_Pagamento = new SimpleStringProperty(Forma_Pagamento);
        this.Dinheiro = new SimpleStringProperty(Dinheiro);
        this.Dia = new SimpleStringProperty(Dia);
    }

    public int getIdVenda() {
        return IdVenda.get();
    }

    public void setIdVenda(int _IdVenda) {
        IdVenda.set(_IdVenda);
    }

    public String getForma_Pagamento() {
        return Forma_Pagamento.get();
    }

    public void setForma_Pagamento(String _Forma_Pagamento) {
        Forma_Pagamento.set(_Forma_Pagamento);
    }

    public String getDinheiro() {
        return Dinheiro.get();
    }

    public void setDinheiro(String _Dinheiro) {
        Dinheiro.set(_Dinheiro);
    }

    public String getDia() {
        return Dia.get();
    }

    public void setDia(String _Dia) {
        Dia.set(_Dia);
    }

}
