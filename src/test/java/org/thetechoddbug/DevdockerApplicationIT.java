package org.thetechoddbug;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DevdockerApplicationIT {

	private static HtmlUnitDriver driver;

	@Before
	public void init() throws Exception {
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void close() throws Exception {
		driver.close();
	}

	@Test
	public void enteringReservationPage_newReservationButtonIsEnabled() throws Exception {
		driver.get("http://127.0.0.1:8080/");
		Wait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
				TimeUnit.SECONDS);
		List<WebElement> refList = driver.findElements(By.tagName("a"));
		for (WebElement we : refList) {
			if ("Continue without updating".equals(we.getText())) {
				WebElement anchorToClick = waitFluent.until(ExpectedConditions.elementToBeClickable(we));
				Assert.assertTrue("Anchor to click for not supported browser page not present.",
						(waitFluent.until(ExpectedConditions.elementToBeClickable(we)) != null));
				anchorToClick.click();
			}
		}
	}
}
