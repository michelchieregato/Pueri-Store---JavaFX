/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaLogin extends Application {

    Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        mainWindow();
    }
    
    public void mainWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(TelaLogin.class.getResource("/fxml/TelaLogin.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            scene.getStylesheets().addAll(TelaLogin.class.getResource("/css/telalogin.css").toExternalForm());
            stage.setTitle("Login Screen");   
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            
            System.out.println("Erro.");
            System.out.println(e);
        }
    }
    
    
    public void TelaAdm(){
        try {
            FXMLLoader loader = new FXMLLoader(TelaLogin.class.getResource("/fxml/TelaAdministrador.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            scene.getStylesheets().addAll(TelaLogin.class.getResource("/css/telaadministrador.css").toExternalForm());
            stage.setTitle("Login Screen");
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            
            System.out.println("Erro.");
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
