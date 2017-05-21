package com.vpomo.virtualships.service;

import com.vpomo.virtualships.model.CellSquare;
import com.vpomo.virtualships.model.Square;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.vpomo.virtualships.service.ControlValues.MAX_NUMBER_SHIPS;

/**
 * Created by Pomogalov on 25.03.2016.
 */
@Service
public class DispatchingServiceImpl implements DispatchingService {
    public ControlValues controlValues;

    public void startMovingShips(int numberShipTypeA, int numberShipTypeD, int numberShipTypeP, ControlValues controlValues) throws InterruptedException {
        this.controlValues = controlValues;
        String threadName = "";

        if (controlValues.square == null) {
            controlValues.square = new Square();
        } else {
        }

        if ((numberShipTypeA > 0) & (numberShipTypeA < MAX_NUMBER_SHIPS) ) {
           for (int n = numberShipTypeA; n > 0; n--) {
               threadName = "typeA-№" + n;
               new ThreadShip(threadName, "typeA", this.controlValues, controlValues.square, 0, n);
           }
        }
        if ((numberShipTypeD > 0) & (numberShipTypeD < MAX_NUMBER_SHIPS)) {
            for (int n = numberShipTypeD; n > 0; n--) {
                threadName = "typeD-№" + n;
                new ThreadShip(threadName, "typeD", this.controlValues, controlValues.square, 1, n);
            }
        }
        if ((numberShipTypeP > 0) & (numberShipTypeP < MAX_NUMBER_SHIPS)) {
            for (int n = numberShipTypeP; n > 0; n--) {
                threadName = "typeP-№" + n;
                new ThreadShip(threadName, "typeP", this.controlValues, controlValues.square, 2, n);
            }
        }

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrrupted");
        }

        //controlValues.stopMoving = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < MAX_NUMBER_SHIPS; j++){
                if (controlValues.arrayThreadShip[i][j] != null) {
                    controlValues.arrayThreadShip[i][j].join();
                }
            }
        }


        synchronized (controlValues.square) {
            System.out.println("Square = " + controlValues.square.toString());
        }

    }

    public synchronized ArrayList<CellSquare> getSquareToJSON() {
        return controlValues.square.getJSONSquare();
    }

    public void clearSquare() {
        controlValues.square.clearSquare();
    }
}
