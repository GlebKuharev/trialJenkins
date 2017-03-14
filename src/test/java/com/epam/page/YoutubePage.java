package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.bean.FlightDetails;

public class YoutubePage extends AbstractPage {

	FlightDetails businessObject;

	@FindBy(xpath = "//span[@id='eow-title']")
	private WebElement videoName;

	@FindBy(xpath = "//a[contains(@href,'/channel/UC3h3wgdF7xwDK38UldkLOiQ')]")
	private WebElement videoAutor;

	public YoutubePage(WebDriver driver, FlightDetails businessObject) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		this.businessObject = businessObject;
	}

	public void openPage(String url) {
		driver.switchTo().defaultContent();
		driver.navigate().to(url);
	}

	public String getStringVideoName() {

		wait(videoName);
		String name = videoName.getText();
		return name;
	}

	public String getStringVideoAutor() {

		wait(videoAutor);
		String autor = videoAutor.getText();
		return autor;
	}

	public boolean comparVideo() {
		String name = getStringVideoName();
		String autor = getStringVideoAutor();

		return name.contains(businessObject.getVideoName())
				&& autor.contains(businessObject.getCompanyName());
	}
}
