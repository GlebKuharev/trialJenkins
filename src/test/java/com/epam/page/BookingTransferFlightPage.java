package com.epam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.bean.FlightDetails;

public class BookingTransferFlightPage extends AbstractPage {	


	private String outboundFlightCheck;
	private String inboundFlightCheck;
	FlightDetails businessObject;

	private final static int FROM = 0; 
	private final static int TO = 1;

	@FindBy(xpath = "//*[@id='openJawRouteSelection_DepartureStationOutbound-input']")
	private WebElement outboundFromInput;

	@FindBy(xpath = "//*[@id='openJawRouteSelection_ArrivalStationOutbound-input']")
	private WebElement outboundToInput;

	@FindBy(xpath = "//*[@id='dateSelection_OutboundDate-datepicker']")
	private WebElement outboundDataInput;

	@FindBy(xpath = "//*[@id='openJawRouteSelection_DepartureStationInbound-input']")
	private WebElement inboundFromInput;

	@FindBy(xpath = "//*[@id='openJawRouteSelection_ArrivalStationInbound-input']")
	private WebElement inboundToInput;

	@FindBy(xpath = "//*[@id='dateSelection_InboundDate-datepicker']")
	private WebElement inboundDataInput;

	@FindBy(xpath = "//*[@id='booking-passengers-input']")
	private WebElement passengersInput;

	@FindBy(xpath = "//*[@id='flyingBlueSearch_FlyingBlueSearch']")
	private WebElement checkBox;

	@FindBy(xpath = "//*[@id='flights']/div/section/div[3]/div")
	private WebElement panel;

	@FindBy(xpath = "//div[contains(@class,'panel_section-button-container')]/button[@type='submit']")
	private WebElement searchButton;

	@FindBy(xpath = "(//button[@class='flight-result-button'])[1]")
	private WebElement outboundTimeButton;

	@FindBy(xpath = "(//button[@class='flight-result-button'])[2]")
	private WebElement inboundTimeButton;

	@FindBy(xpath = "//*[@name='next_button']")
	private WebElement nextButton;

	@FindBy(xpath = "//*[@id='flights']//ol/li")
	private WebElement airport;

	@FindBy(xpath = "//a[contains(@class,'summary-section')]")
	private WebElement buttonDetails;

	@FindBy(xpath = "//div[contains(@class,'HV-gu--bp0--y1-1 HV-gu--bp0--x1-2 HV-gu--bp0--80p')]")
	private WebElement title;

	@FindBy(xpath = "//*[contains(@class,'panel is-closed')]/div[2]/p[2]")
	private WebElement outboundFlight;

	@FindBy(xpath = "//*[contains(@class,'panel is-closed')]/div[5]/p[2]")
	private WebElement inboundFlight;

	@FindBy(xpath = "//section[contains(@class,'flight outbound')]//div[contains(@class,'margin-bottom-2rem margin-bottom-1d5rem--bp10')][1]//div[contains(@class,'selected')]")
	private WebElement outboundSelected;

	@FindBy(xpath = "//section[contains(@class,'flight inbound')]//div[contains(@class,'margin-bottom-2rem margin-bottom-1d5rem--bp10')][1]//div[contains(@class,'selected')]")
	private WebElement inboundSelected;

	public BookingTransferFlightPage (WebDriver driver, FlightDetails businessObject) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		this.businessObject = businessObject;		
	}

	public static String[] getAirportNameFromDetailField(String flight) {
		String[] lines = flight.trim().split("–");
		return lines;
	}	

	public boolean comparisonBookinResults() {
		String[] outbound = getAirportNameFromDetailField(outboundFlightCheck);
		String[] inbound = getAirportNameFromDetailField(inboundFlightCheck);

		if (outbound[FROM].contains(businessObject.getAirportOutboundFrom())
				&& outbound[TO].contains(businessObject.getAirportOutboundTo()) 
				&& inbound[FROM].contains(businessObject.getAirportInboundFrom())
				&& inbound[TO].contains(businessObject.getAirportInboundTo())) {	
		} else {
			System.out.println(outbound[FROM]);
			return false;
		}		
		return true;
	}

	public void bookingConnectionFlight() {

		outboundFromInput.clear();
		outboundFromInput.sendKeys(businessObject.getAirportOutboundFrom());
		airport.click();

		outboundToInput.clear();
		outboundToInput.sendKeys(businessObject.getAirportOutboundTo());
		airport.click();

		outboundDataInput.clear();
		outboundDataInput.sendKeys(businessObject.getDataOutbound());

		inboundFromInput.clear();
		inboundFromInput.sendKeys(businessObject.getAirportInboundFrom());
		airport.click();

		inboundToInput.clear();
		inboundToInput.sendKeys(businessObject.getAirportInboundTo());
		airport.click();

		inboundDataInput.clear();
		inboundDataInput.sendKeys(businessObject.getDataInbound());

		panel.click();
		wait(searchButton);
		searchButton.click();		

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", outboundTimeButton);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(outboundTimeButton));
		outboundTimeButton.submit();
		wait(outboundSelected);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inboundTimeButton);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(inboundTimeButton));
		inboundTimeButton.submit();
		wait(inboundSelected);

		buttonDetails.click();
		outboundFlightCheck = outboundFlight.getText();			
		inboundFlightCheck = inboundFlight.getText();

	}

}
