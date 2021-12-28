package NopCommerceTestingPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NopCommerceTestingCls {

	static public WebDriver driver = new ChromeDriver();

	public static void main(String[] args) {
		driver.get("https://admin-demo.nopcommerce.com/");
		assertPageUrl("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

		driver.close();
	}

	public static void assertPageUrl(String expectedUrl) {
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, expectedUrl);
	}

}
