package com.university.lms;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PayFine extends Application {
    public static Scene scene ; 
    @Override
    public void start(Stage stage)throws Exception{
        Label title = new Label("Pay Library Fine");

        Label studentLabel = new Label("Student ID");
        TextField studentField = new TextField();

        Label fineLabel = new Label("Fine Amount");
        TextField fineField = new TextField();

        Button payButton = new Button("Pay Fine");

        Label result = new Label();

        payButton.setOnAction(e -> {

            try {

                double fine = Double.parseDouble(fineField.getText());

                if (fine > 0) {
                    result.setText("Fine Paid Successfully!");
                    fineField.clear();
                } 
                else {
                    result.setText("No Fine to Pay");
                }

            } catch (Exception ex) {
                result.setText("Enter valid fine amount");
            }

        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                title,
                studentLabel,
                studentField,
                fineLabel,
                fineField,
                payButton,
                result
        );

        Scene scene = new Scene(layout, 350, 300);

        stage.setTitle("Pay Fine");
        stage.setScene(scene);
        stage.show();
    }
    }

