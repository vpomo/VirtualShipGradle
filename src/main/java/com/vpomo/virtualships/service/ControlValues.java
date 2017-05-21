package com.vpomo.virtualships.service;

import com.vpomo.virtualships.model.Square;

/**
 * Created by Pomogalov on 31.03.2016.
 */
public class ControlValues {
    public static final int MAX_NUMBER_SHIPS = 7;
    public static final int MAX_SIZE_SQUARE = 40;


    public static volatile boolean stopMoving = false;
    public static volatile boolean setValue = true;

    public static volatile Thread arrayThreadShip[][] = new Thread[3][MAX_NUMBER_SHIPS];
    public static volatile Square square;
}
