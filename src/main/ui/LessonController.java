package main.ui;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.entities.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class LessonController implements Initializable {
    public TextField title;
    public TextField category;
    public TextField keywords;
    public ImageView image1;
    public ImageView image2;
    public Label image1Label;
    public Label image2Label;
    public AnchorPane images;
    public WebView description;
    public VBox files;
    public VBox whole;
    public TextField author;
    public Button delete;
    public static Problem problemToDelete;
    public static Problem problemToEdit;
    private Problem problem;

    private HostServices hostServices ;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices ;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            problem = ProblemFinder.getInstance().findById(Controller.chosenProblem);
            title.setText(problem.getTitle());
            try {
                category.setText(CategoryFinder.getInstance().findById(problem.getCategory_id()).getTitle());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            keywords.setText("");
            List<Key_word> keyWords = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
            for (Key_word k: keyWords) {
                if(keywords.getText().equals("")) keywords.setText(k.getTitle());
                else keywords.setText(keywords.getText() + ", " + k.getTitle());
            }
            if(problem.getImage1() != null){
                String path1 = "file" + problem.getImage1().replace('\\', '/').substring(1);
                image1.setImage(new Image(path1));
                image1Label.setOnMouseClicked(e -> showImage(path1));
            } else {
                image1Label.setVisible(false);
            }
            if(problem.getImage2() != null){
                String path2 = "file" + problem.getImage2().replace('\\', '/').substring(1);
                image2.setImage(new Image(path2));
                image2Label.setOnMouseClicked(e -> showImage(path2));
            } else {
                image2Label.setVisible(false);
            }
            if (problem.getImage1() == null && problem.getImage1() == null) {
                whole.getChildren().remove(images);
            }
            String content = problem.getDescription().replace("contenteditable=\"true\"", "contenteditable=\"false\"");
            description.getEngine().loadContent(content);
            author.setText(problem.getUser_name());

            List<String> savedFiles = new ArrayList<>();
            savedFiles.addAll(Arrays.asList(problem.getPath().split(";")));
            for (String file : savedFiles) {
                if (file.isEmpty()) {
                    continue;
                }
                files.getChildren().add(showFile(file));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            problemToEdit = problem;
            URL fxmlLocation = getClass().getResource("../fxml/new_lesson.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            loader.setController(new EditLessonController());
            Parent root = loader.load();
            ((Stage) title.getScene().getWindow()).setScene(new Scene(root));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        try {
            problemToDelete = problem;
            URL fxmlLocation = getClass().getResource("../fxml/password.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            closeWindow();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML void showEditLog() {
        try {
            problemToEdit = problem;
            URL fxmlLocation = getClass().getResource("../fxml/edit_log.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.setTitle("Edit Log");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }

    public TextField showFile(String fileName) {
        String[] name = fileName.split("\\\\");
        TextField file = new TextField(name[name.length-1]);
        file.setEditable(false);
        file.setPrefSize(750, 60);
        file.setLayoutY(20);
        file.setFont(Font.font("Calibri", FontPosture.ITALIC, 25));
        file.setCursor(Cursor.HAND);
        file.setStyle("-fx-text-fill: blue;");

        file.setOnMouseClicked(e -> {
            hostServices.showDocument(fileName);
        });
        file.setOnMouseEntered(e -> {
            file.setText(fileName);
        });
        file.setOnMouseExited(e -> {
            file.setText(name[name.length-1]);
        });
        return file;
    }

    public void showImage(String imgPath) {
        try {
            Stage stage = new Stage();
            ImageView image = new ImageView(imgPath);
            stage.setScene(new Scene(new ScrollPane(image)));
            stage.setTitle(problem.getTitle());
            stage.show();
        } catch(Exception exp) {
            exp.printStackTrace();
        }
    }
}
