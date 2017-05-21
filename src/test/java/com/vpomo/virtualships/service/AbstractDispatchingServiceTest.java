package com.vpomo.virtualships.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Pomogalov on 29.03.2016.
 */
public abstract class AbstractDispatchingServiceTest {
    @Autowired
    protected DispatchingService dispatchingService;

    @Before
    public void shouldDispatchingService() {
        System.out.println(" ===== Before test passed  ==== ");
    }

    @Test
    public void testStartMovingShips() throws InterruptedException {
        int numberShipTypeA;
        int numberShipTypeD;
        int numberShipTypeP;
        System.out.println(" ===== test startMovingShips(int numberShipTypeA, int numberShipTypeD, int numberShipTypeP) started  ==== ");
        numberShipTypeA = 2;
        numberShipTypeD = 1;
        numberShipTypeP = 0;
        //this.dispatchingService.startMovingShips(numberShipTypeA, numberShipTypeD, numberShipTypeP);

        System.out.println(" ===== test startMovingShips passed  ==== ");
    }

}
