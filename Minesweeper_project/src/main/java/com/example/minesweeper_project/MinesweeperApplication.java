package com.example.minesweeper_project;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class
MinesweeperApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        Scene scene = new Scene(createField());
        stage.setScene(scene);
        stage.show();
    }


    static final int Y_FIELDS = 16;
    static final int X_FIELDS = 16;
    static final int FIELD_SIZE = 20;


static com.example.minesweeper_project.Field[][] grid = new com.example.minesweeper_project.Field[X_FIELDS][Y_FIELDS];


    private int count;


    private Parent createField() {
        Pane root = new Pane();
        root.setPrefSize(450, 320);


        Label numberofBounds = new Label();
        root.getChildren().add(numberofBounds);


        for (int y = 0; y < Y_FIELDS; y++) {
            for (int x = 0; x < X_FIELDS; x++) {
                Field field = new Field(x, y, Math.random() < 0.2);
                grid[x][y] = field;
                root.getChildren().add(field);
            }
        }

        for (int y = 0; y < Y_FIELDS; y++) {
            for (int x = 0; x < X_FIELDS; x++) {
                Field field = grid[x][y];
                count = 0;
                if (!field.getHasBomb()) {
                    count = 0;
                    ArrayList<Field> fieldlist = (ArrayList<Field>) getNeighbours(field);
                    for (Field f : fieldlist) {
                        if (f.getHasBomb()) {
                            count++;
                        }
                    }
                    if (count == 0){
                        field.setBombCount("");
                    } else {
                        field.setBombCount(Integer.toString(count));
                    }
                }
            }
        }


        numberofBounds.setText("Bombcount: "+ Field.numberofBounds);
        numberofBounds.setTranslateX(350);
        numberofBounds.setTranslateY(0);


        return root;
    }


    static List<Field> getNeighbours(Field field) {
        List<Field> neighbours = new ArrayList<>();


        int[] points = new int[]{
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };
        for (int i = 0; i < points.length; i++) {
            int dx = points[i];     //delta X
            int dy = points[i + 1];   //delta Y

            int newX = field.getX() + dx;     //get the field on the specific position
            int newY = field.getY() + dy;


            if (newX >= 0 && newX < X_FIELDS && newY >= 0 && newY < Y_FIELDS) {
                neighbours.add(grid[newX][newY]);


            }
            i++;
        }
        return neighbours;
    }





    public static void main(String[] args) {
        launch();
    }


}