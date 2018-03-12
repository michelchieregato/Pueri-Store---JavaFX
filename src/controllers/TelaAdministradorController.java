/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class TelaAdministradorController implements Initializable {
    
    private String perfil;
    private String unidade;

    @FXML
    private JFXButton btnVenda;

    @FXML
    private JFXButton btnRelatorio;

    @FXML
    private JFXButton btnPesquisa;

    @FXML
    private Label lblTItulo;

    @FXML
    private JFXButton btnSangria;

    @FXML
    private AnchorPane opacityPane;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private ImageView drawerImage;

    @FXML
    private AnchorPane pane3;

    @FXML
    private AnchorPane pane4;

    @FXML
    private JFXButton btnSair;

    @FXML
    private JFXButton btnTransfere;

    @FXML
    private AnchorPane drawerPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        opacityPane.setVisible(false);
        
        System.out.println(unidade);
        
        lblTItulo.setText("Pueri Domus - " + unidade);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        Animation();


        drawerImage.setOnMouseClicked(event -> {


            opacityPane.setVisible(true);

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });

        opacityPane.setOnMouseClicked(event -> {



            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });


            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
        });

    }
    
    @FXML
    void handlebtnVenda(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TelaVenda.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setMaximized(true);
            stage.show();
         } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    
    public void getData(String perfil, String unidade) {
        this.perfil = perfil;
        this.unidade = unidade;
        lblTItulo.setText("Pueri Domus - " + unidade);
    }

    public  void  Animation(){


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

                    FadeTransition fadeTransition00=new FadeTransition(Duration.seconds(3),pane2);
                    fadeTransition00.setFromValue(0);
                    fadeTransition00.setToValue(1);
                    fadeTransition00.play();


                    fadeTransition00.setOnFinished(event3 -> {
                        FadeTransition fadeTransition11=new FadeTransition(Duration.seconds(3),pane3);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {
                            FadeTransition fadeTransition22=new FadeTransition(Duration.seconds(3),pane4);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.setToValue(1);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                Animation();
                            });
                        });


                    });
                });

            });




        });



    }

}
