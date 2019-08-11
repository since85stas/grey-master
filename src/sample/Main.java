package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import static sample.Utils.*;

public class Main extends Application {

    File filePath;
    BufferedImage origImage;
    Button convButton;
    Button saveButton;
    boolean isBlackToWhite = true;
    FlowPane convPane;
    CheckBox invCheck;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Label titleLable = new Label("GREY CONVERTOR V1.0");
        titleLable.setPrefWidth(300);
        titleLable.setStyle("-fx-font-size:25");

        Button button = new Button();
        button.setText("Select file");
        button.setPrefWidth(200);
        button.setStyle("-fx-font-size:20");

        button.setOnMouseClicked(mousevent -> {
            final FileChooser fc = new FileChooser();
            File file =fc.showOpenDialog(primaryStage);
            if (file == null) {
                System.out.println("error");
            } else {
                filePath=file.getAbsoluteFile();
                origImage = readImage(filePath.getAbsolutePath());
                System.out.println("ok");
                convPane.setVisible(true);
            }

        });

        convPane = new FlowPane();
        convPane.setOrientation(Orientation.HORIZONTAL);
        convPane.setHgap(10);
        convPane.setVisible(false);

        invCheck = new CheckBox("inverse");

        convButton = new Button();
        convButton.setPrefWidth(200);
        convButton.setStyle("-fx-font-size:20");
        convButton.setText("convert");
        convButton.setOnMouseClicked(mouseEvent -> {
            if (invCheck.isSelected()) {
                isBlackToWhite = false;
            } else {
                isBlackToWhite = true;
            }
            origImage = transformImageMy(origImage, isBlackToWhite);
            saveButton.setVisible(true);
        });

        convPane.getChildren().add(convButton);
        convPane.getChildren().add(invCheck);

        saveButton = new Button();
        saveButton.setPrefWidth(200);
        saveButton.setStyle("-fx-font-size:20");
        saveButton.setVisible(false);
        saveButton.setText("save");
        saveButton.setOnMouseClicked(mouseEvent -> {
            writeImage(origImage,filePath);
        });

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setOrientation(Orientation.VERTICAL);
        pane.setVgap(20);

        pane.getChildren().addAll(titleLable,button,convPane,saveButton);

        primaryStage.setScene(new Scene(pane, 400, 375));
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
