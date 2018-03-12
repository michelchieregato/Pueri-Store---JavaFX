/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.ClienteDao;
import DAO.ItensVendaDAO;
import DAO.ProdutoDao;
import DAO.VendaDao;
import Models.Cliente;
import Models.ItensVenda;
import Models.Produto;
import Models.Venda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.function.Predicate;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class TelaVendaController implements Initializable {

    // Listas Ligadas
    private ObservableList<Cliente> list;
    private ObservableList<Produto> list_produto;
    private ObservableList<ItensVenda> list_itens_venda;
    
    // Variaveis da venda
    
    private Venda venda;
    private Produto produto_atual; 
    private Cliente cliente;
    private float total_venda = 0;
    private String unidade = "Verbo Divino";
    private String vendedor = "Michel";

    @FXML
    private JFXTextField txtBuscaCliente;

    @FXML
    private JFXButton btnAddCliente;

    @FXML
    private JFXTreeTableView<Cliente> tblCliente;

    @FXML
    private TreeTableColumn<Cliente, String> colId;

    @FXML
    private TreeTableColumn<Cliente, String> colCpf;

    @FXML
    private TreeTableColumn<Cliente, String> colNome;

    @FXML
    private JFXTreeTableView<Produto> tblProdutos;

    @FXML
    private TreeTableColumn<Produto, String> colIdProduto;

    @FXML
    private TreeTableColumn<Produto, String> colDescricaoProduto;

    @FXML
    private TreeTableColumn<Produto, String> colTamanhoProduto;

    @FXML
    private TreeTableColumn<Produto, String> colPrecoProduto;

    @FXML
    private TreeTableColumn<Produto, String> colEstoqueProduto;

    @FXML
    private JFXTextField txtProduto;

    @FXML
    private Label lblProduto;

    @FXML
    private Label lblPreco;

    @FXML
    private Label lblCodigo;
    
    @FXML
    private JFXButton btnAddCarrinho;
    
    @FXML
    private JFXTreeTableView<ItensVenda> tblVenda;

    @FXML
    private TreeTableColumn<ItensVenda, String> colDescricaoVenda;

    @FXML
    private TreeTableColumn<ItensVenda, String> colQuantidadeVenda;

    @FXML
    private TreeTableColumn<ItensVenda, String> colPrecoVenda;
    
    @FXML
    private TreeTableColumn<ItensVenda, String> colTamanhoVenda;
    
    @FXML
    private Label lblCliente;

    @FXML
    private Label lblTotal;
    
    @FXML
    private Spinner<Integer> spinnerQnt;
    
    @FXML
    private JFXButton btnFinalizaVenda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicializa tabelas
        inicializaTabelaCliente();
        inicializaTabelaProduto();
        inicializaTabelaVenda();
        
        spinnerQnt.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1, 1));
        
        txtBuscaCliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tblCliente.setPredicate(new Predicate<TreeItem<Cliente>>() {
                    @Override
                    public boolean test(TreeItem<Cliente> modelTreeItem) {
                        return modelTreeItem.getValue().nome.getValue().toLowerCase().contains(newValue) | modelTreeItem.getValue().cpf.getValue().contains(newValue);
                    }
                });
            }
        });

        txtProduto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tblProdutos.setPredicate(new Predicate<TreeItem<Produto>>() {
                    @Override
                    public boolean test(TreeItem<Produto> modelTreeItem) {
                        return modelTreeItem.getValue().descricao.getValue().toLowerCase().contains(newValue.toLowerCase()) | Integer.toString(modelTreeItem.getValue().codigo.getValue()).contains(newValue);
                    }
                });
            }
        });
        
    }
    
    @FXML
    void handleTblCliente(MouseEvent event) {
        cliente = tblCliente.getSelectionModel().getSelectedItem().getValue();
        lblCliente.setText(cliente.getNome());
    }
    
    @FXML
    void handleTblProduto(MouseEvent event) {
        produto_atual = tblProdutos.getSelectionModel().getSelectedItem().getValue();
        atualiza_linhas(produto_atual);
    }
    
    @FXML
    void handleAddCarrinho(ActionEvent event) {
        
        // Cria novo item de venda
        ItensVenda item_venda = new ItensVenda(544, Integer.toString(spinnerQnt.getValue()), produto_atual.getCodigo(), Float.toString(Float.parseFloat(produto_atual.getPrecoVenda()) * spinnerQnt.getValue()));
        
        // Adiciona o produto a ele
        item_venda.produto = produto_atual;
        
        // Adiciona na lista ligada e atualiza valor total da compra
        list_itens_venda.add(item_venda);
        
        total_venda += Float.parseFloat(item_venda.getTotal());
        lblTotal.setText("R$ " + Float.toString(total_venda));
        
        while(spinnerQnt.getValue() > 1)
            spinnerQnt.decrement();
        
        txtProduto.setText("");
    }
    
    @FXML
    void handleFinalizaVenda(ActionEvent event) {
        
        VendaDao venda_dao = new VendaDao();
        venda_dao.insere_vazio(venda);
        venda = venda_dao.busca_ultimo();
        
        ItensVendaDAO itens_venda_dao = new ItensVendaDAO();
        venda.setPreco(Float.toString(total_venda));
        venda.setUnidade(unidade);
        venda.setVendedor(vendedor);
        
        venda_dao.atualizar(venda);
        
        try {
            list_itens_venda.forEach(c -> {
                    c.setIdVenda(venda.getIdVenda());
                    itens_venda_dao.insere(c);
                });
        } catch (Exception e) {
            showAlert(2, "Erro", "Não foi possível adicionar cliente: \n\t" + e.toString());
        }
        showAlert(0, "Sucesso!", "Venda concluída com sucesso!");
        
    }
    
    

    @FXML
    void handleAddCliente(ActionEvent event) {
    }
    
    
    void inicializaTabelaCliente() {
        colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Cliente, String> param) {
                return param.getValue().getValue().nome;
            }
        });
        colCpf.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Cliente, String> param) {
                return param.getValue().getValue().cpf;
            }
        });
        colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Cliente, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().id.get()));
            }
        });

        list = FXCollections.observableArrayList();

        TreeItem<Cliente> root = new RecursiveTreeItem<Cliente>(list, RecursiveTreeObject::getChildren);
        tblCliente.setRoot(root);
        tblCliente.setShowRoot(false);

        try {

            ClienteDao cliente = new ClienteDao();
            Vector<Cliente> lista_de_cliente = cliente.buscarTodos();

            lista_de_cliente.forEach(c -> {
                list.addAll(c);
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Ta imprimindo o estoque da verbo apenas!!! Depois mudar
    private void inicializaTabelaProduto() {
        // Id
        colIdProduto.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produto, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().codigo.get()));
            }
        });
        // Descricao
        colDescricaoProduto.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produto, String> param) {
                return param.getValue().getValue().descricao;
            }
        });
        // Preco Produto
        colPrecoProduto.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produto, String> param) {
                return param.getValue().getValue().preco_venda;
            }
        });
        // Preco Produto
        colTamanhoProduto.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produto, String> param) {
                return param.getValue().getValue().tamanho;
            }
        });
        // Preco Produto
        colEstoqueProduto.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produto, String> param) {
                return param.getValue().getValue().estoque_verbo;
                // Ta imprimindo o estoque da verbo apenas!!! Depois mudar
            }
        });

        list_produto = FXCollections.observableArrayList();
        TreeItem<Produto> root_produto = new RecursiveTreeItem<Produto>(list_produto, RecursiveTreeObject::getChildren);
        tblProdutos.setRoot(root_produto);
        tblProdutos.setShowRoot(false);

        try {

            ProdutoDao produto = new ProdutoDao();
            Vector<Produto> lista_de_produtos = produto.buscarTodos();

            lista_de_produtos.forEach(c -> {
                list_produto.addAll(c);
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void inicializaTabelaVenda() {
        // Descricao
        colDescricaoVenda.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItensVenda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItensVenda, String> param) {
                return param.getValue().getValue().produto.descricao;
            }
        });
        // Tamanho
        colTamanhoVenda.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItensVenda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItensVenda, String> param) {
                return param.getValue().getValue().produto.tamanho;
            }
        });
        // Preco Produto
        colQuantidadeVenda.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItensVenda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItensVenda, String> param) {
                return param.getValue().getValue().Quantidade;
            }
        });
        // Preco Produto
        colPrecoVenda.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItensVenda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItensVenda, String> param) {
                return param.getValue().getValue().Total;
            }
        });

        list_itens_venda = FXCollections.observableArrayList();
        TreeItem<ItensVenda> root_itens = new RecursiveTreeItem<ItensVenda>(list_itens_venda, RecursiveTreeObject::getChildren);
        tblVenda.setRoot(root_itens);
        tblVenda.setShowRoot(false);
    }

    private void atualiza_linhas(Produto produto) {
        lblProduto.setText(produto.getDescricao() + ' ' + produto.getTamanho());
        lblCodigo.setText(Integer.toString(produto.getCodigo()));
        lblPreco.setText(produto.getPrecoVenda());
    }
    
    private void showAlert(int tipo, String header, String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TelaAlerta.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            FadeTransition scale = new  FadeTransition(Duration.seconds(1), root1);
            scale.setFromValue(0);
            scale.setToValue(1);
            scale.play();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.DECORATED);
            TelaAlertaController telaAlertaController = fxmlLoader.getController();
            telaAlertaController.getData(tipo, header, message);
            stage.show();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
