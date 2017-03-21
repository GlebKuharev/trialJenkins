package com.epam.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.bean.FlightDetails;

public class MainPage extends AbstractPage {

	private final String BASE_URL = "https://www.transavia.com/en-NL/home/";
	private FlightDetails businessObject;
	
	
	//test
	@FindBy(xpath = "html/body/header/nav/div[1]/div[1]/ul/li[4]/a")
	private WebElement serviceBtn;
	
	@FindBy(xpath = ".//*[@id='horizontal-sub-navigation-service']/div/div[2]/div/div[2]/div[2]/ul/li[1]/a")
	private WebElement handluggageBtn;	
	
	

	@FindBy(xpath = "//a[text()='Log in']")
	private WebElement loginBtn;

	@FindBy(id = "routeSelection_DepartureStation-input")
	private WebElement departureInput;

	@FindBy(id = "routeSelection_ArrivalStation-input")
	private WebElement arrivalInput;

	@FindBy(id = "booking-passengers-input")
	private WebElement passengersInput;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement adultsAddButton;

	@FindBy(xpath = "(//button[@type='button'])[4]")
	private WebElement childrenAddButton;

	@FindBy(xpath = "(//button[@type='submit'])[4]")
	private WebElement searchButton;

	@FindBy(id = "desktop")
	private WebElement form;

	@FindBy(css = ".close")
	private WebElement saveButton;
	
	@FindBy(xpath = "//*[@id='desktop']/section/div[3]/ul/li[2]/a")
	private WebElement addMultipleDestinations;
	
	
//test
	public void clickOnServiceBtn()
	{
		wait(serviceBtn);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(serviceBtn));
		serviceBtn.click();
		
		wait(handluggageBtn);
		handluggageBtn.click();
	}	
	
	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public MainPage(WebDriver driver, FlightDetails businessObject) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		this.businessObject = businessObject;
	}

	public void clickOnAddMulBtn()
	{
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(addMultipleDestinations));
		addMultipleDestinations.click();
	}
	
	public void clickOnLoginBtn()
	{
		wait(loginBtn);
		loginBtn.click();
	}

	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}

	public void fillBookForm() {
		wait(form);

		scroll("scroll(0,250)");

		wait(departureInput);

		departureInput.clear();
		departureInput.sendKeys(businessObject.getAirportDep());

		wait(arrivalInput);
		arrivalInput.clear();
		arrivalInput.sendKeys(businessObject.getAirportArriv());

		if (businessObject.getAirportArriv().contains("Agadir")) {
			List<WebElement> iElements = driver.findElements(By.xpath("//li[2]/ol/li"));
			for (WebElement elem : iElements) {
				if (elem.getText().equals(businessObject.getAirportArriv())) {
					scrollTo(elem);
					wait(elem);
					elem.click();
					break;
				}
			}
		}

		wait(passengersInput);
		passengersInput.click();

		wait(adultsAddButton);
		adultsAddButton.click();

		childrenAddButton.click();

		saveButton.click();

		form.submit();
	}

}
