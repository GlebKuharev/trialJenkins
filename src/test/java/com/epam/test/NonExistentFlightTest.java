package com.epam.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.bean.FlightDetails;
import com.epam.step.Steps;

import static com.epam.driver.DriverSingleton.closeDriver;

/**
 * Created by PC on 07.03.2017.
 */
public class NonExistentFlightTest {
    private Steps steps;


    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test
    public void nonExistentFlight()
    {
        FlightDetails businessObject = new FlightDetails();
        businessObject.setAirportDep("Dubai, United Arab Emirates");
        businessObject.setAirportArriv("Agadir, Morocco");

        steps.fillBookPage(businessObject);
        Assert.assertTrue(steps.checkNonExistentFlight());
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
       closeDriver();
    }


}
