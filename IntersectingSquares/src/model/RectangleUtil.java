package model;

import java.util.ArrayList;

public class RectangleUtil {

    /**
     * Find the intersection of two rectangles on a Cartesian graph. If the rectangles lay on top of
     * each other than this would be considered an intersection.
     * 
     * @param recA
     * @param recB
     * @return the rectangle formed by intersection of two rectangles, null if intersection doesn't
     *         exist
     */
    public Rectangle findIntersection(Rectangle recA, Rectangle recB) {

        int numberOfIntersectionCorners = 0;
        ArrayList<Coordinate> recBCoords = findCornerCoordinates(recB); // get the four points of rec for intersection testing
        ArrayList<Integer> corners = new ArrayList<>(); // will be used to store corner number of intersection
        ArrayList<Coordinate> intersectingCoordinate = new ArrayList<>(); // list of intersection points

        int[][] spanRecA = getSpan(recA);
        int[][] spanRecB = getSpan(recB);

        /*
         * Loop through the coordinates of rectangle B, for intersection to occur one of these points
         * must be inside the span of rectangle A, grab all intersection points, the corner number
         * and how many points intersect
         */
        for (int i = 0; i < 4; i++) {
            if (recBCoords.get(i).x >= spanRecA[0][0] && recBCoords.get(i).x <= spanRecA[0][1]) {
                if (recBCoords.get(i).y >= spanRecA[1][0] && recBCoords.get(i).y <= spanRecA[1][1]) {
                    intersectingCoordinate.add(recBCoords.get(i));
                    corners.add(i);
                    numberOfIntersectionCorners++;
                }

            }
        }

        return createRectangle(recB, numberOfIntersectionCorners, corners, intersectingCoordinate, spanRecA, spanRecB);
    }

    /**
     * Helper to decide which create rectangle should be called based on the number of corners found
     * to be intersecting
     * 
     * @param recB
     * @param numberOfIntersectionCorners
     * @param corners
     * @param intersectingCoordinate
     * @param spanRecA
     * @param spanRecB
     * @return
     */
    public Rectangle createRectangle(Rectangle recB, int numberOfIntersectionCorners, ArrayList<Integer> corners,
                                     ArrayList<Coordinate> intersectingCoordinate, int[][] spanRecA, int[][] spanRecB) {

        Rectangle result;

        switch (numberOfIntersectionCorners) {
            case 0:
                result = null;
                break;
            case 1:
                result = createRectangleOneCorner(intersectingCoordinate.get(0), corners.get(0), spanRecA, spanRecB);
                break;
            case 2:
                result = createRectangleTwoCorners(intersectingCoordinate, corners, spanRecA, spanRecB);
                break;
            case 4:
                result = recB; // The second rectangle is entirely inside the first rectangle
                break;
            default:
                result = null; // insert error handling
                break;
        }

        return result;
    }

    /**
     * Helper method extracted for readability, performs calculations necessary to calculate and
     * create variables for rectangle of intersection
     * 
     * @param p the Coordinate of intersection
     * @param corner the of the rectangle that intersects with the other rectangle
     * @return the resulting rectangle, return null if the corner is < 0 or > 3, or the Coordinate
     *         is null
     */
    public Rectangle createRectangleOneCorner(Coordinate p, int corner, int[][] spanRecA, int[][] spanRecB) {

        Rectangle result;
        int width;
        int height;

        /**
         * This switch statement looks at which corner is intersection and then using the
         * coordinates of this point and the spans stored earlier to calculate the position of the
         * bottom left corner and then width and height to create the resulting rectangle of
         * intersection.
         */
        switch (corner) {
            case 0:
                width = spanRecA[0][1] - p.x;
                height = spanRecA[1][1] - p.y;
                result = new Rectangle(p.x, p.y, width, height);
                break;

            case 1:
                width = spanRecA[0][1] - p.x;
                height = p.y - spanRecA[1][0];
                result = new Rectangle(spanRecB[0][0], spanRecA[1][0], width, height);
                break;

            case 2:
                width = p.x - spanRecA[0][0];
                height = spanRecA[1][1] - p.y;
                result = new Rectangle(spanRecA[0][0], spanRecA[1][0], width, height);
                break;

            case 3:
                width = p.x - spanRecA[0][0];
                height = spanRecA[1][1] - p.y;
                result = new Rectangle(spanRecA[0][0], spanRecB[1][0], width, height);
                break;

            default:
                result = null;
                break;
        }
        return result;
    }

