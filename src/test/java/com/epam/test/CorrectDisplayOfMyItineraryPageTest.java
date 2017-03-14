package com.epam.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.bean.FlightDetails;
import com.epam.step.Steps;

import static com.epam.driver.DriverSingleton.closeDriver;


public class CorrectDisplayOfMyItineraryPageTest {

    private Steps steps;

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }
 
    @Test
    public void checkMyItinerary() {
    	
    	String bookingFlightNo;
    	String itineraryFlightNo;

        FlightDetails businessObject = new FlightDetails();
    	businessObject.setFlightDate("9 June 2016");

    	steps.openMainPage();
    	steps.doLogin(businessObject);
    	bookingFlightNo = steps.getBookingFlightNo();
    	steps.openMyItineraryPage();
    	itineraryFlightNo = steps.getItineraryFlightNo();
    	
    	Assert.assertTrue(steps.getPageHeader().equals("My itinerary"));
    	Assert.assertTrue(bookingFlightNo.equals(itineraryFlightNo));
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
      closeDriver();
    }
}
