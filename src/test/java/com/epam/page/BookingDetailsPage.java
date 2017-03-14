package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingDetailsPage extends AbstractPage {

	@FindBy(css = ".front")
    private WebElement totalAmountSum;
	
	@FindBy(xpath = "//div[contains(text(), 'Payment amount')]/parent::*/following-sibling::*//div[@class='amount']")
    private WebElement paymentAmountSum;	
	
	public BookingDetailsPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public Double getTotalSum() {
		wait(totalAmountSum);
		return parseStringPrice(totalAmountSum.getText());
	}
	
	public Double getPaymentAmount() {
		wait(paymentAmountSum);
		return parseStringPrice(totalAmountSum.getText());
	}		
}