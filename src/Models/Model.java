/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author miche
 */
public class Model extends RecursiveTreeObject<Model>{
    
    public StringProperty nome,cpf, telefone, endereco;
    
    public Model (String nome, String cpf, String endereco, String telefone) {
        this.nome = new SimpleStringProperty(nome);
        this.telefone = new SimpleStringProperty(telefone);
        this.cpf = new SimpleStringProperty(cpf);
        this.endereco = new SimpleStringProperty(endereco);
    }

    public StringProperty getNome() {
        return nome;
    }

    public void setNome(StringProperty nome) {
        this.nome = nome;
    }

    public StringProperty getCpf() {
        return cpf;
    }

    public void setCpf(StringProperty cpf) {
        this.cpf = cpf;
    }

    public StringProperty getTelefone() {
        return telefone;
    }

    public void setTelefone(StringProperty telefone) {
        this.telefone = telefone;
    }

    public StringProperty getEndereco() {
        return endereco;
    }

    public void setEndereco(StringProperty endereco) {
        this.endereco = endereco;
    }
    
    
    
    
}
