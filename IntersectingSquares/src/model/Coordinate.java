package model;

/**
 * This class represents a point on a Cartesian graph.
 * 
 * @author Michael Humphrey
 *
 */
public class Coordinate {

    int x;
    int y;

    /**
     *
     * @param x the x coordinate on the graph
     * @param y the y coordinate on the graph
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {

        return x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getY() {

        return y;
    }

    public void setY(int y) {

        this.y = y;
    }
}
