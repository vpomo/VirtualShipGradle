package com.vpomo.virtualships.service;

import com.vpomo.virtualships.model.Ship;
import com.vpomo.virtualships.model.Square;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vpomo.virtualships.service.ControlValues.MAX_SIZE_SQUARE;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Pomogalov on 24.03.2016.
 */

public abstract class AbstractShipServiceTest {
    @Autowired
    protected ShipService shipService;

    @Before
    public void shouldShipService() {

        System.out.println(" ===== Before test passed  ==== ");
    }

    @Test
    public void testNextMove() {
        int coordinateX1, coordinateY1;
        int coordinateX2, coordinateY2;
        int changeCoordinate;
        Square square = new Square();
        System.out.println(" ===== test nextMove() started  ==== ");
        Ship shipTest = new Ship("typeA",square);
        coordinateX1 = shipTest.getCoordinateX();
        coordinateY1 = shipTest.getCoordinateY();
        this.shipService.nextMove(shipTest, square);

        coordinateX2 = shipTest.getCoordinateX();
        coordinateY2 = shipTest.getCoordinateY();

        changeCoordinate = 0;
        changeCoordinate = (coordinateX1 - coordinateX2) + (coordinateY1 - coordinateY2);
        assertFalse(changeCoordinate == 0);

        System.out.println(" ===== test nextMove() passed  ==== ");
    }

    @Test
    /**
     * int nextMoveX(String typeShip, int directionMovement, int currentX)
     */
    public void testNextMoveXTypeA() {
        System.out.println(" ===== test nextMoveX() for ship type A started  ==== ");
        String shipType = "typeA";
        int currentX = 2;
        assertTrue(this.shipService.nextMoveX(shipType, 0, currentX) == -1);
        assertTrue(this.shipService.nextMoveX(shipType, 6, currentX) == -1);
        assertTrue(this.shipService.nextMoveX(shipType, 7, currentX) == -1);
        assertTrue(this.shipService.nextMoveX(shipType, 1, currentX) == 0);
        assertTrue(this.shipService.nextMoveX(shipType, 3, currentX) == 1);

        currentX = 0;
        assertTrue(this.shipService.nextMoveX(shipType, 0, currentX) == 1);
        currentX = MAX_SIZE_SQUARE - 1;
        assertTrue(this.shipService.nextMoveX(shipType, 2, currentX) == -1);

        System.out.println(" ===== test nextMoveX() for ship type A passed  ==== ");
    }

    @Test
    public void testNextMoveYTypeA() {
        System.out.println(" ===== test nextMoveY() for ship type A started  ==== ");
        String shipType = "typeA";
        int currentY = 2;
        assertTrue(this.shipService.nextMoveY(shipType, 0, currentY) == -1);
        assertTrue(this.shipService.nextMoveY(shipType, 3, currentY) == 0);
        assertTrue(this.shipService.nextMoveY(shipType, 4, currentY) == 1);

        currentY = 0;
        assertTrue(this.shipService.nextMoveY(shipType, 0, currentY) == 1);
        currentY = MAX_SIZE_SQUARE - 1;
        assertTrue(this.shipService.nextMoveY(shipType, 2, currentY) == -1);

        System.out.println(" ===== test nextMoveY() for ship type A passed  ==== ");
    }

    @Test
    public void testNextMoveXTypeD() {
        System.out.println(" ===== test nextMoveX() for ship type D started  ==== ");
        String shipType = "typeD";
        int currentX = 2;
        assertTrue(this.shipService.nextMoveX(shipType, 0, currentX) == -1);
        assertTrue(this.shipService.nextMoveX(shipType, 1, currentX) == 1);
        assertTrue(this.shipService.nextMoveX(shipType, 2, currentX) == 1);
        assertTrue(this.shipService.nextMoveX(shipType, 3, currentX) == -1);

        currentX = 0;
        assertTrue(this.shipService.nextMoveX(shipType, 0, currentX) == 1);
        currentX = MAX_SIZE_SQUARE - 1;
        assertTrue(this.shipService.nextMoveX(shipType, 1, currentX) == -1);

        System.out.println(" ===== test nextMoveX() for ship type D passed  ==== ");
    }

    @Test
    public void testNextMoveYTypeD() {
        System.out.println(" ===== test nextMoveY() for ship type D started  ==== ");
        String shipType = "typeD";
        int currentY = 2;
        assertTrue(this.shipService.nextMoveY(shipType, 0, currentY) == -1);
        assertTrue(this.shipService.nextMoveY(shipType, 1, currentY) == -1);
        assertTrue(this.shipService.nextMoveY(shipType, 2, currentY) == 1);
        assertTrue(this.shipService.nextMoveY(shipType, 3, currentY) == 1);

        currentY = 0;
        assertTrue(this.shipService.nextMoveY(shipType, 0, currentY) == 1);
        currentY = MAX_SIZE_SQUARE - 1;
        assertTrue(this.shipService.nextMoveY(shipType, 1, currentY) == -1);

        System.out.println(" ===== test nextMoveY() for ship type D passed  ==== ");
    }

    @Test
    public void testNextMoveXTypeP() {
        System.out.println(" ===== test nextMoveX() for ship type P started  ==== ");
        String shipType = "typeP";
        int currentX = 2;
        assertTrue(this.shipService.nextMoveX(shipType, 0, currentX) == 0);
        assertTrue(this.shipService.nextMoveX(shipType, 1, currentX) == 1);
        assertTrue(this.shipService.nextMoveX(shipType, 2, currentX) == 0);
        assertTrue(this.shipService.nextMoveX(shipType, 3, currentX) == -1);

        currentX = 0;
        assertTrue(this.shipService.nextMoveX(shipType, 3, currentX) == 1);
        currentX = MAX_SIZE_SQUARE - 1;
        assertTrue(this.shipService.nextMoveX(shipType, 1, currentX) == -1);

        System.out.println(" ===== test nextMoveX() for ship type P passed  ==== ");
    }

    @Test
    public void testNextMoveYTypeP() {
        System.out.println(" ===== test nextMoveY() for ship type P started  ==== ");
        String shipType = "typeP";
        int currentY = 2;
        assertTrue(this.shipService.nextMoveY(shipType, 0, currentY) == -1);
        assertTrue(this.shipService.nextMoveY(shipType, 1, currentY) == 0);
        assertTrue(this.shipService.nextMoveY(shipType, 2, currentY) == 1);
        assertTrue(this.shipService.nextMoveY(shipType, 3, currentY) == 0);

        currentY = 0;
        assertTrue(this.shipService.nextMoveY(shipType, 0, currentY) == 1);
        currentY = MAX_SIZE_SQUARE - 1;
        assertTrue(this.shipService.nextMoveY(shipType, 2, currentY) == -1);

        System.out.println(" ===== test nextMoveY() for ship type P passed  ==== ");
    }

}
