package com.vpomo.virtualships.service;

import com.vpomo.virtualships.model.CellSquare;

import java.util.ArrayList;

/**
 * Created by Pomogalov on 25.03.2016.
 */
public interface DispatchingService {
    void startMovingShips(int numberShipTypeA, int numberShipTypeD, int numberShipTypeP, ControlValues controlValues) throws InterruptedException;
    void clearSquare();
    ArrayList<CellSquare> getSquareToJSON();
}
