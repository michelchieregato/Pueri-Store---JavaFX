/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.Conexao;
import Models.TelaLogin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class TelaLoginController implements Initializable {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

      @FXML
    private JFXTextField txtLogin;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    private Label lblConexao;
    
    @FXML
    private JFXComboBox<String> slctUnidade;
    
    @FXML
    private JFXButton btnLogin;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexao = Conexao.conector();
        
        slctUnidade.getItems().removeAll(slctUnidade.getItems());
        slctUnidade.getItems().addAll("Aclimacao","Aldeia da Serra","Itaim","Verbo Divino");
        slctUnidade.getSelectionModel().select("Verbo Divino");


        backgroundAnimation();
        
        
        if (conexao != null){
            lblConexao.setText("Você está conectado!");
            lblConexao.setStyle("-");
            lblConexao.setTextFill(Paint.valueOf("#09f70b"));
        }
    }
    
    @FXML
    void handleBtnLogin(ActionEvent event) {
        logar();
    }

    private void backgroundAnimation() {

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(3),pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(3),pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                FadeTransition fadeTransition2=new FadeTransition(Duration.seconds(3),pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                   FadeTransition fadeTransition0 =new FadeTransition(Duration.seconds(3),pane2);
                    fadeTransition0.setToValue(1);
                    fadeTransition0.setFromValue(0);
                    fadeTransition0.play();

                    fadeTransition0.setOnFinished(event3 -> {

                        FadeTransition fadeTransition11 =new FadeTransition(Duration.seconds(3),pane3);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {

                            FadeTransition fadeTransition22 =new FadeTransition(Duration.seconds(3),pane4);
                            fadeTransition22.setToValue(1);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                backgroundAnimation();
                            });

                        });

                    });

                });
            });

        });

    }
    
    
    
    private void logar(){
        String sql = "select * from tblogin where login=? and senha=?";
        try {
            //Consultam o banco de dados, e comparam com o digitado pelo usuário
            //O ? é substituído pelo conteudo das variáveis
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtLogin.getText());
            pst.setString(2, txtSenha.getText());
            //Executando a query
            rs = pst.executeQuery();
            if (rs.next()){
                //O  campo abaixo obtém o conteúdo do campo Perfil (tabela usuários)
                String vendedor = rs.getString(2);
                String perfil = rs.getString(5);
                if (perfil.equals("Administrador")){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TelaAdministrador.fxml"));
                    AnchorPane root1 = (AnchorPane) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    TelaAdministradorController telaAdmController = fxmlLoader.getController();
                    telaAdmController.getData(perfil, slctUnidade.getValue());
                    stage.setMaximized(true);
                    stage.show();
                    Stage this_stage = (Stage) btnLogin.getScene().getWindow(); 
                    this_stage.close();
                    conexao.close(); 
                } else {
//                    prevStage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TelaAdministrador.fxml"));
                    AnchorPane root1 = (AnchorPane) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));  
                    stage.show();
//                    TelaQualUnidade telaQual = new TelaQualUnidade(vendedor, false);
//                    telaQual.setVisible(true);
//                    this.dispose();
                    
                    conexao.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usúarío ou senha incorretos");
                txtLogin.setText("");
                txtSenha.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados");
        }
    }
    
}
