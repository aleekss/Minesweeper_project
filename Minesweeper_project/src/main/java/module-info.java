module com.example.minesweeper_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens com.example.minesweeper_project to javafx.fxml;
    exports com.example.minesweeper_project;
}