module com.university.lms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql ;
    opens com.university.lms to javafx.fxml;
    exports com.university.lms;
}
