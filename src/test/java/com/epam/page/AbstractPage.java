package com.epam.page;

import org.openqa.selenium.JavascriptExecutor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	protected WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

	protected void wait(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void wait(WebElement element, String attribute, String value) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.attributeContains(element, attribute,
				value));
	}

	protected void wait(WebElement element, String text) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	protected void scroll(String pix) {
		((JavascriptExecutor) driver).executeScript(pix);
	}

	protected void scroll(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
	}

	protected double parseStringPrice(String price) {
		StringBuilder sb = new StringBuilder(price);
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(sb);
		while (m.find()) {
			sb = new StringBuilder(sb.substring(m.start(), m.end()));
			break;
		}
		double number = Double.parseDouble(sb.toString());
		return number;
	}
	
}
