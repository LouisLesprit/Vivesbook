/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bags.Account;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.controller.*;

/**
 *
 * @author Katrien.Deleu
 */
public class VIVESbook extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
        laadLoginScherm();
        primaryStage.show();
    }

    public void laadLoginScherm() {

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
              "view/Login.fxml"));
            Parent root = loader.load();
            LoginController controller = (LoginController) loader.
              getController();

            // referentie naar hier bewaren in de controller
            controller.setMainApp(this);

            Scene scene = new Scene(root);
            stage.setTitle("VIVESbook");
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("!!! - " + e.getMessage());

        }

    }

    public void laadPosttoevoegenScherm() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
              "view/Posttoevoegen.fxml"));

            // controller instellen
            Parent root = loader.load();
            PosttoevoegenController controller = (PosttoevoegenController) loader.getController();

            // referentie naar hier bewaren in de controller
            controller.setMainApp(this);

            Scene scene = new Scene(root);
            stage.setTitle("Post toevoegen");
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println("!!! - " + e.getMessage());
        }

    }

   
    public void laadPostsScherm() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
              "view/Post.fxml"));

            // controller ophalen
            Parent root = loader.load();
            PostController controller = (PostController) loader.getController();

            // referentie naar hier bewaren in de controller
            controller.setMainApp(this);

            Scene scene = new Scene(root);
            stage.setTitle("VIVESbook - overzicht posts");
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println("!!! - " + e.getMessage());
        }

    }
    
    public void laadAccounttoevoegenScherm(){
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
              "view/Accounttoevoegen.fxml"));

            // controller ophalen
            Parent root = loader.load();
            AccounttoevoegenController controller = (AccounttoevoegenController) loader.getController();
            
            // referentie naar hier bewaren in de controller
            controller.setMainApp(this);

            Scene scene = new Scene(root);
            stage.setTitle("VIVESbook - Account toevoegen");
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println("!!! - " + e.getMessage());
        }
    }
    
    public void laadHomeScherm(Account account){
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
              "view/Home.fxml"));
            
            // controller ophalen
            Parent root = loader.load();

            HomeController controller = (HomeController) loader.getController();
            
            // referentie naar hier bewaren in de controller
            controller.setMainApp(this);
            controller.setData(account);

            Scene scene = new Scene(root);
            stage.setTitle("VIVESbook - Home");
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println("!!! - " + e.getMessage());
        }
    }
   
    public static void main(String[] args) {
        launch(args);
    }

}
