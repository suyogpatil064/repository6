package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class seleniumUtils {
	
public static WebDriver driver;
	
	/* Click methods */
	public static void clickById(String id) {
		driver.findElement(By.id(id)).click();
	}
	public static void clickByXpath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	public static void clickByClass(String className) {
		driver.findElement(By.className(className)).click();
	}
	public static void clickByText(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}
	
	/* Text-box methods */
	public static void textById(String id, String text) {
		driver.findElement(By.id(id)).sendKeys(text);
	}
	public static void textByXpath(String xpath, String text) {
		driver.findElement(By.xpath(xpath)).sendKeys(text);
	}
	public static void textByClass(String className, String text) {
		driver.findElement(By.className(className)).sendKeys(text);
	}
	
	/* Select methods for checkbox and radio button */
	public static void selectById(String id) {
		driver.findElement(By.id(id)).click();
	}
	public static void selectByXpath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	public static void selectByClass(String className) {
		driver.findElement(By.className(className)).click();
	}
	
	/* Select methods drop-down */
	public static Select selectById(String id, String visibleText) {
		Select selectObj = new Select(driver.findElement(By.id(id)));
		selectObj.selectByVisibleText(visibleText);
		return selectObj;
	}
	public static Select selectByXpath(String xpath, String visibleText) {
		Select selectObj = new Select(driver.findElement(By.xpath(xpath)));
		selectObj.selectByVisibleText(visibleText);
		return selectObj;
	}
	public static Select selectByClass(String className, String visibleText) {
		Select selectObj = new Select(driver.findElement(By.className(className)));
		selectObj.selectByVisibleText(visibleText);
		return selectObj;
	}
	
	/* driver navigation methods */
	public static void back() {
		driver.navigate().back();
	}
	public static void refresh() {
		driver.navigate().refresh();
	}
	public static void goTo(String url) {
		driver.navigate().to(url);
	}
	public static void forward() {
		driver.navigate().forward();
	}
	
}
