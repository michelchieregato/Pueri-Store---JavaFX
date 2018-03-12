/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class TelaAlertaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView imgEmogi;

    @FXML
    private Label lblPrincipal;

    @FXML
    private Label lblTexto;
    
    @FXML
    private AnchorPane backgrondPane;
    
    @FXML
    private AnchorPane alertPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void getData(int tipo, String header, String message) {
        switch(tipo) {
            case 0:
                imgEmogi.setImage(new Image("/imagens/smile.png"));
                break;
            case 1:
                imgEmogi.setImage(new Image("/imagens/laugh.png"));
                break;
            case 2:
                imgEmogi.setImage(new Image("/imagens/sad.png"));
                break;
            default:
                imgEmogi.setImage(new Image("/imagens/smile.png"));
                break;
        }
        switch(tipo) {
            case 0:
                backgrondPane.setStyle("-fx-background-color: #00BF46");
                break;
            case 1:
                backgrondPane.setStyle("-fx-background-color: #FFBF46");
                break;
            case 2:
                backgrondPane.setStyle("-fx-background-color: #FF0000");
                break;
            default:
                backgrondPane.setStyle("-fx-background-color: #004541");
                break;
        }
        
        lblPrincipal.setText(header);
        lblTexto.setText(message);
        
    }
    
}
