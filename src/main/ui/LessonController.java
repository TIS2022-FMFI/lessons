package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.entities.KPFinder;
import main.entities.Key_word;
import main.entities.Problem;
import main.entities.ProblemFinder;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LessonController implements Initializable {
    public TextField title;
    public TextField keywords;
    public ImageView image1;
    public ImageView image2;
    public TextField description;
    public TextField files;
    public TextField author;
    public TextField last_editor;
    public Button delete;
    public static Problem problemToDelete;
    private Problem problem;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            problem = ProblemFinder.getInstance().findById(Controller.chosenProblem);
            title.setText(problem.getTitle());
            keywords.setText("");
            List<Key_word> keyWords = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
            for (Key_word k: keyWords) {
                if(keywords.getText().equals("")) keywords.setText(k.getTitle());
                else keywords.setText(keywords.getText() + ", " + k.getTitle());
            }
            if(problem.getImage1() != null){
                image1.setImage(new Image(problem.getImage1()));
            }
            if(problem.getImage2() != null){
                image2.setImage(new Image(problem.getImage2()));
            }
            description.setText(problem.getDescription());
            files.setText(problem.getPath());
            author.setText(problem.getUser_name());
            last_editor.setText(problem.getLast_editor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(){

    }

    public void delete(){
        try {
            problemToDelete = problem;
            URL fxmlLocation = getClass().getResource("../fxml/password.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Delete of " + problem.getTitle());
            stage.show();
            closeWindow();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }
}
