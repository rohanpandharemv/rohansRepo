package tripAdvisor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TripAdvisorAutomation {
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://www.tripadvisor.in/Hotel_Review-g35805-d114591-Reviews-Four_Seasons_Hotel_Chicago-Chicago_Illinois.html");

		pause(200);

		/*
		 * System.out.println(
		 * "----------------Clicking review link--------------------");
		 * 
		 * WebElement rev_link = driver.findElement(By.xpath(
		 * "//div[contains(@class,'heading_rating')]/div[contains(@class,'rs')]/a"
		 * ));
		 * 
		 * if(rev_link.isDisplayed()){ rev_link.click();
		 * System.out.println("Clicked review link"); pause(150); }
		 */

		System.out.println("----------------Names-----------------------");
		List<WebElement> names = driver
				.findElements(By
						.xpath("//div[contains(@class,'reviewSelector')]/div[contains(@class,'basic_review')]"
							  +"/div[contains(@class,'col1of2')]/div[contains(@class,'member_info')]"
							  +"/div[@class='memberOverlayLink']"
							  +"/div[contains(@class,'username')]/span"));

		for (WebElement webElement : names) {
			String name = webElement.getText().trim();
			System.out.println(name);
		}

		System.out.println("----------------Contents-----------------------");
		List<WebElement> contents = driver
				.findElements(By
						.xpath("//div[contains(@class,'reviewSelector')]/div[contains(@class,'basic_review')]"
				                +"/div[contains(@class,'col2of2')]/div[@class='innerBubble']"
								+"/div[@class='wrap']/div[contains(@class,'entry')]/p[@class='partial_entry']"));

		for (WebElement content : contents) {
			String data = content.getText();
			System.out.println(data + "\n");
		}

		System.out
				.println("----------------MentionDates-----------------------");
		List<WebElement> mentionDates = driver
				.findElements(By
						.xpath("//div[contains(@class,'reviewSelector')]/div[contains(@class,'basic_review')]"
				              +"/div[contains(@class,'col2of2')]/div[@class='innerBubble']/div[@class='wrap']"
							  +"/div[contains(@class,'reviewItemInline')]/span[contains(@class,'ratingDate')]"));

		for (WebElement dates : mentionDates) {
			String data = dates.getAttribute("title");
			System.out.println(data);
		}

		System.out.println("----------------ratings-----------------------");
		List<WebElement> ratings = driver
				.findElements(By
						.xpath("//div[contains(@class,'reviewSelector')]/div[contains(@class,'basic_review')]"
				               +"/div[contains(@class,'col2of2')]/div[@class='innerBubble']"
							   +"/div[@class='wrap']/div[contains(@class,'reviewItemInline')]"
				               +"/span[contains(@class,'rating_s')]/img"));

		for (WebElement rating : ratings) {
			String data = rating.getAttribute("alt").substring(0, 1).trim();
			System.out.println(data);
		}

		driver.close();
		driver.quit();
	}

	static void pause(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}