package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class EditKeywordsController {
    public Button closeButton;
    public Button addButton;
    public Button deleteButton;

    /**
     * Opens window to create new keyword when button is clicked
     */
    public void newKeyword(){
        try {
            URL fxmlLocation = getClass().getResource("../fxml/new_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("New prime keyword");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens window to delete keyword when button is clicked
     */
    public void deleteKeyword(){
        try {
            URL fxmlLocation = getClass().getResource("../fxml/delete_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("Remove prime keyword");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * On close of window close window
     */
    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
