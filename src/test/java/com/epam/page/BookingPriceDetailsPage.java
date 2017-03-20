package com.epam.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.bean.FlightDetails;


public class BookingPriceDetailsPage extends AbstractPage {

    private String priceForOnePersonDep;
    private String priceForOnePersonArriv;
    private String addPriceForTicket;
    private String totalPrice;

    @FindBy(xpath = "//button[@name='selectFlight.MarketFareKey']")
    private WebElement selectDepartureFlightButton;

    @FindBy(xpath = "(//section[@class='flight inbound']//button[@class='flight-result-button'])[1]")
    private WebElement selectArrivalFlightButton;

    @FindBy(xpath = "//button[@name='selectFlight.MarketFareKey']//div[@class='price ']")
    private WebElement priceDepartureForOnePerson;

    @FindBy(xpath = "//section[@class='flight inbound']//button[@name='selectFlight.MarketFareKey']//div[@class='price ']")
    private WebElement priceArrivalForOnePerson;

    @FindBy(xpath = "//button[@name='next_button']")
    private WebElement nextButton;

    @FindBy(xpath = "//td[3]/div/div/button")
    private WebElement selectPlusTicket;

    @FindBy(xpath = "//div[@class='is-hidden is-visible-block--bp25']//thead//th[last()-1]/span[2]")
    private WebElement priceForPlusTicketForOnePerson;

    @FindBy(css = ".back")
    private WebElement totalAmount;

    @FindBy(xpath = "//th[3]")
//    @FindBy(xpath = "(//section[@class='flight inbound']//button[@class='flight-result-button'])[1]")
    private WebElement waitElement;

    @FindBy(xpath = "//*[@id='flights']/div/div")
    private WebElement disableSelectDepAiroprt;

    @FindBy(xpath = "(//*[@id='flights'])[2]/div/div")
    private WebElement disableSelectArrAirport;

    @FindBy(xpath = "//td[3]/div/div/button[2]")
    private WebElement disableSelectTicket;

    public BookingPriceDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void fillBookForm() {
        
//    	scroll("scroll(0,100)");
//        wait(selectDepartureFlightButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectDepartureFlightButton);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectDepartureFlightButton));
        selectDepartureFlightButton.submit();
        wait(disableSelectDepAiroprt, "class", "selected");
        priceForOnePersonDep = priceDepartureForOnePerson.getText();

//        scroll("scroll(0,800)");
//        wait(selectArrivalFlightButton);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectArrivalFlightButton);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectArrivalFlightButton));
        selectArrivalFlightButton.submit();
        wait(disableSelectArrAirport, "class", "selected");
        priceForOnePersonArriv = priceArrivalForOnePerson.getText();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        wait(nextButton);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(nextButton));
        retryingFindClick(nextButton);
//        nextButton.submit();

        wait(waitElement);
        addPriceForTicket = priceForPlusTicketForOnePerson.getText();

        scroll("scroll(0,1000)");

        wait(selectPlusTicket);
        selectPlusTicket.click();
        wait(disableSelectTicket, "Selected");
        totalPrice = totalAmount.getText();
    }

    public boolean getTotalPrice(FlightDetails flightDetails) {
        double priceDep = parseStringPrice(priceForOnePersonDep);
//System.out.println("priceDep " + priceDep);
        double priceArriv = parseStringPrice(priceForOnePersonArriv);
//System.out.println("priceArriv " + priceArriv);
        double extraAmount = parseStringPrice(addPriceForTicket);
//        System.out.println("extraAmount " + extraAmount);
        double expectTotAmount = parseStringPrice(totalPrice);
//        System.out.println("expectTotAmount " + expectTotAmount);
//        System.out.println("No of passengers: " + flightDetails.getNumberOfPassengers());
        double realTotalAmount = (priceDep + priceArriv) * flightDetails.getNumberOfPassengers() + extraAmount * flightDetails.getNumberOfPassengers();
//        System.out.println("realTotalAmount " + realTotalAmount);
        if (realTotalAmount == expectTotAmount) {
            return true;
        }
        return false;
    }

}
