package com.example.minesweeper_project;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.security.cert.PolicyNode;

public class Field extends StackPane {

    public static int numberofBounds;
    private int x;
    private int y;
    private boolean hasBomb;
    private String text = "";
    public Text bombCount;
    private Rectangle fieldNode = null;

    private boolean isOpen = false;

    static boolean stop = false;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public Field(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        if (hasBomb){
            numberofBounds++;
        }


        if (hasBomb) {
            text = "X";
        }


        //Create Node //Rectangle oder Button for Feld:
        fieldNode = new Rectangle(MinesweeperApplication.FIELD_SIZE - 2, MinesweeperApplication.FIELD_SIZE - 2);
        fieldNode.setFill(Color.LIGHTSKYBLUE);
        fieldNode.setStroke(Color.YELLOWGREEN);
        fieldNode.setVisible(true);

        bombCount = new Text();
        bombCount.setText(this.hasBomb ? "X" : ""); // => if else
        bombCount.setStroke(Color.BLACK);
        bombCount.setVisible(false);









        getChildren().addAll(fieldNode, bombCount); //Die Verdeckung und die Eigenschaften auf das Fieldobjekt schicken
        setTranslateX(x* MinesweeperApplication.FIELD_SIZE);
        setTranslateY(y* MinesweeperApplication.FIELD_SIZE);
        setOnMouseClicked(e -> onFieldClick(e));




    }

    private void onFieldClick(MouseEvent e) {

        if(stop){
            return;
        }

        if (e.getButton() == MouseButton.PRIMARY) {
            open2();
        }
        if (e.getButton() == MouseButton.SECONDARY) {
            open();
        }
        if (e.getButton() == MouseButton.PRIMARY && hasBomb == true) {
        showBombs();
        stop = true;




        }
    }

    public void open() {
        fieldNode.setFill(Color.RED);
        bombCount.setVisible(false);
    }

    public void open2() {
        if (this.isOpen) {
            return;
        }


        this.isOpen = true;
        bombCount.setVisible(true);
        fieldNode.setFill(Color.GRAY);
        if (bombCount.getText().isEmpty()) {
            MinesweeperApplication.getNeighbours(this).forEach(Field::open2);
        }


    }


    public void showBombs(){
        for (int y = 0; y < MinesweeperApplication.Y_FIELDS; y++) {
            for (int x = 0; x < MinesweeperApplication.X_FIELDS; x++) {
                Field field = MinesweeperApplication.grid[x][y];
                if (field.hasBomb){
                    field.bombCount.setVisible(true);
                }
            }
        }
    }











    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }
    public boolean getHasBomb(){
        return hasBomb;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Text getBombCount() {
        return bombCount;
    }

    public void setBombCount(String bombCount) {
        this.bombCount.setText(bombCount);
    }

    public Rectangle getFieldNode() {
        return fieldNode;
    }

    public void setFieldNode(Rectangle fieldNode) {
        this.fieldNode = fieldNode;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}