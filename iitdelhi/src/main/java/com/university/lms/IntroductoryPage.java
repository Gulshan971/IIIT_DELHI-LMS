package com.university.lms;

// import java.awt.Font;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IntroductoryPage extends Application {
    public static Scene scene ;

    @Override
    public void start(Stage stage)throws IOException{
        
        BorderPane root = new BorderPane();

        // ----- HEADER -----
        HBox header = new HBox(20);
        header.setStyle("-fx-padding: 10; -fx-background-color: #2d3b7666;");

        Image logoImg = new Image("file:iitdelhi\\src\\main\\java\\com\\university\\lms\\LogoIIItD.jpg");
        ImageView logo = new ImageView(logoImg);
        logo.setFitHeight(40);
        logo.setPreserveRatio(true);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.SOMETIMES);

        Button home = new Button("Home");
        Button courses = new Button("Issue Book");
        courses.setOnAction(new  EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                IssueBook issueBook = new IssueBook() ;
                try{
                    issueBook.start(stage) ;
                }
                catch(Exception ex){
                    ex.printStackTrace() ;
                }
                

            }
        });
        Button assignments = new Button("Return Book");
        assignments.setOnAction(e ->{
         ReturnBook returnBook = new ReturnBook() ;
         try{
            returnBook.start(stage) ;
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
        });
        Button grades = new Button("Pay Fine");
        grades.setOnAction(e->{
            PayFine payFine = new PayFine() ;
            try{
              payFine.start(stage);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });

        header.getChildren().addAll(logo, spacer, home, courses, assignments, grades);


        Image campusImg = new Image("file:iitdelhi\\src\\main\\java\\com\\university\\lms\\library_pohot.jpg");
        ImageView campuslogo = new ImageView(campusImg);
        campuslogo.setPreserveRatio(false);
        campuslogo.fitWidthProperty().bind(stage.widthProperty());
        campuslogo.setFitHeight(250);

        VBox topContent = new VBox() ;
        topContent.getChildren().addAll(header , campuslogo) ; 

        Label info = new Label("Ouick Links") ;
        // info.setFont(Font.font(STYLESHEET_MODENA));
          
        VBox details  = new VBox(20) ;

        Button Repository = new Button("Repository") ;
        Button Sugamaya_pustakalya = new Button("Sugamaya Pustakalya") ;
        Button Remote_Access = new Button("Remote_Access") ;
        Button Suugest_A_Book = new Button("Suggest A Book") ;
        Button Events_lib = new Button("Events") ;
        Button Floor_guide = new Button("Floor guide") ;
        Button NewsLetter = new Button("NewsLetter") ;

        Image gif = new Image("file:iitdelhi\\src\\main\\java\\com\\university\\lms\\central_image.gif");
       ImageView gifView = new ImageView(gif);

       gifView.setFitWidth(400);
       gifView.setPreserveRatio(true);

        VBox rules = new VBox() ;
        Text t1 = new Text("Library Opening Hours:") ;
        t1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text t2 = new Text("Mon-Fri: 8:30 AM to 9 PM (during the session)") ;
        Text t3 = new Text("Sat: 8:30 AM to 6 PM");
        Text t4 = new Text("During Exams:\r\n" + //
                        "Mon-Sun, including holidays: 8:30 AM to 9 PM");
        Text t5 = new Text("Mon-Fri: 8:30 AM to 7 PM (during Summer/Winter break)");                
        rules.getChildren().addAll(t1 , t2,t3,t4,t5) ;
        details.getChildren().addAll(Repository ,  Sugamaya_pustakalya  ,Remote_Access ,Suugest_A_Book , Events_lib , Floor_guide , NewsLetter) ;
        details.setAlignment(Pos.CENTER);
        HBox conclude = new HBox() ;
        conclude.setStyle("-fx-background-color: black; -fx-padding: 15;");
        conclude.setAlignment(Pos.CENTER);

        Label footerText = new Label("© IIITD Library");
        footerText.setStyle("-fx-text-fill: white;");

        conclude.getChildren().add(footerText);

        VBox info_part = new VBox() ;
        info_part.getChildren().addAll(info,details) ;
        info_part.setAlignment(Pos.CENTER);
        root.setTop(topContent);
        root.setLeft(info_part);
        root.setRight(rules); 
        root.setCenter(gifView) ;
        rules.setPrefWidth(300);
        root.setBottom(conclude) ;

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("IIITD LMS");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

    
}
