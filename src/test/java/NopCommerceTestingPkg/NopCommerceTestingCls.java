package NopCommerceTestingPkg;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NopCommerceTestingCls {

	static public WebDriver driver = new ChromeDriver();
	static public Actions actionProvider = new Actions(driver);
	static public Random rnd = new Random();

	public static void main(String[] args) throws InterruptedException {
		driver.get("https://admin-demo.nopcommerce.com/");
		driver.manage().window().maximize();
		JavascriptExecutor jse = (JavascriptExecutor) driver;

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
		Assert.assertEquals("rgba(36, 142, 206, 1)", loginButtonHover);

		loginButton.click();

		assertPageUrl("https://admin-demo.nopcommerce.com/admin/");
		assertPageTitle("Dashboard / nopCommerce administration");
		assertPageHeading("Dashboard");

		Boolean checkUserName = driver.findElement(By.xpath("//div[@id=\"navbarText\"]/ul/li[2]")).getText()
				.equals("John Smith");
		Assert.assertTrue(checkUserName);

		Boolean checkSidebarVisible = !driver.findElement(By.className("os-content-glue")).getCssValue("width")
				.equals("73px");
		Assert.assertTrue(checkSidebarVisible);
		checkActiveNavItem("ul.nav a[href=\"/Admin\"]");

		WebElement catalogLink = driver.findElement(By.xpath("//nav/ul/li[2]"));

//			  TODO - assert hover over Catalog // Nothing really seams to happen on the dom. needs mor investagtion

		catalogLink.click();
		hasClass(catalogLink, "menu-open");
		Boolean catalogSubMenuIsVisible = driver.findElement(By.xpath("//nav/ul/li[2]/ul")).getCssValue("display")
				.equals("block");
		Assert.assertTrue(catalogSubMenuIsVisible);

		WebElement porductsLink = driver.findElement(By.cssSelector("ul.nav a[href=\"/Admin/Product/List\"]"));
		actionProvider.moveToElement(porductsLink).build().perform();
		String productLinkHover = porductsLink.getCssValue("color");
		Assert.assertEquals("rgba(255, 255, 255, 1)", productLinkHover);

		porductsLink.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Product/List");
		assertPageTitle("Products / nopCommerce administration");
		assertPageHeading("Products");
		checkActiveNavItem("ul.nav a[href=\"/Admin/Product/List\"]");

		WebElement addNewProductBtn = driver.findElement(By.cssSelector("a[href=\"/Admin/Product/Create\"]"));
		actionProvider.moveToElement(addNewProductBtn).build().perform();
		String addNewProductBtnHover = addNewProductBtn.getCssValue("background-color");
		// Assert.assertEquals("rgba(70, 126, 159, 1)",addNewProductBtnHover); // it
		// evaluates to tow different values ??

		addNewProductBtn.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Product/Create");
		assertPageTitle("Add a new product / nopCommerce administration");
		assertPageHeading("Add a new product back to product list");

		WebElement modeChekbox = driver.findElement(By.cssSelector(".onoffswitch-inner"));
		boolean isBasic = !modeChekbox.getCssValue("margin-left").equals(0);
		if (!isBasic) {
			modeChekbox.click();
		}

		Boolean productInfoCardIsCollapsed = checkCardIsCollapsed("product-info");
		if (productInfoCardIsCollapsed) {
			driver.findElement(By.id("product-info")).click();
		}

		WebElement productName = driver.findElement(By.id("Name"));
		String fristProduct = "first Prodcut By zinah " + rnd.nextInt(50);
		;
		productName.sendKeys(fristProduct);
		Assert.assertEquals(productName.getAttribute("value"), fristProduct);
//		  - assert hovering over the ? button

		WebElement shortDescription = driver.findElement(By.id("ShortDescription"));
		String shortDescriptionText = "first Prodcut short description By zinah";
		shortDescription.sendKeys(shortDescriptionText);
		Assert.assertEquals(shortDescription.getAttribute("value"), shortDescriptionText);

//			  - assert hovering over the ? button
//
//		WebElement frame = new WebDriverWait(driver, Duration.ofSeconds(3))
//				.until(driver.findElement(By.id("FullDescription_ifr")));

		Thread.sleep(5000);
		driver.switchTo().frame("FullDescription_ifr");
		WebElement fullDescription = driver.findElement(By.id("tinymce"));
		String fullDescriptionText = "first Prodcut full description By zinah";
		fullDescription.sendKeys(fullDescriptionText);
		Assert.assertEquals(fullDescription.getText(), fullDescriptionText);
		driver.switchTo().defaultContent();

//
		WebElement skuInput = driver.findElement(By.id("Sku"));
		String sku = "R2345678";
		skuInput.sendKeys(sku);
		Assert.assertEquals(skuInput.getAttribute("value"), sku);
//			  - assert hovering over the ? button
//
//			- Fill the Categories field.

		driver.findElement(By.cssSelector(".k-multiselect-wrap")).click();
		WebElement categoriesSelect = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"SelectedCategoryIds_listbox\"]/li[1]")));
		categoriesSelect.click();
		driver.findElement(By.xpath("//div[@class=\"card-body\"][1]")).click();
		WebElement categoriesTags = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//ul[@id=\"SelectedCategoryIds_taglist\"]/li[1]"))));

		// Assert.assertEquals(categoriesSelect.getText(), categoriesTags.getText());
		// - assert hovering over the ? button

		Boolean pricesCardIsCollapsed = checkCardIsCollapsed("product-price");
		if (pricesCardIsCollapsed) {
			driver.findElement(By.id("product-price")).click();
		}

