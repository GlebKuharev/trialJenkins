package com.epam.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.bean.FlightDetails;
import com.epam.step.Steps;

import static com.epam.driver.DriverSingleton.closeDriver;

/**
 * Created by PC on 01.03.2017.
 */
public class TotalAmountFlightTest {

    private Steps steps;


    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test
    public void checkTotalAmount()
    {
        FlightDetails businessObject = new FlightDetails();
        businessObject.setAirportDep("London (Luton), United Kingdom");
        businessObject.setAirportArriv("Paris (Orly), France");
        businessObject.setNumberOfPassengers(3);

        steps.fillBookPage(businessObject);
        Assert.assertTrue(steps.getTotalTicketsSum(businessObject));
    }
    
    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        closeDriver();
    }
}
