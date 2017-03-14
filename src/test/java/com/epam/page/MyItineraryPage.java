package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyItineraryPage extends AbstractPage {
	
	public MyItineraryPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
					
	@FindBy(xpath = "//h2[contains(text(), 'Flight number')]")
    private WebElement flightNo;
	
	@FindBy(xpath = "//h1[contains(text(), 'My itinerary')]")
    private WebElement headerName;
	
	
	public String getPageHeader() {
		wait (headerName);
		return headerName.getText();
	}
	
	public String getItineraryFlightNo() {
		wait(flightNo);
		return  flightNo.getText().replace("Flight number ", "");
	}

}
