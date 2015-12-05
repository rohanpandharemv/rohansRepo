package booking;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BookingAutomation {
	public static void main(String[] args) throws InterruptedException {
		// WebDriver driver = new FirefoxDriver();

		DesiredCapabilities sCaps = new DesiredCapabilities();
		sCaps.setJavascriptEnabled(true);
		sCaps.setCapability("takesScreenshot", false);
		sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] { "--web-security=false",
		        "--ssl-protocol=any", "--ignore-ssl-errors=true", "--webdriver-loglevel=INFO" });

		WebDriver driver = new PhantomJSDriver(sCaps);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		// WebDriver driver = new ChromeDriver();
		// WebDriver driver = new HtmlUnitDriver();

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://www.booking.com/reviewlist.en-us.html?cc1=us;dist=1;pagename=the-ritz-carlton-marina-del-rey;type=total;offset=0;rows=20;sort=f_recent_desc");

		Thread.sleep(2000);

		String parentXpath = "//ul[@class='review_list']/li[contains(@class,'review_item')]";
		String namesXpath = ".//div[@class='review_item_reviewer']/h4[1] | .//div[@class='review_item_reviewer']/div[1]";
		String contentXpath = ".//div[@class='review_item_review']/div[contains(@class,'lang_ltr')]/div[@class='review_item_review_content']";
		// String ratingXpath =
		// ".//div[@class='review_item_review']/div[contains(@class,'review_item_review_score')]";
		String ratingXpath = ".//div[@class='review_item_review']/div[contains(@class,'review_item_review_score')]";
		String mentionDateXpath = ".//div[@class='review_item_review']/div[contains(@class,'review_item_review_container')]/div[@class='review_item_review_header']/div[@class='review_item_header_score_container']/div[@class='review_item_review_score']";

		/*
		 * review_xpath=//ul[@class=
		 * 'review_list']/li[contains(@class,'review_item')]
		 * content_xpath=.//div[@class=
		 * 'review_item_review']/div[contains(@class,'lang_ltr')]/div[@class='review_item_review_content']
		 * mentionTime_xpath=.//p
		 * author_xpath=.//div[@class='review_item_reviewer']/div[1]
		 * rating_xpath
		 * =.//div/div/div/div/div[@class='review_item_review_score']
		 */

		Integer count = 0;
		List<WebElement> parent = driver.findElements(By.xpath(parentXpath));

		System.out.println("Parent content size ==========>>>" + parent.size()
				+ "\n\n");

		System.out.println("----------------Names-----------------------");

		for (WebElement webElement : parent) {
			String name = webElement.findElement(By.xpath(namesXpath))
					.getText();
			System.out.println(name);

			System.out.println("=================");
			
			
			String rating = webElement.findElement(By.xpath(ratingXpath))
					.getText();
			System.out.println(rating);

		}
	}
}

/*
 * System.out.println("----------------Contents-----------------------");
 * 
 * for (WebElement content : parent) { String data =
 * content.findElement(By.xpath(contentXpath)).getText();
 * System.out.println(++count +" "+ data + "\n"); }
 * 
 * 
 * 
 * 
 * System.out.println("----------------MentionDates-----------------------");
 * 
 * count = 0; for (WebElement dates : parent) { String data =
 * dates.findElement(By.xpath(mentionDateXpath)).getText();
 * System.out.println(++count + " " +data); }
 * 
 * 
 * 
 * 
 * System.out.println(
 * "----------------ratings by parent format-----------------------");
 * 
 * count = 0 ; for (WebElement rating : parent) { String data =
 * rating.findElement(By.xpath(ratingXpath)).getText();
 * System.out.println(++count + " " +data); }
 * 
 * 
 * 
 * 
 * System.out.println(
 * "----------------ratings by direct xpath format-----------------------");
 * 
 * List<WebElement> ratings = driver.findElements(By.xpath(
 * "//ul[@class='review_list']/li[contains(@class,'review_item')]/div[@class='review_item_review']/div[contains(@class,'lang_ltr')]/div[@class='review_item_review_header']/div[@class='review_item_header_score_container']/div[@class='review_item_review_score']"
 * )); count = 0 ; for (WebElement rating : parent) { String data =
 * rating.findElement(By.xpath(ratingXpath)).getText();
 * System.out.println(++count + " " +data); }
 * 
 * driver.close(); driver.quit(); }
 * 
 * static void pause(int seconds) { try { Thread.sleep(seconds); } catch
 * (Exception ex) { ex.printStackTrace(); } }
 */

/*
 * List<WebElement> contents = driver.findElements(By.xpath(contentXpath));
 * count = 0;
 * 
 * List<WebElement> mentionDates = driver.findElements(By
 * .xpath(mentionDateXpath));
 */