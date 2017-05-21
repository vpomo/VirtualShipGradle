package com.vpomo.virtualships.web;

import com.vpomo.virtualships.model.CellSquare;
import com.vpomo.virtualships.model.NumberShip;
import com.vpomo.virtualships.service.ControlValues;
import com.vpomo.virtualships.service.DispatchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Pomogalov on 04.04.2016.
 */
@Controller
public class DrawSquare {
    private static final Logger logger = LoggerFactory.getLogger(DrawSquare.class);
    private final DispatchingService dispatchingService;
    public ControlValues controlValues;

    @Autowired
    public DrawSquare(DispatchingService dispatchingService) {
        this.dispatchingService = dispatchingService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) throws InterruptedException {
        //dispatchingService.startMovingShips(1,2,1);
        return "/index";
    }

    @RequestMapping(value = "/restful/getall", method = RequestMethod.GET)
    public @ResponseBody ArrayList<CellSquare> getSquare() {
        return dispatchingService.getSquareToJSON();
    }

    @RequestMapping(value = "/restful/start", method = RequestMethod.POST)
    public void startMoving(@RequestBody NumberShip numberShip) throws InterruptedException {
        logger.info("Initialization model ...");
        if (controlValues == null) {
            this.controlValues = new ControlValues();
        }
        controlValues.stopMoving = false;
        logger.info("NumberShipTypeA=" + numberShip.getNumberShipTypeA());
        dispatchingService.startMovingShips(numberShip.getNumberShipTypeA(), numberShip.getNumberShipTypeD(), numberShip.getNumberShipTypeP(), controlValues);
    }

    @RequestMapping(value = "/restful/stop", method = RequestMethod.POST)
    public void stopMoving(@RequestBody boolean flagStop) throws InterruptedException  {
        controlValues.stopMoving = true;
    }

    @RequestMapping(value = "/restful/clear", method = RequestMethod.POST)
    public void clearSquare(@RequestBody boolean flagStop) throws InterruptedException  {
        dispatchingService.clearSquare();
        logger.info("Clearing square");
    }

}
