/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Model;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import javax.swing.Action;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class TelaCadastroClienteController implements Initializable {
    
    ObservableList<Model> list;

     @FXML
    private JFXTreeTableView<Model> tabelaCliente;

    @FXML
    private TreeTableColumn<Model, String> colNome;

    @FXML
    private TreeTableColumn<Model, String> colCpf;

    @FXML
    private TreeTableColumn<Model, String> colTelefone;

    @FXML
    private TreeTableColumn<Model, String> colEndereco;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtCpf;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXComboBox<String> slctEndereco;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private Label lblCpf;

    @FXML
    private Label lblEndereco;

    @FXML
    private JFXTextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        slctEndereco.getItems().addAll("Rua Apiacas 961", "Rua dos bobos nº 0");
        
        colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().nome;
            }
        });
        colCpf.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().cpf;
            }
        });
        colTelefone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().telefone;
            }
        });
        colEndereco.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().endereco;
            }
        });
        
        list = FXCollections.observableArrayList();
        
        TreeItem<Model> root=new RecursiveTreeItem<Model>(list, RecursiveTreeObject ::getChildren);
        tabelaCliente.setRoot(root);
        tabelaCliente.setShowRoot(false);
        
        list.addAll(new Model("Adele", "40836474856","22","Rua Apiacas 961"));
        list.addAll(new Model("Beatriz", "9042790","22","Rua Apiacas 961"));
        list.addAll(new Model("Camila", "1231231123","22","Rua Apiacas 961"));
        
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tabelaCliente.setPredicate(new Predicate<TreeItem<Model>>(){
                    @Override
                    public boolean test(TreeItem<Model> modelTreeItem) {
                        return modelTreeItem.getValue().nome.getValue().toLowerCase().contains(newValue) |modelTreeItem.getValue().cpf.getValue().contains(newValue);
                    }
                });
            }
        });
        
    }
    @FXML
    void handleAdd(ActionEvent event) {
        list.addAll(new Model(txtNome.getText(), txtCpf.getText(),txtTelefone.getText(),slctEndereco.getValue()));
    }
    
    @FXML
    void  deleteAction(ActionEvent event){
        int index=tabelaCliente.getSelectionModel().getSelectedIndex();
        list.remove(index);
        clearFields();
    }
    
    public void clearFields(){
        txtNome.setText("");
        txtCpf.setText("");
        txtSearch.setText("");
        txtTelefone.setText("");
//        ageTF.setText("");
//        ageLB.setText("");
//        genderLB.setText("");
        slctEndereco.getSelectionModel().select("");
    }

    @FXML
    void clearAction(ActionEvent event){
        clearFields();
    }
}
