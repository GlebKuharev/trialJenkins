package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.bean.FlightDetails;


public class BookingPriceDetailsPage extends AbstractPage {

    private String priceForOnePersonDep;
    private String priceForOnePersonArriv;
    private String addPriceForTicket;
    private String totalPrice;

    @FindBy(xpath = "//button[@name='selectFlight.MarketFareKey']")
    private WebElement selectDepartureFlightButton;

    @FindBy(xpath = "//form[@id='flights']/div/button")
    private WebElement selectArrivalFlightButton;

    @FindBy(xpath = "//button[@name='selectFlight.MarketFareKey']//div[@class='price ']")
    private WebElement priceDepartureForOnePerson;

    @FindBy(xpath = "(//button[@name='selectFlight.MarketFareKey'])[2]//div[@class='price ']")
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
        scroll("scroll(0,100)");
        wait(selectDepartureFlightButton);
        selectDepartureFlightButton.click();
        wait(disableSelectDepAiroprt, "class", "selected");
        priceForOnePersonDep = priceDepartureForOnePerson.getText();

        scroll("scroll(0,800)");

        wait(selectArrivalFlightButton);
        selectArrivalFlightButton.click();
        wait(disableSelectArrAirport, "class", "selected");
        priceForOnePersonArriv = priceArrivalForOnePerson.getText();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait(nextButton);
        nextButton.submit();

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
        double priceArriv = parseStringPrice(priceForOnePersonArriv);
        double extraAmount = parseStringPrice(addPriceForTicket);
        double expectTotAmount = parseStringPrice(totalPrice);
        double realTotalAmount = (priceDep + priceArriv) * flightDetails.getNumberOfPassengers() + extraAmount * flightDetails.getNumberOfPassengers();
        if (realTotalAmount == expectTotAmount) {
            return true;
        }
        return false;
    }

}
