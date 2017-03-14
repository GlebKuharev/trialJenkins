package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingOverviewPage extends AbstractPage {
	
	public BookingOverviewPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath = "(//a/text()[contains(., 'Booking details')]/parent::*)[1]")
    private WebElement bookingDetailsBtn;
	
	@FindBy(xpath = "//h5[contains(text(), 'Flight number')]//following-sibling::*[@class='flight-info_value']")
    private WebElement flightNo;
	
	@FindBy(xpath = "//a/text()[contains(., 'Share your itinerary')]/parent::*")
    private WebElement shareItineraryBtn;
	
	@FindBy(id = "share-itinerary-content")
    private WebElement itineraryLink;
	
	
	public void openBookingDetailsPage() {
		wait(bookingDetailsBtn);
		bookingDetailsBtn.click();
	}
	
	public String getFlightNo() {
		
		return flightNo.getText();
	}
	
	public void openMyItineraryPage() {
		
		wait(shareItineraryBtn);
		shareItineraryBtn.click();
		wait(itineraryLink);
		String iLink = itineraryLink.getAttribute("value");
		driver.navigate().to(iLink);
	}

}
