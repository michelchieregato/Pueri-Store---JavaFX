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
public class Produto extends RecursiveTreeObject<Produto>{
    
    public SimpleIntegerProperty codigo;
    public SimpleStringProperty descricao;
    public SimpleStringProperty sigla;
    public SimpleStringProperty preco_custo;
    public SimpleStringProperty preco_venda;
    public SimpleStringProperty tamanho;
    public SimpleStringProperty estoque_central;
    public SimpleStringProperty estoque_aldeia;
    public SimpleStringProperty estoque_aclimacao;
    public SimpleStringProperty estoque_itaim;
    public SimpleStringProperty estoque_verbo;

    public Produto(int codigo, String descricao, String sigla, String preco_custo, String preco_venda, String tamanho, String estoque_central, String estoque_aldeia, String estoque_aclimacao, String estoque_itaim, String estoque_verbo) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.sigla = new SimpleStringProperty(sigla);;
        this.preco_custo = new SimpleStringProperty(preco_custo);
        this.preco_venda = new SimpleStringProperty(preco_venda);
        this.tamanho = new SimpleStringProperty(tamanho);
        this.estoque_central = new SimpleStringProperty(estoque_central);
        this.estoque_aldeia = new SimpleStringProperty(estoque_aldeia);
        this.estoque_aclimacao = new SimpleStringProperty(estoque_aclimacao);
        this.estoque_itaim = new SimpleStringProperty(estoque_itaim);
        this.estoque_verbo = new SimpleStringProperty(estoque_verbo);
    }

  
    
    
    public int getCodigo() {
        return codigo.get();
        }

    public void setCodigo(int _Codigo) {
        codigo.set(_Codigo);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String _descricao) {
        descricao.set(_descricao);
    }

    public String getSigla() {
        return sigla.get();
    }

    public void setSigla(String _sigla) {
        sigla.set(_sigla);
    }

    public String getPrecoCusto() {
        return preco_custo.get();
    }

    public void setPrecoCusto(String _preco_custo) {
        preco_custo.set(_preco_custo);
    }

    public String getPrecoVenda() {
        return preco_venda.get();
    }

    public void setPrecoVenda(String _preco_venda) {
        preco_venda.set(_preco_venda);
    }

    public String getTamanho() {
        return tamanho.get();
    }

    public void setTamanho(String _tamanho) {
        tamanho.set(_tamanho);
    }

    public String getEstoqueCentral() {
        return estoque_central.get();
    }

    public void setEstoqueCentral(String _estoque_central) {
        estoque_central.set(_estoque_central);
    }    
    
    public String getEstoqueVerbo() {
        return estoque_verbo.get();
    }

    public void setEstoqueVerbo(String _estoque_verbo) {
        estoque_verbo.set(_estoque_verbo);
    }    
    
    public String getEstoqueItaim() {
        return estoque_itaim.get();
    }
    
    public void setEstoqueItaim(String _estoque_itaim) {
        estoque_itaim.set(_estoque_itaim);
    }    
    public String getEstoqueAldeia() {
        return estoque_aldeia.get();
    }

    public void setEstoqueAldeia(String _estoque_aldeia) {
        estoque_aldeia.set(_estoque_aldeia);
    }    
    public String getEstoqueAclimacao() {
        return estoque_aclimacao.get();
    }

    public void setEstoqueAclimacao(String _estoque_aclimacao) {
        estoque_aclimacao.set(_estoque_aclimacao);
    }    

    
    
}
