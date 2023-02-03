package main.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.Category;
import main.entities.CategoryFinder;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;

public class DeleteCategoryController {
    public TextField categoryToDelete;
    public Button closeButton;

    public void apply() throws SQLException, IOException {
        if(categoryToDelete.getText().equals("")){
            closeWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing keyword");
            alert.setHeaderText("Enter keyword please!");
            alert.showAndWait();
            URL fxmlLocation = getClass().getResource("../fxml/delete_keyword.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            return;
        }
        Category category = CategoryFinder.getInstance().findByTitle(categoryToDelete.getText().toLowerCase(Locale.ROOT));
        try{
            category.delete();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing category");
            alert.setHeaderText("Category successful removed, reopen application, please!");
            alert.showAndWait();
            closeWindow();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Removing category");
            alert.setHeaderText("Category '" + categoryToDelete.getText() + "' doesnt exists.");
            alert.showAndWait();
            closeWindow();
            URL fxmlLocation = getClass().getResource("../fxml/delete_category.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
