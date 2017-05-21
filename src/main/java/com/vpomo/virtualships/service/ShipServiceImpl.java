package com.vpomo.virtualships.service;

import com.vpomo.virtualships.model.Ship;
import com.vpomo.virtualships.model.Square;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.vpomo.virtualships.service.ControlValues.MAX_SIZE_SQUARE;


/**
 * Created by Pomogalov on 22.03.2016.
 */

@Service
public class ShipServiceImpl implements ShipService {
    private Square square;

    public ShipServiceImpl () {

    }

    public ShipServiceImpl (Square square) {
        this.square = square;
    }

    public synchronized void nextMove(Ship ship, Square square) {
        int currentCoordinateX, nextCoordinateX;
        int currentCoordinateY, nextCoordinateY;
        String typeShip;
        String colorShip = "##";
        String colorMarkPre = "##";
        String colorMarkMix = "##";
        String colorOldForShip;
        String colorOldForMark;
        int directionMoveShip = 10;
        Random nextMove = new Random();

        currentCoordinateX = ship.getCoordinateX();
        currentCoordinateY = ship.getCoordinateY();
        typeShip = ship.getTypeShip();

        switch (typeShip) {
            case "typeA":
                directionMoveShip = nextMove.nextInt(8);
                colorMarkPre = "mark_a";
                colorShip = "ship_a";
                break;
            case "typeD":
                directionMoveShip = nextMove.nextInt(4);
                colorMarkPre = "mark_d";
                colorShip = "ship_d";
                break;
            case "typeP":
                directionMoveShip = nextMove.nextInt(4);
                colorMarkPre = "mark_p";
                colorShip = "ship_p";
                break;
            default:
                break;
        }

        nextCoordinateX = currentCoordinateX + nextMoveX(typeShip, directionMoveShip, currentCoordinateX);
        nextCoordinateY = currentCoordinateY + nextMoveY(typeShip, directionMoveShip, currentCoordinateY);

        colorOldForShip = square.getColorCell(nextCoordinateX, nextCoordinateY);
        colorOldForMark = square.getPreviousColorCell(currentCoordinateX,currentCoordinateY);
        colorMarkMix = calculateColorMark(colorMarkPre, colorOldForMark);

        ship.setCoordinateX(nextCoordinateX);
        ship.setCoordinateY(nextCoordinateY);

        square.setColorCell(currentCoordinateX,currentCoordinateY, colorMarkMix);
        square.setNumberTimesCell(currentCoordinateX,currentCoordinateY,4);

        square.setPreviousColorCell(nextCoordinateX, nextCoordinateY, colorOldForShip);
        square.setColorCell(nextCoordinateX,nextCoordinateY, colorShip);
        square.setNumberTimesCell(nextCoordinateX,nextCoordinateY,5);
    }

