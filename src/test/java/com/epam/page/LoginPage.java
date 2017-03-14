package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.bean.FlightDetails;
import com.epam.bean.User;

public class LoginPage extends AbstractPage {
	
	public LoginPage(WebDriver driver, FlightDetails bo)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(id = "retrieveBookingByLastname_RecordLocator")
    private WebElement bookingNoFld;
	
	@FindBy(id = "retrieveBookingByLastname_LastName")
    private WebElement lastNameFld;
	
	@FindBy(id = "retrieveBookingByLastname_FlightDate-datepicker")
    private WebElement flightDateFld;

	@FindBy(xpath = "//button[text()='View booking']")
    private WebElement viewBookingBtn;
	
	@FindBy(id="access-booking")
	private WebElement form;
	
	
	public void login(FlightDetails bo)
	{
		wait(bookingNoFld);
		bookingNoFld.sendKeys(User.BOOKING_NO);
		wait(lastNameFld);
		lastNameFld.sendKeys(User.LAST_NAME);
		wait(flightDateFld);
		flightDateFld.sendKeys(bo.getFlightDate());
		form.click();
		form.submit();
	}

}
