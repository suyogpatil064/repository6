package FlipKart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class homePage extends Base{

	
	private static By loginCloseButton = By.cssSelector("button[class='_2KpZ6l _2doB4z']");
	private static By searchBar = (By.name("q"));
	
	// close the login pop-up if it appears
			public static void closeLogin() {
				// declaring a list of close button class that can be checked whether empty or not
				List<WebElement> isPresentList = driver.findElements(loginCloseButton);
				if (isPresentList.size() != 0) {
					driver.findElement(loginCloseButton).click();
					driver.findElement(searchBar).click();
				}
			}

			// enter the search query in search box
			public static void enterSearch(String searchQuery) throws InterruptedException {
				
				driver.findElement(searchBar).clear();
//				Thread.sleep(2000);
				driver.findElement(searchBar).sendKeys(Keys.CONTROL+"a");
				driver.findElement(searchBar).sendKeys(Keys.DELETE);
				driver.findElement(searchBar).sendKeys(searchQuery + Keys.ENTER);
				titleTest.log(Status.INFO, "Search query for " + searchQuery + " entered");
			}
}
