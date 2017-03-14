package com.epam.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.bean.FlightDetails;
import com.epam.step.Steps;

import static com.epam.driver.DriverSingleton.closeDriver;


public class VideoTest {

	private Steps steps;
	private static String companyName = "Transavia";
	private static String videoName = "Luggage";

	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void checkVideoLinck() {
		
		FlightDetails businessObject = new FlightDetails();
		
		businessObject.setCompanyName(companyName);
		businessObject.setVideoName(videoName);
		
		Assert.assertTrue(steps.handLuggageInfoVideo(businessObject));
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		closeDriver();
	}
}