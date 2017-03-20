package com.epam.step;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.epam.bean.FlightDetails;
import com.epam.driver.DriverSingleton;
import com.epam.page.BookingDetailsPage;
import com.epam.page.BookingNonExistentFlightErrorMessage;
import com.epam.page.BookingOverviewPage;
import com.epam.page.BookingPriceDetailsPage;
import com.epam.page.BookingTransferFlightPage;
import com.epam.page.LoginPage;
import com.epam.page.MainPage;
import com.epam.page.MyItineraryPage;
import com.epam.page.HandLuggage;
import com.epam.page.YoutubePage;

public class Steps {
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void openMainPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
	}

	public void doLogin(FlightDetails bo) {

		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnLoginBtn();

		LoginPage loginPage = new LoginPage(driver, bo);
		loginPage.login(bo);
	}

	public boolean findSingleFlight() {
		return false;
	}

	public void fillBookPage(FlightDetails businessObject) {
		MainPage mainPage = new MainPage(driver, businessObject);
		mainPage.openPage();
		mainPage.fillBookForm();
	}

	public boolean getTotalTicketsSum(FlightDetails flightDetails) {
		BookingPriceDetailsPage bookingPriceDetailsPage = new BookingPriceDetailsPage(
				driver);
		bookingPriceDetailsPage.fillBookForm();

		return bookingPriceDetailsPage.getTotalPrice(flightDetails);
	}

	public void openMyItineraryPage() {

		BookingOverviewPage bookingOverviewPage = new BookingOverviewPage(
				driver);
		bookingOverviewPage.openMyItineraryPage();
	}

	public String getPageHeader() {

		MyItineraryPage myIPage = new MyItineraryPage(driver);
		return myIPage.getPageHeader();
	}

	public String getItineraryFlightNo() {

		MyItineraryPage myIPage = new MyItineraryPage(driver);
		return myIPage.getItineraryFlightNo();
	}

	public String getBookingFlightNo() {
		BookingOverviewPage bookingOverviewPage = new BookingOverviewPage(
				driver);
		return bookingOverviewPage.getFlightNo();
	}

	public void openBookingDetailsPage() {
		BookingOverviewPage bookingOverviewPage = new BookingOverviewPage(
				driver);
		bookingOverviewPage.openBookingDetailsPage();
	}

	public double getPaymentAmount() {
		BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);
		return bookingDetailsPage.getPaymentAmount();
	}

	public double getTotalSum() {
		BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);
		return bookingDetailsPage.getTotalSum();
	}

	public boolean checkNonExistentFlight() {
		BookingNonExistentFlightErrorMessage bookingErrorMessage = new BookingNonExistentFlightErrorMessage(
				driver);

		return bookingErrorMessage.checkErrorMessage();
	}

	public boolean bookingTransferFlight(FlightDetails businessObject) {
		
		BookingTransferFlightPage btf = new BookingTransferFlightPage(driver, businessObject);
		MainPage mainPage = new MainPage(driver);

		mainPage.openPage();
		mainPage.clickOnAddMulBtn();
		btf.bookingConnectionFlight();

		return btf.comparisonBookinResults();
	}

	public boolean handLuggageInfoVideo(FlightDetails businessObject) {
		MainPage mainPage = new MainPage(driver);		
		mainPage.openPage();
		mainPage.clickOnServiceBtn();
		
		HandLuggage handLuggage = new HandLuggage(driver);	
		handLuggage.openVideoPage();
		String link = handLuggage.getLink();
		YoutubePage youtubePage = new YoutubePage(driver, businessObject);
//		youtubePage.openPage(handLuggage.getLink());
		youtubePage.openPage(link);

		return youtubePage.comparVideo();

	}
}