    /**
     * Method to calculate the area of intersection rectangle when two corners are intersecting
     * 
     * @param intersectingCoordinates the points of intersection
     * @param corners the numbers that were found to be intersecting
     * @param spanRecA the span of rectangle A
     * @param spanRecB the span of rectangle B
     * @return result the rectangle found in the intersection, will return null if corners aren't
     *         found
     */
    public Rectangle createRectangleTwoCorners(ArrayList<Coordinate> intersectingCoordinates, ArrayList<Integer> corners, int[][] spanRecA,
                                               int[][] spanRecB) {

        Rectangle result = null;
        int cornerOne = corners.get(0);
        int cornerTwo = corners.get(1);
        Coordinate pointOne = intersectingCoordinates.get(0);
        Coordinate pointTwo = intersectingCoordinates.get(1);
        int height;
        int width;

        if (cornerOne == 0 && cornerTwo == 1) {
            height = pointTwo.y - pointOne.y;
            width = spanRecA[0][1] - pointTwo.x;
            result = new Rectangle(pointOne.x, pointOne.y, width, height);
        } else if (cornerOne == 1 && cornerTwo == 2) {
            height = pointOne.y - spanRecA[1][0];
            width = pointTwo.x - pointOne.x;
            result = new Rectangle(spanRecB[0][0], spanRecA[1][0], width, height);
        } else if (cornerOne == 2 && cornerTwo == 3) {
            height = pointOne.y - pointTwo.y;
            width = pointTwo.x - spanRecA[0][0];
            result = new Rectangle(spanRecA[0][0], spanRecB[1][0], width, height);
        } else if (cornerOne == 0 && cornerTwo == 3) {
            height = spanRecA[1][1] - pointOne.y;
            width = pointTwo.x - pointOne.x;
            result = new Rectangle(pointOne.x, pointOne.y, width, height);
        }

        return result;
    }

    /**
     * This method will extract the Coordinates of the 4 corners of a rectangle in clockwise
     * rotation starting with the bottom left corner
     * 
     * @param rec the rectangle used to extract the Coordinates
     * @return the list of Coordinates calculated
     */
    public ArrayList<Coordinate> findCornerCoordinates(Rectangle rec) {

        ArrayList<Coordinate> coordinates = new ArrayList<>();

        coordinates.add(new Coordinate(rec.x, rec.y)); // bottomLeft point
        coordinates.add(new Coordinate(rec.x, rec.y + rec.height)); // topLeft point
        coordinates.add(new Coordinate(rec.x + rec.width, rec.y + rec.height)); // topRight point
        coordinates.add(new Coordinate(rec.x + rec.width, rec.y)); // bottomRight point

        return coordinates;
    }

    /**
     * This method will get the span of the rectangle. The span is the right most x Coordinate
     * stored in [0] and the left most x Coordinate stored in [1]. The same for the y coordinate
     * 
     * @param rec the rectangle used to get spans
     * @return a 2d array that will store the spans, the first row is the x span, and the second row
     *         is the y span
     */
    public int[][] getSpan(Rectangle rec) {

        int[][] span = new int[2][2];

        span[0][0] = rec.x;
        span[0][1] = rec.x + rec.width;
        span[1][0] = rec.y;
        span[1][1] = rec.y + rec.height;

        return span;
    }
}
