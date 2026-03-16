package com.university.lms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IssueBook extends Application{
    private static Scene scene ;
    @Override
    public void start(Stage stage) throws Exception {
        HBox header = new HBox(20);
        header.setStyle("-fx-padding: 10; -fx-background-color: #2d3b7666;");

        Image logoImg = new Image("file:iitdelhi\\src\\main\\java\\com\\university\\lms\\iiitd_header.png");
        ImageView logo = new ImageView(logoImg);
        logo.setFitHeight(100);
        logo.setPreserveRatio(true);
         Text text = new Text("Issue Book Page");
         text.setX(300) ;
         text.setY(20) ;
         text.setFont(new Font ("Segoe Script", 24));
         Label label = new Label("Enter Book ID");
         TextField tf = new TextField();
         tf.autosize();
         tf.setPromptText("GB_|_|_|_|_|");
         String student_id = StudentLogin.getStudentId() ;
         Button btn = new Button("Issue Book");

         btn.setOnAction(e->{
             System.out.println(student_id);
             String book_id = tf.getText() ;
             Boolean verifier = IssueDAO.issueBook(book_id, student_id) ;
             if(verifier){
                System.out.println("Book issued successful");
             }
             else{
                System.out.println("Book does not issued successfully");
             }
            

         });
         btn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-padding: 10px 20px;");
         Button backbtn = new Button("Home") ;
         backbtn.setStyle("-fx-background-color: purple; -fx-text-fill: white; -fx-padding: 10px 20px;");
         backbtn.setOnAction(e -> {
             try {
                 IntroductoryPage sd = new IntroductoryPage();
                 sd.start(stage);
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
         });

         VBox root = new VBox(12);
         root.getChildren().addAll(logo , text,label,tf, btn ,backbtn) ;
         Scene scene = new Scene(root,600,400);
         root.setStyle("-fx-background-color: #54ac23;");
         stage.setTitle("Issue Book");
         stage.setScene(scene);  
         stage.show();  


    }
    public static void main(String[] args) {
        launch(args) ;
    }
}
