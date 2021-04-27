package FlipKart;
//trial for github
//onkar kahate
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup extends Base{
	//static WebDriver driver; // declaring global WebDriver variable

	public DriverSetup() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static WebDriver getDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\geckodriver.exe");

			ProfilesIni settings = new ProfilesIni();
			FirefoxProfile profile = settings.getProfile("selenium");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver(firefoxOptions);

			// driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\msedgedriver.exe");

			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\operadriver.exe");

			driver = new OperaDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS); // page load time out
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS); //No such element found
		String baseUrl = "https://www.flipkart.com";
		driver.get(baseUrl);
		return driver;
	}
}
