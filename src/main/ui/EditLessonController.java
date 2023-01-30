package main.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.entities.*;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class EditLessonController extends NewLessonController {
    @FXML
    VBox whole;
    @FXML
    TextField action;

    TextField editorLabel = new TextField();
    TextField editor = new TextField();
    private final Problem problem = LessonController.problemToEdit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        action.setText("EDIT LESSON");
        newLessDelete.setText("CLOSE");

        // Load category
        String category;
        try {
            category = CategoryFinder.getInstance().findById(problem.getCategory_id()).getTitle();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Load keywords
        List<Key_word> key_words;
        try {
            key_words = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String keywordText = "";
        for (Key_word kw : key_words) keywordText += kw.getTitle() + ";";
        if (!keywordText.isEmpty()) keywordText = keywordText.substring(0, keywordText.length() - 1);

        // Load files
        savedFiles.addAll(Arrays.asList(problem.getPath().split(";")));
        for (String file : savedFiles) {
            files.getChildren().add(showFile(file));
        }

        // Make author uneditable
        newLessAutor.setEditable(false);

        // Add Editor name
        Integer numChildes = whole.getChildren().size();
        whole.getChildren().add(numChildes - 2, addEditor());
        Separator sep = new Separator();
        sep.setMaxWidth(400);
        sep.setPrefHeight(3);
        sep.setStyle("-fx-background-color: #000000;");
        whole.getChildren().add(numChildes - 2, sep);


        image1 = problem.getImage1();
        image2 = problem.getImage2();

        newLessTitle.setText(problem.getTitle());
        newLessCat.setValue(category);
        newLessKeyWord.setText(keywordText);
        imagePath1.setText(problem.getImage1());
        imagePath2.setText(problem.getImage2());
        newLessDesc.setText(problem.getDescription());
        newLessAutor.setText(problem.getUser_name());
    }

    @FXML
    private void saveButton() {
        problem.setTitle(newLessTitle.getText());

        // Category
        try {
            problem.setCategory_id(CategoryFinder.getInstance().findByTitle(newLessCat.getValue()).getCategory_id());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Keywords
        List<String> newKeywords = new ArrayList<>(Arrays.asList(newLessKeyWord.getText().split(";")));
        List<Key_word> usedKeywords;
        try {
            usedKeywords = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Key_word keyword : usedKeywords) {
            if (!newKeywords.contains(keyword.getTitle())) {
                try {
                    Key_word_problem tmp = KPFinder.getInstance().findByKeywordAndProblem(keyword.getKey_word_id(), problem.getProblem_id());
                    tmp.delete();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                newKeywords.remove(keyword.getTitle());
            }
        }
        for (String key : newKeywords) {
            if (key.isEmpty()) {
                continue;
            }
            Key_word kw;
            try {
                kw = KeywordFinder.getInstance().findByTitle(key);
                if (kw == null) {
                    kw = new Key_word();
                    kw.setTitle(key);
                    kw.insert();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Key_word_problem kwp = new Key_word_problem();
            kwp.setProblem_id(problem.getProblem_id());
            kwp.setKey_word_id(kw.getKey_word_id());
            try {
                kwp.insert();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Image1
        problem.setImage1(super.image1);

        // Image2
        problem.setImage2(super.image2);

        // Description
        problem.setDescription(newLessDesc.getText());

        // Files
        problem.setPath(String.join(";", super.savedFiles));

        // Last editor
        problem.setLast_editor(editor.getText());

        // Last edited at
        problem.setLast_edited_at(new java.sql.Date(new Date().getTime()));

        try {
            problem.update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HBox addEditor() {
        editorLabel.setText("Editor:");
        editorLabel.setPrefSize(180, 60);
        editorLabel.setAlignment(Pos.CENTER);
        editorLabel.setEditable(false);
        editorLabel.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC, 25));

        editor.setPrefSize(700, 60);
        editor.setPromptText("Enter your name");
        editor.setFont(Font.font("Calibri", FontPosture.ITALIC, 25));
        editor.setCursor(Cursor.TEXT);

        HBox output = new HBox(editorLabel, editor);
        output.setPrefSize(200, 60);
        return output;
    }
}
