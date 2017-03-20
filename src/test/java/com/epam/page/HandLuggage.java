package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandLuggage extends AbstractPage {

	@FindBy(xpath = ".//*[@id='skip-links']/ul/li[5]/a")
	private WebElement clickOpenVideoLink;

	@FindBy(xpath = "//a[contains(@class,'ytp-title-link yt-uix-sessionlink')]")
	private WebElement clickYoutubeLinck;

	@FindBy(xpath = ".//*[@id='top']/div/div[9]/section/div/div/div/div[2]/div/p/iframe")
	private WebElement iframe;

	@FindBy(xpath = "//p/iframe[@src='//www.youtube.com/embed/fQMuhniqWAg']")
	private WebElement videoName;

	@FindBy(xpath = ".//*[@id='ytp-share-panel']/div/a")
	private WebElement linkVideo;

	@FindBy(xpath = ".//*[@id='eow-title']")
	private WebElement link;

	@FindBy(xpath = "//button[contains(@class,'ytp-button ytp-share-button')]")
	private WebElement linkButton;

	public HandLuggage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public String getLink() {
		
		wait(linkVideo);
		String link = linkVideo.getText();
		return link;
	}

	public void openVideoPage() {

//		wait(clickOpenVideoLink);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(clickOpenVideoLink));
		clickOpenVideoLink.click();

		driver.switchTo().frame(iframe);
		wait(linkButton);
		linkButton.click();
	}
}