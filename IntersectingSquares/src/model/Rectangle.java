package model;

/*
 * This class represents a Rectangle, it will be represented as an x,y coordinate and width and
 * height
 * @Author Michael Humphrey
 */

public class Rectangle {

    int x;
    int y;
    int width;
    int height;

    /**
     *
     * @param x the coordinate of the bottom left Coordinate of the Rectangle
     * @param y the coordinate of the bottom left Coordinate of the Rectangle
     * @param width the width of the Rectangle
     * @param height the height of the Rectangle
     */
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
