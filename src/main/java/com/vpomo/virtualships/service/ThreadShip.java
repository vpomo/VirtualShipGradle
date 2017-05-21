package com.vpomo.virtualships.service;

import com.vpomo.virtualships.model.Ship;
import com.vpomo.virtualships.model.Square;

import java.util.Random;

/**
 * Created by Pomogalov on 29.03.2016.
 */
public class ThreadShip implements Runnable {
    private String name;
    private Square square;
    private Thread thread;
    private ControlValues controlValues;
    private Ship ship;
    private ShipServiceImpl shipService;

    public ThreadShip (String threadName, String typeShip, ControlValues controlValues, Square square, int numberTypeShip, int numberThread) {
        this.name = threadName;
        this.square = square;
        this.controlValues = controlValues;
        this.ship = new Ship(typeShip, square);
        this.shipService = new ShipServiceImpl(square);

        thread = new Thread(this, name);
        controlValues.arrayThreadShip[numberTypeShip][numberThread] = thread;
        thread.start();
        System.out.println("Thread " + name + " starting ...");
    }

    @Override
    public void run(){
        Random nextSpeed = new Random();
        int ratio;
        try {
                while (! controlValues.stopMoving) {
                    if (ship != null) {
                        shipService.nextMove(ship, square);
                    }
                    ratio = nextSpeed.nextInt(10);
                    thread.sleep(500 + ratio*100);
                }
        } catch (InterruptedException e) {
            System.out.println("Thread " + name + "interrupted");
        }
        System.out.println("Thread " + name + " stopped");
    }

}