//			  - assert hovering over the ? button
		String price = "200";
		WebElement priceInput = driver.findElement(By.cssSelector("#product-price-area input[role=\"spinbutton\"]"));
		actionProvider.moveToElement(priceInput).click().perform();
		WebElement priceInputHidden = driver.findElement(By.id("Price"));
		priceInputHidden.sendKeys(price);
		Assert.assertEquals(priceInputHidden.getAttribute("value"), "0" + price);
		// jse.executeScript("document.getElementById('Price').setAttribute('value',
		// '200');");

		WebElement taxExemp = driver.findElement(By.id("IsTaxExempt"));
		taxExemp.click();
		Assert.assertTrue(taxExemp.isSelected());
//		  - assert the tax category disappears
		WebElement pnlTaxCategory = driver.findElement(By.id("pnlTaxCategory"));
		hasClass(pnlTaxCategory, "d-none");
//			  - assert hovering over the ? button
		Boolean InventoryCardIsCollapsed = checkCardIsCollapsed("product-inventory");
		if (InventoryCardIsCollapsed) {
			driver.findElement(By.id("product-inventory")).click();
		}

//			  - assert hovering over the ? button
		Select inverntoryMethod = new Select(driver.findElement(By.id("ManageInventoryMethodId")));
		inverntoryMethod.selectByValue("1");

//			- Click on save button.
		WebElement saveNewProductBtn = driver.findElement(By.cssSelector("[name = \"save\"]"));
		actionProvider.moveToElement(saveNewProductBtn).build().perform();
		String saveNewProductBtnHover = saveNewProductBtn.getCssValue("background-color");
		// Assert.assertEquals("rgba(70, 126, 159, 1)",saveNewProductBtnHover);
		saveNewProductBtn.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Product/List");
		assertPageTitle("Products / nopCommerce administration");
		assertPageHeading("Products");
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("The new product has been added successfully."));
//			  - assert the new proudct appears in the table BY:
		WebElement productSearchCard = driver.findElement(By.cssSelector(".card-search .search-row"));
		Boolean searchProductsOpend = hasClass(productSearchCard, "opened");
		if (!searchProductsOpend) {
			productSearchCard.click();
		}

		WebElement searchProductName = driver.findElement(By.id("SearchProductName"));
		searchProductName.sendKeys(fristProduct);
		WebElement searchProductsBtn = driver.findElement(By.id("search-products"));
		searchProductsBtn.click();

//			    - assert there is a row that has the filled proudct info.
		Thread.sleep(5000); // It didn't work except with it
		WebElement producRow = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//table/tbody/tr/td[3]"))));
		Assert.assertTrue(producRow.getSize().equals(1));
		WebElement productsTable = driver.findElement(By.id("products-grid_wrapper"));
//		Assert.assertTrue(producRow.getText().contains(fristProduct));
		Assert.assertTrue(productsTable.getText().contains(fristProduct));

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

		// driver.close();
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
		return hasClass(elem, "collapsed-card");

	}

}
