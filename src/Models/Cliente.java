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
public class Cliente extends RecursiveTreeObject<Cliente>{
    
    public SimpleIntegerProperty id;
    public SimpleStringProperty nome;
    SimpleStringProperty telefone;
    SimpleStringProperty email;
    public SimpleStringProperty cpf;
    SimpleStringProperty cep;
    SimpleStringProperty endereco;

    public Cliente(int id, String nome, String telefone, String email, String cpf, String cep, String endereco) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.telefone = new SimpleStringProperty(telefone);;
        this.email = new SimpleStringProperty(email);
        this.cpf = new SimpleStringProperty(cpf);;
        this.cep = new SimpleStringProperty(cep);;
        this.endereco = new SimpleStringProperty(endereco);;
    }

  
    
    
public int getId() {
        return id.get();
    }

    public void setId(int _id) {
        id.set(_id);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String _nome) {
        nome.set(_nome);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String _telefone) {
        telefone.set(_telefone);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String _email) {
        email.set(_email);
    }

    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String _cpf) {
        cpf.set(_cpf);
    }

    public String getCep() {
        return cep.get();
    }

    public void setCep(String _cep) {
        cep.set(_cep);
    }

    public String getEndereco() {
        return endereco.get();
    }

    public void setEndereco(String _endereco) {
        endereco.set(_endereco);
    }    

    
    
}
