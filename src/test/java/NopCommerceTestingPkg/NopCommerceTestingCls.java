package NopCommerceTestingPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class NopCommerceTestingCls {

	static public WebDriver driver = new ChromeDriver();
	static public Actions actionProvider = new Actions(driver);

	public static void main(String[] args) {
		driver.get("https://admin-demo.nopcommerce.com/");
		driver.manage().window().maximize();
		
		assertPageUrl("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		assertPageTitle("Your store. Login");

		Boolean loginButtonExists = driver.findElements(By.className("login-button")).size() > 0;
		Assert.assertTrue(loginButtonExists);
		
		Boolean emailNotEmpty = !driver.findElement(By.id("Email")).getAttribute("value").equals("");
		Assert.assertTrue(emailNotEmpty);

		Boolean passwordNotEmpty = !driver.findElement(By.id("Password")).getAttribute("value").equals("");
		Assert.assertTrue(passwordNotEmpty);
		
		WebElement rememberMeCheckbox = driver.findElement(By.id("RememberMe"));
		rememberMeCheckbox.click();
		Assert.assertTrue(rememberMeCheckbox.isSelected());
		
		WebElement loginButton = driver.findElement(By.className("login-button"));
		actionProvider.moveToElement(loginButton).build().perform();
		String loginButtonHover = loginButton.getCssValue("background-color");
		Assert.assertEquals("rgba(36, 142, 206, 1)",loginButtonHover);
		
		loginButton.click();
		
		assertPageUrl("https://admin-demo.nopcommerce.com/admin/");
		assertPageTitle("Dashboard / nopCommerce administration");
		assertPageHeading("Dashboard");
		
		Boolean checkUserName = driver.findElement(By.xpath("//div[@id=\"navbarText\"]/ul/li[2]")).getText().equals("John Smith");
		Assert.assertTrue(checkUserName);
		
		Boolean checkSidebarVisible = !driver.findElement(By.className("os-content-glue")).getCssValue("width").equals("73px");
		Assert.assertTrue(checkSidebarVisible);
		checkActiveNavItem("ul.nav a[href=\"/Admin\"]");
		 
		WebElement catalogLink = driver.findElement(By.xpath("//nav/ul/li[2]"));
		
//			  TODO - assert hover over Catalog // Nothing really seams to happen on the dom. needs mor investagtion

		catalogLink.click();
		hasClass(catalogLink, "menu-open");
		Boolean catalogSubMenuIsVisible = driver.findElement(By.xpath("//nav/ul/li[2]/ul")).getCssValue("display").equals("block");
		Assert.assertTrue(catalogSubMenuIsVisible);
		
		WebElement porductsLink = driver.findElement(By.cssSelector("ul.nav a[href=\"/Admin/Product/List\"]"));
		actionProvider.moveToElement(porductsLink).build().perform();
		String productLinkHover = porductsLink.getCssValue("color");
		Assert.assertEquals("rgba(255, 255, 255, 1)",productLinkHover);
		
		porductsLink.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Product/List");
		assertPageTitle("Products / nopCommerce administration");
		assertPageHeading("Products");
		checkActiveNavItem("ul.nav a[href=\"/Admin/Product/List\"]");
		
		WebElement addNewProductBtn = driver.findElement(By.cssSelector("a[href=\"/Admin/Product/Create\"]"));
		actionProvider.moveToElement(addNewProductBtn).build().perform();
		String addNewProductBtnHover = addNewProductBtn.getCssValue("background-color");
		//Assert.assertEquals("rgba(70, 126, 159, 1)",addNewProductBtnHover); // it evaluates to tow different values ??

		addNewProductBtn.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Product/Create");
		assertPageTitle("Add a new product / nopCommerce administration");
		assertPageHeading("Add a new product back to product list");

		WebElement modeChekbox = driver.findElement(By.cssSelector(".onoffswitch-inner"));
		boolean isBasic = !modeChekbox.getCssValue("margin-left").equals(0);
		if(!isBasic) {
			modeChekbox.click();
		}
		
		
		Boolean productInfoCardIsCollapsed = checkCardIsCollapsed("product-info");
		if(productInfoCardIsCollapsed) {
			driver.findElement(By.id("product-info")).click();
		}
		
//			- Fill the product name.
		WebElement productName = driver.findElement(By.id("Name"));
		String fristProduct = "first Prodcut By zinah";
		productName.sendKeys(fristProduct);
		Assert.assertEquals(productName.getAttribute("value"), fristProduct);
//
//			  - assert filling the product name.
//			  - assert hovering over the ? button
//
//			- Fill the proudct short description.
//
//			  - assert filling the short description.
//			  - assert hovering over the ? button
//
//			- Fill the full description textarea.
//
//			  - assert the value of the full description.
//			  - assert hovering over the ? button
//
//			- Fill the SKU field.
//
//			  - assert filling the SKU.
//			  - assert hovering over the ? button
//
//			- Fill the Categories field.
//
//			  - assert filling the categories field.
//			  - assert hovering over the ? button
//
//			- Click on the prices tab.
//
//			  - assert the prices tab is open if not then click.
//			  - assert hovering over the ? button
//
//			- Fill the price field.
//
//			  - assert hovering over the ? button
//			  - assert filling the field.
//			  - Click on increment button.
//			    - assert incrementing the field.
//			  - Click on decrement button.
//			    - assert decrenmenting the field.
//
//			- Check Tax exempt checkbox.
//
//			  - assert cheking the checkbox.
//			  - assert hovering over the ? button
//			  - assert the tax category disappears
//
//			- Click on Inventory Tab
//			  - assert the tab is open if not then click.
//			- Select the inventory method.
//
//			  - assert the selected item "Track Inventory".
//			  - assert hovering over the ? button
//
//			- Fill Stock Quantity.
//
//			  - assert hovering over the ? button
//			  - assert the value of the input.
//			  - Click on increment button.
//			    - assert incrementing the field.
//			  - Click on decrement button.
//			    - assert decrenmenting the field.
//
//			- Click on save button.
//
//			  - assert hovering over the button.
//			  - assert the url contains "Product/List"
//			  - assert the succes message appears with the text "The new product has been added successfully."
//			  - assert the new proudct appears in the table BY:
//			    - assert the search tab is opened.
//			    - Fill product name and assert that.
//			    - Click on the Search button.
//			    - assert there is a row that has the filled proudct info.
//
//			- Click on Promotions link
//
//			  - assert the Promoitions link is the current active element.
//			  - assert the submenu appears
//			  - assert the arrow changes.
//
//			- Click on the Discount link.
//
//			  - assert the Discoun link is the current active element.
//			  - assert the arrow changes.
//			  - assert the url contians "Discount/List"
//			  - assert the page title.
//			  - assert the page heading is "Discounts"
//
//			- Click on Add new button.
//
//			  - assert hover over the button
//			  - assert the url contins "Discount/Create"
//			  - assert the page heading is "Add a new discount"
//
//			- Fill the Discount name.
//
//			  - assert the tooltip
//			  - assert filling the input.
//
//			- Select a discount type
//
//			  - assert the selected item is "Assigned to products"
//			  - assert the tooltip.
//
//			- Fill the start date.
//
//			  - assert the filled value.
//			  - assert the tooltip.
//
//			- Fill the end date
//			  - assert the filled value.
//			  - assert the tooltip.
//			  - assert the end date is after the start date.
//
//			- Click on save.
//			  - assert hovering over the button.
//			  - assert the url contains "Discount/List"
//			  - assert the success message is visible and contains "The new discount has been added successfully."
//			  - assert the discount appears in the table.
//			    - assert the search tab is opened.
//			    - Fill discount name and assert that.
//			    - Click on the Search button.
//			    - assert there is a row that has the filled discount info.
//
//			- Click on Edit of the new Discount.
//			    - assert hover over the edit button.
//			    - assert the url has "Discount/Edit".
//			    - assert the page title.
//			    - assert the heading of the page is "Edit discount details".
//			    - assert the name of the discount in the heading.
//			    - assert the discount info tab is open.
//			    - assert the name input.
//			    - assert the disoucnt type.
//			    - assert the start date.
//			    - assert the end date.
//
//			- Click on the Applied to products tab.
//			    - assert the Applied to products tab is open.
//			    - assert it contains the products table.
//
//			- Click on the Add new Product
//			    - assert the hover on the  button.
//			    - assert a new windwo opens.
//			    - assert the new window url is "https://admin-demo.nopcommerce.com/Admin/Discount/ProductAddPopup?discountId=3&btnId=btnRefreshProducts&formId=discount-form"
//			    - assert the new window haeading is "Add a new product"
//
//			- Fill the product name 
//			    - assert the focus effect.
//			    - assert the filled value.
//
//			- Click the search button.
//			    - assert the hover effect.
//			    - assert the product is visible in the table.
//
//			- Check the product checkbox.
//			    - assert the product is checked.
//
//			- Click on the save button.
//			    - assert the hover effect.
//			    - assert the windows is closed.
//			    - assert the applied products table is filled with the selected product.

		//driver.close();
	}

	public static void assertPageUrl(String expectedUrl) {
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, expectedUrl);
	}

	public static void assertPageTitle(String expectedTitle) {
		String title = driver.getTitle();
		Assert.assertEquals(title, expectedTitle);
	}

	public static void assertPageHeading(String expectedHeading) {
		String title = driver.findElement(By.cssSelector(".content-header h1")).getText();
		Assert.assertEquals(title, expectedHeading);
	}

	public static void checkActiveNavItem(String locator) {
		WebElement elem = driver.findElement(By.cssSelector(locator));
		Boolean isActive = hasClass(elem, "active");
		Assert.assertTrue(isActive);
	}

	public static Boolean hasClass(WebElement element, String elemClass) {
		String classes = element.getAttribute("class");
		for (String c : classes.split(" ")) {
			if (c.equals(elemClass)) {
				return true;
			}
		}

		return false;

	}
	
	public static Boolean checkCardIsCollapsed(String cardId) {
		
		WebElement elem = driver.findElement(By.id(cardId));
		return  hasClass(elem, "collapsed-card");
		
	}

}
