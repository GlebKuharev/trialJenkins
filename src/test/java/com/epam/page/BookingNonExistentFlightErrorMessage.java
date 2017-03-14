package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by PC on 07.03.2017.
 */
public class BookingNonExistentFlightErrorMessage extends AbstractPage {
    private String expectedMessage = "Unfortunately we do not fly from Dubai, United Arab Emirates to Agadir, Morocco. However, we do fly from Dubai, United Arab Emirates to other destinations. Please change your destination and try again.";

    @FindBy(xpath = "//div[@class='notification-message notification-inline notification-error']/p")
    private WebElement actualMessage;

    public BookingNonExistentFlightErrorMessage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean checkErrorMessage(){
       wait(actualMessage);
       String message = actualMessage.getText();

       return message.equals(expectedMessage);
    }
}
