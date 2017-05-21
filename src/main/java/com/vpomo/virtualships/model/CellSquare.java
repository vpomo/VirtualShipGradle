package com.vpomo.virtualships.model;

/**
 * Created by Pomogalov on 28.03.2016.
 */
public class CellSquare {
    private int coordinateX;
    private int coordinateY;
    private String color;
    private String previousColor;
    private int numberTimesToChangeColor;

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberTimesToChangeColor() {
        return numberTimesToChangeColor;
    }

    public void setNumberTimesToChangeColor(int numberTimesToChangeColor) {
        this.numberTimesToChangeColor = numberTimesToChangeColor;
    }

    public String getPreviousColor() {
        return previousColor;
    }

    public void setPreviousColor(String previousColor) {
        this.previousColor = previousColor;
    }

    public CellSquare(int currentCoordinateX, int currentCoordinateY) {
        this.coordinateX = currentCoordinateX;
        this.coordinateY = currentCoordinateY;
    }
}
