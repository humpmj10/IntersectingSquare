package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class RectangleUtilTest {

    Rectangle recA;

    RectangleUtil util = new RectangleUtil();

    @Test
    public void testFindCornerCoordinates() {

        recA = new Rectangle(0, 0, 2, 2);
        ArrayList<Coordinate> coordinates = util.findCornerCoordinates(recA);

        // bottomLeft
        assertEquals(coordinates.get(0).getX(), 0);
        assertEquals(coordinates.get(0).getY(), 0);
        // topLeft
        assertEquals(coordinates.get(1).getX(), 0);
        assertEquals(coordinates.get(1).getY(), 2);
        // topRight
        assertEquals(coordinates.get(2).getX(), 2);
        assertEquals(coordinates.get(2).getY(), 2);
        // bottomRight
        assertEquals(coordinates.get(3).getX(), 2);
        assertEquals(coordinates.get(3).getY(), 0);

        // make sure negative numbers work
        recA = new Rectangle(-2, -2, 4, 4);
        coordinates = util.findCornerCoordinates(recA);

        // bottomLeft
        assertEquals(coordinates.get(0).getX(), -2);
        assertEquals(coordinates.get(0).getY(), -2);
        // topLeft
        assertEquals(coordinates.get(1).getX(), -2);
        assertEquals(coordinates.get(1).getY(), 2);
        // topRight
        assertEquals(coordinates.get(2).getX(), 2);
        assertEquals(coordinates.get(2).getY(), 2);
        // bottomRight
        assertEquals(coordinates.get(3).getX(), 2);
        assertEquals(coordinates.get(3).getY(), -2);

    }

    @Test
    public void testGetSpan() {

        recA = new Rectangle(0, 0, 2, 2);
        int[][] span = util.getSpan(recA);
        assertEquals(span[0][0], 0);
        assertEquals(span[0][1], 2);
        assertEquals(span[1][0], 0);
        assertEquals(span[1][1], 2);

        recA = new Rectangle(-2, -2, 2, 2);
        span = util.getSpan(recA);
        assertEquals(span[0][0], -2);
        assertEquals(span[0][1], 0);
        assertEquals(span[1][0], -2);
        assertEquals(span[1][1], 0);
    }

    /**
     * Test for intersection point as bottomLeftCorner
     */
    @Test
    public void testCreateRectangleOneCornerFirstCase() {

        Coordinate p = new Coordinate(1, 1);
        int corner = 0;
        int[][] spanRecA = {{0, 2}, {0, 2}};
        int[][] spanRecB = {{1, 3}, {1, 3}};
        Rectangle result = util.createRectangleOneCorner(p, corner, spanRecA, spanRecB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);

    }

    /**
     * Test for intersection point as topLeftCorner
     */
    @Test
    public void testCreateRectangleOneCornerSecondCase() {

        Coordinate p = new Coordinate(1, 1);
        int corner = 1;
        int[][] spanRecA = {{0, 2}, {0, 2}};
        int[][] spanRecB = {{1, 3}, {1, -1}};
        Rectangle result = util.createRectangleOneCorner(p, corner, spanRecA, spanRecB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 0);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);

    }

    /**
     * Test for intersection point as topRightCorner
     */
    @Test
    public void testCreateRectangleOneCornerThirdCase() {

        Coordinate p = new Coordinate(1, 1);
        int corner = 2;
        int[][] spanRecA = {{0, 2}, {0, 2}};
        int[][] spanRecB = {{-1, 1}, {-1, 1}};
        Rectangle result = util.createRectangleOneCorner(p, corner, spanRecA, spanRecB);
        assertEquals(result.x, 0);
        assertEquals(result.y, 0);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);

    }

    /**
     * Test for intersection point as bottomRightCorner
     */
    @Test
    public void testCreateRectangleOneCornerFourthCase() {

        Coordinate p = new Coordinate(1, 1);
        int corner = 3;
        int[][] spanRecA = {{0, 2}, {0, 2}};
        int[][] spanRecB = {{1, 3}, {1, 3}};
        Rectangle result = util.createRectangleOneCorner(p, corner, spanRecA, spanRecB);
        assertEquals(result.x, 0);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);

    }

    /**
     * Test for intersection point as bottomLeftCorner
     */
    @Test
    public void testFindIntersectionOneCornerCaseOne() {

        Rectangle recA = new Rectangle(0, 0, 2, 2);
        Rectangle recB = new Rectangle(1, 1, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);
    }

    /**
     * Test for intersection point as topLeftCorner
     */
    @Test
    public void testFindIntersectionOneCornerCaseTwo() {

        Rectangle recA = new Rectangle(0, 0, 2, 2);
        Rectangle recB = new Rectangle(1, -1, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 0);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);
    }

    /**
     * Test for intersection point as topRightCorner
     */
    @Test
    public void testFindIntersectionOneCornerCaseThree() {

        Rectangle recA = new Rectangle(0, 0, 2, 2);
        Rectangle recB = new Rectangle(-1, -1, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 0);
        assertEquals(result.y, 0);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);
    }

    /**
     * Test for intersection point as bottomRightCorner
     */
    @Test
    public void testFindIntersectionOneCornerCaseFour() {

        Rectangle recA = new Rectangle(0, 0, 2, 2);
        Rectangle recB = new Rectangle(-1, 1, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 0);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 1);
    }

    @Test
    public void testFindIntersectionFourCorners() {

        Rectangle recA = new Rectangle(0, 0, 4, 4);
        Rectangle recB = new Rectangle(1, 1, 1, 1);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result, recB);
    }

    @Test
    public void testFindIntersectionNoCorners() {

        Rectangle recA = new Rectangle(0, 0, 4, 4);
        Rectangle recB = new Rectangle(15, 15, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result, null);
    }

    /**
     * Test for intersection points are bottomLeft and topRightCorner
     */
    @Test
    public void testCreateRectangleTwoCornersCaseOne() {

        ArrayList<Coordinate> intersectingCoordinates = new ArrayList<>();
        ArrayList<Integer> corners = new ArrayList<>();

        intersectingCoordinates.add(new Coordinate(3, 1));
        intersectingCoordinates.add(new Coordinate(3, 3));

        corners.add(0);
        corners.add(1);

        int[][] spanRecA = {{0, 4}, {0, 4}};
        int[][] spanRecB = {{1, 3}, {3, 5}};
        Rectangle result = util.createRectangleTwoCorners(intersectingCoordinates, corners, spanRecA, spanRecB);
        assertEquals(result.x, 3);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 2);
    }

    /**
     * Test for intersection points are topLeftCorner and topRightCorner
     */
    @Test
    public void testCreateRectangleTwoCornersCaseTwo() {

        ArrayList<Coordinate> intersectingCoordinates = new ArrayList<>();
        ArrayList<Integer> corners = new ArrayList<>();

        intersectingCoordinates.add(new Coordinate(1, 1));
        intersectingCoordinates.add(new Coordinate(3, 1));

        corners.add(1);
        corners.add(2);

        int[][] spanRecA = {{0, 4}, {0, 4}};
        int[][] spanRecB = {{1, -1}, {3, 1}};
        Rectangle result = util.createRectangleTwoCorners(intersectingCoordinates, corners, spanRecA, spanRecB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 0);
        assertEquals(result.width, 2);
        assertEquals(result.height, 1);
    }

    /**
     * Test for intersection points are topRightCorner and bottomRightCorner
     */
    @Test
    public void testCreateRectangleTwoCornersCaseThree() {

        ArrayList<Coordinate> intersectingCoordinates = new ArrayList<>();
        ArrayList<Integer> corners = new ArrayList<>();

        intersectingCoordinates.add(new Coordinate(1, 3));
        intersectingCoordinates.add(new Coordinate(1, 1));

        corners.add(2);
        corners.add(3);

        int[][] spanRecA = {{0, 4}, {0, 4}};
        int[][] spanRecB = {{-1, -1}, {1, 3}};
        Rectangle result = util.createRectangleTwoCorners(intersectingCoordinates, corners, spanRecA, spanRecB);
        assertEquals(result.x, 0);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 2);
    }

    /**
     * Test for intersection points are bottomLeftCorner and bottomRightCorner
     */
    @Test
    public void testCreateRectangleTwoCornersCaseFour() {

        ArrayList<Coordinate> intersectingCoordinates = new ArrayList<>();
        ArrayList<Integer> corners = new ArrayList<>();

        intersectingCoordinates.add(new Coordinate(1, 3));
        intersectingCoordinates.add(new Coordinate(3, 3));

        corners.add(0);
        corners.add(3);

        int[][] spanRecA = {{0, 4}, {0, 4}};
        int[][] spanRecB = {{1, 3}, {3, 5}};
        Rectangle result = util.createRectangleTwoCorners(intersectingCoordinates, corners, spanRecA, spanRecB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 3);
        assertEquals(result.width, 2);
        assertEquals(result.height, 1);
    }

    /**
     * Test for intersection points are bottomLeft and topRightCorner
     */
    @Test
    public void testfindIntersectionTwoCornersCaseOne() {

        Rectangle recA = new Rectangle(0, 0, 4, 4);
        Rectangle recB = new Rectangle(3, 1, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 3);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 2);
    }

    /**
     * Test for intersection points are topLeftCorner and topRightCorner
     */
    @Test
    public void testfindIntersectionTwoCornersCaseTwo() {

        Rectangle recA = new Rectangle(0, 0, 4, 4);
        Rectangle recB = new Rectangle(1, -1, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 0);
        assertEquals(result.width, 2);
        assertEquals(result.height, 1);
    }

    /**
     * Test for intersection points are topRightCorner and bottomRightCorner
     */
    @Test
    public void testfindIntersectionTwoCornersCaseThree() {

        Rectangle recA = new Rectangle(0, 0, 4, 4);
        Rectangle recB = new Rectangle(-1, 1, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 0);
        assertEquals(result.y, 1);
        assertEquals(result.width, 1);
        assertEquals(result.height, 2);
    }

    /**
     * Test for intersection points are bottomLeftCorner and bottomRightCorner
     */
    @Test
    public void testfindIntersectionTwoCornersCaseFour() {

        Rectangle recA = new Rectangle(0, 0, 4, 4);
        Rectangle recB = new Rectangle(1, 3, 2, 2);
        Rectangle result = util.findIntersection(recA, recB);
        assertEquals(result.x, 1);
        assertEquals(result.y, 3);
        assertEquals(result.width, 2);
        assertEquals(result.height, 1);
    }

    @Test
    public void testfindIntersectionCornersOverLapping() {

        Rectangle recA = new Rectangle(0, 0, 4, 4);
        Rectangle result = util.findIntersection(recA, recA);
        assertEquals(result, recA);

    }
}
