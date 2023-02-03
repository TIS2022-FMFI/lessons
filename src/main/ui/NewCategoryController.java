package main.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.Category;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;

public class NewCategoryController {
    public TextField categoryText;
    public Button closeBtn;

    public void apply() throws SQLException, IOException {
        if(categoryText.getText().equals("")){
            closeWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creating Category");
            alert.setHeaderText("Enter category please!");
            alert.showAndWait();
            URL fxmlLocation = getClass().getResource("../fxml/new_category.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            return;
        }
        Category category = new Category();
        category.setTitle(categoryText.getText().toLowerCase(Locale.ROOT));
        try{
            category.insert();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding category");
            alert.setHeaderText("Successfully added, please click 'Reload page' button!");
            alert.showAndWait();
            closeWindow();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Adding category");
            alert.setHeaderText("Adding new category '" + category.getTitle() + "' was unsuccessful.\nThis category already exists or check if you are not using diacritic");
            alert.showAndWait();
            closeWindow();
            URL fxmlLocation = getClass().getResource("../fxml/new_category.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    public void closeWindow(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
