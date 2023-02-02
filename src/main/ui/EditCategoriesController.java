package main.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class EditCategoriesController {
    public Button closeButton;
    public Button addButton;
    public Button deleteButton;

    public void newCategory(){
        try {
            URL fxmlLocation = getClass().getResource("../fxml/new_category.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("New category");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(){
        try {
            URL fxmlLocation = getClass().getResource("../fxml/delete_category.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("Remove category");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
