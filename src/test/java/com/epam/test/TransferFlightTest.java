package com.epam.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.bean.FlightDetails;
import com.epam.step.Steps;

import static com.epam.driver.DriverSingleton.closeDriver;

public class TransferFlightTest {

	 private Steps steps;
	 
	 private static String airportOutboundFrom = "Stockholm (Arlanda)";
	 private static String airportOutboundTo = "Eindhoven";
	 private static String dataOutbound = "7 Jul 2017";
	 private static String airportInboundFrom = "Eindhoven";
	 private static String airportInboundTo = "Antalya";
	 private static String dataInbound = "7 Jul 2017";	 

	    @BeforeMethod(description = "Init browser")
	    public void setUp()
	    {
	        steps = new Steps();
	        steps.initBrowser();
	    }
	 
	    @Test
	    public void checkTransferFlight() {

			FlightDetails businessObject = new FlightDetails();
	    	
	    	businessObject.setAirportOutboundFrom(airportOutboundFrom);
	    	businessObject.setAirportOutboundTo(airportOutboundTo);
	    	businessObject.setDataOutbound(dataOutbound);
	    	
	    	businessObject.setAirportInboundFrom(airportInboundFrom);
	    	businessObject.setAirportInboundTo(airportInboundTo);
	    	businessObject.setDataInbound(dataInbound);		    	
	    	
	    	Assert.assertTrue(steps.bookingTransferFlight(businessObject));
	    }

	    @AfterMethod(description = "Stop Browser")
	    public void stopBrowser()
	    {
			closeDriver();
	    }
	}