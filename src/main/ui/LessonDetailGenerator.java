package main.ui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import main.entities.KPFinder;
import main.entities.Key_word;
import main.entities.Problem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LessonDetailGenerator {
    private static final LessonDetailGenerator INSTANCE = new LessonDetailGenerator();

    public static LessonDetailGenerator getInstance() {
        return INSTANCE;
    }

    private LessonDetailGenerator() {
    }

    public HBox lessonDetail(Problem problem) throws SQLException {
        Text topic = new Text(75, 40, problem.getTitle());
        topic.setStyle("-fx-font: bold 18 Calibri;");
        topic.setTextAlignment(TextAlignment.CENTER);

        List<Key_word> key_words = KPFinder.getInstance().findByProblemId(problem.getProblem_id());
        String keywordText = "";
        for (Key_word kw : key_words) keywordText += kw.getTitle() + ", ";
        if (!keywordText.isEmpty()) keywordText = keywordText.substring(0, keywordText.length() - 2);
        Text keywords = new Text(50, 70, keywordText);
        keywords.setStyle("-fx-font: italic 16 Calibri;");
        keywords.setTextAlignment(TextAlignment.CENTER);

        VBox info = new VBox(topic, keywords);
        info.setMinSize(500, 100);

        Image image = new Image("lupa3.png");

        ImageView firstImage = new ImageView(image);
        firstImage.setFitHeight(150);
        firstImage.setFitWidth(150);
        firstImage.setPickOnBounds(true);
        firstImage.setPreserveRatio(true);
        HBox.setMargin(firstImage, new Insets(15));

        ImageView secondImage = new ImageView(image);
        secondImage.setFitHeight(150);
        secondImage.setFitWidth(150);
        secondImage.setPickOnBounds(true);
        secondImage.setPreserveRatio(true);
        HBox.setMargin(secondImage, new Insets(10));


        Button show = new Button("SHOW");
        show.setMinSize(100, 30);
        show.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        show.setTextAlignment(TextAlignment.CENTER);
        HBox.setMargin(show, new Insets(50, 10, 50, 10));

        Button modal = new Button("MODAL");
        modal.setMinSize(100, 30);
        modal.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        modal.setTextAlignment(TextAlignment.CENTER);
        HBox.setMargin(modal, new Insets(50, 10, 50, 10));

        modal.setOnAction(e -> {
            //try {
                //Parent root = FXMLLoader.load(getClass().getResource("lessonDetail.fxml"));
                Stage stage = new Stage();
                stage.setTitle(problem.getTitle());
                //stage.setScene(new Scene(root));
                stage.show();
            //}
            //catch (IOException ex) {
            //    ex.printStackTrace();
            //}
        });

        HBox detail = new HBox(info, firstImage, secondImage, show, modal);
        detail.setMinSize(10, 150);
        return detail;
    }
}

