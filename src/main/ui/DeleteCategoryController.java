package main.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entities.Category;
import main.entities.CategoryFinder;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteCategoryController implements Initializable {
    public ChoiceBox<String> categoryToDelete;
    public Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (Category c : CategoryFinder.getInstance().findAll()){
                categoryToDelete.getItems().add(c.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void apply() throws SQLException, IOException {
        if(categoryToDelete.getValue() == null){
            closeWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing category");
            alert.setHeaderText("Choose category, please!");
            alert.showAndWait();
            URL fxmlLocation = getClass().getResource("../fxml/delete_category.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            return;
        }
        Category category = CategoryFinder.getInstance().findByTitle(categoryToDelete.getValue());
        try{
            category.delete();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removing category");
            alert.setHeaderText("Category successfully removed, click 'Reload page' button, please!");
            alert.showAndWait();
            closeWindow();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Removing category");
            alert.setHeaderText("Category '" + categoryToDelete.getValue() + "' doesnt exists.");
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