    private String calculateColorMark(String colorNew, String colorOld) {
        String colorMix = "..";
		
        if (((colorNew.equals("mark_a")) & (colorOld.equals("mark_a"))) || ((colorNew.equals("mark_a")) & (colorOld.equals("..")))) {
            colorMix = "mark_a";
        }
        if (((colorNew.equals("mark_d")) & (colorOld.equals("mark_d"))) || ((colorNew.equals("mark_d")) & (colorOld.equals("..")))) {
            colorMix = "mark_d";
        }
        if (((colorNew.equals("mark_p")) & (colorOld.equals("mark_p"))) || ((colorNew.equals("mark_p")) & (colorOld.equals("..")))) {
            colorMix = "mark_p";
        }

        if (((colorNew.equals("mark_a")) & (colorOld.equals("mark_d"))) || ((colorNew.equals("mark_d")) & (colorOld.equals("mark_a")))) {
            colorMix = "mark_ad";
        }
        if (((colorNew.equals("mark_a")) & (colorOld.equals("mark_p"))) || ((colorNew.equals("mark_p")) & (colorOld.equals("mark_a")))) {
            colorMix = "mark_ap";
        }
        if (((colorNew.equals("mark_d")) & (colorOld.equals("mark_p"))) || ((colorNew.equals("mark_p")) & (colorOld.equals("mark_d")))) {
            colorMix = "mark_dp";
        }

        if (((colorNew.equals("mark_ad")) & (colorOld.equals("mark_a"))) || ((colorNew.equals("mark_a")) & (colorOld.equals("mark_ad")))) {
            colorMix = "mark_ada";
        }
        if (((colorNew.equals("mark_ad")) & (colorOld.equals("mark_d"))) || ((colorNew.equals("mark_d")) & (colorOld.equals("mark_ad")))) {
            colorMix = "mark_add";
        }
        if (((colorNew.equals("mark_ad")) & (colorOld.equals("mark_p"))) || ((colorNew.equals("mark_p")) & (colorOld.equals("mark_ad")))) {
            colorMix = "mark_adp";
        }

        if (((colorNew.equals("mark_dp")) & (colorOld.equals("mark_a"))) || ((colorNew.equals("mark_a")) & (colorOld.equals("mark_dp")))) {
            colorMix = "mark_dpa";
        }
        if (((colorNew.equals("mark_dp")) & (colorOld.equals("mark_d"))) || ((colorNew.equals("mark_d")) & (colorOld.equals("mark_dp")))) {
            colorMix = "mark_dpd";
        }
        if (((colorNew.equals("mark_dp")) & (colorOld.equals("mark_p"))) || ((colorNew.equals("mark_p")) & (colorOld.equals("mark_dp")))) {
            colorMix = "mark_dpp";
        }

        if (((colorNew.equals("mark_ap")) & (colorOld.equals("mark_a"))) || ((colorNew.equals("mark_a")) & (colorOld.equals("mark_ap")))) {
            colorMix = "mark_apa";
        }
        if (((colorNew.equals("mark_ap")) & (colorOld.equals("mark_d"))) || ((colorNew.equals("mark_d")) & (colorOld.equals("mark_ap")))) {
            colorMix = "mark_apd";
        }
        if (((colorNew.equals("mark_ap")) & (colorOld.equals("mark_p"))) || ((colorNew.equals("mark_p")) & (colorOld.equals("mark_ap")))) {
            colorMix = "mark_app";
        }

        return colorMix;
    }

/**
 * directionMovement - variants of the ship movements for typeA
 * 0 - Up + Left; 1 - Up + Middle; 2- //Up + Right
 * 3 - Horizontally + Right;
 * 4 - Down + Right; 5 - Down + Middle; 6 - Down + Left;
 * 7 - Horizontally + Left;
 *
 * directionMovement - variants of the ship movements for typeD
 * 0 - Up + Left; 1- //Up + Right
 * 2 - Down + Right; 3 - Down + Left;
 *
 * directionMovement - variants of the ship movements for typeP
 * 0 - Up + Middle;
 * 1 - Horizontally + Right;
 * 2 - Down + Middle;
 * 3 - Horizontally + Left;
 *
 */
    public int nextMoveX(String typeShip, int directionMovement, int currentX) {
        int moveX = 0;
        if (typeShip.equals("typeA")) {
            switch (directionMovement) {
                case 0:
                case 6:
                case 7:
                    if (currentX == 0){
                        moveX = 1;
                    } else {
                        moveX = -1;
                    }
                    break;
                case 1:
                case 5:
                    moveX = 0;
                    break;
                case 2:
                case 3:
                case 4:
                    if (currentX == (MAX_SIZE_SQUARE-1)){
                        moveX = -1;
                    } else {
                        moveX = 1;
                    }
                    break;
                default:
                    break;
            }
        }
        if (typeShip.equals("typeD")) {
            switch (directionMovement) {
                case 0:
                case 3:
                    if (currentX == 0){
                        moveX = 1;
                    } else {
                        moveX = -1;
                    }
                    break;
                case 1:
                case 2:
                    if (currentX == (MAX_SIZE_SQUARE-1)){
                        moveX = -1;
                    } else {
                        moveX = 1;
                    }
                    break;
                default:
                    break;
            }
        }
        if (typeShip.equals("typeP")) {
            switch (directionMovement) {
                case 0:
                case 2:
                    moveX = 0;
                    break;
                case 1:
                    if (currentX == (MAX_SIZE_SQUARE-1)){
                        moveX = -1;
                    } else {
                        moveX = 1;
                    }
                    break;
                case 3:
                    if (currentX == 0){
                        moveX = 1;
                    } else {
                        moveX = -1;
                    }
                    break;
                default:
                    break;
            }
        }
        return moveX;
    }

    public int nextMoveY(String typeShip, int directionMovement, int currentY) {
        int moveY = 0;
        if (typeShip.equals("typeA")) {
            switch (directionMovement) {
                case 0:
                case 1:
                case 2:
                    if (currentY == 0){
                        moveY = 1;
                    } else {
                        moveY = -1;
                    }
                    break;
                case 3:
                case 7:
                    moveY = 0;
                    break;
                case 4:
                case 5:
                case 6:
                    if (currentY == (MAX_SIZE_SQUARE-1)){
                        moveY = -1;
                    } else {
                        moveY = 1;
                    }
                    break;
                default:
                    break;
            }
        }
        if (typeShip.equals("typeD")) {
            switch (directionMovement) {
                case 0:
                case 1:
                    if (currentY == 0){
                        moveY = 1;
                    } else {
                        moveY = -1;
                    }
                    break;
                case 2:
                case 3:
                    if (currentY == (MAX_SIZE_SQUARE-1)){
                        moveY = -1;
                    } else {
                        moveY = 1;
                    }
                    break;
                default:
                    break;
            }
        }
        if (typeShip.equals("typeP")) {
            switch (directionMovement) {
                case 1:
                case 3:
                    moveY = 0;
                    break;
                case 2:
                    if (currentY == (MAX_SIZE_SQUARE-1)){
                        moveY = -1;
                    } else {
                        moveY = 1;
                    }
                    break;
                case 0:
                    if (currentY == 0){
                        moveY = 1;
                    } else {
                        moveY = -1;
                    }
                    break;
                default:
                    break;
            }
        }
        return moveY;
    }
}
