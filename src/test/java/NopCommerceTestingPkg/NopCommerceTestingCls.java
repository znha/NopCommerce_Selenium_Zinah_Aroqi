package NopCommerceTestingPkg;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	static public Random skuRnd = new Random();

	public static void main(String[] args) throws InterruptedException {
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
		//Assert.assertEquals("rgba(36, 142, 206, 1)", loginButtonHover);

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

//			  TODO - assert hover over Catalog // Nothing really seams to happen on the dom. needs more investagtion

		catalogLink.click();
		hasClass(catalogLink, "menu-open");
		Boolean catalogSubMenuIsVisible = driver.findElement(By.xpath("//nav/ul/li[2]/ul")).getCssValue("display")
				.equals("block");
		Assert.assertTrue(catalogSubMenuIsVisible);

		WebElement porductsLink = driver.findElement(By.cssSelector("ul.nav a[href=\"/Admin/Product/List\"]"));
		actionProvider.moveToElement(porductsLink).build().perform();
		String productLinkHover = porductsLink.getCssValue("color");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Assert.assertEquals("rgba(255, 255, 255, 1)", productLinkHover);

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
		productName.sendKeys(fristProduct);
		Assert.assertEquals(productName.getAttribute("value"), fristProduct);
		
		WebElement productNameTooltip = driver.findElement(By.xpath("//label[@for=\"Name\"]/following-sibling::div"));
		actionProvider.moveToElement(productNameTooltip).build().perform();
		Assert.assertEquals(productNameTooltip.getAttribute("data-original-title"),"The name of the product.");
		
		

		WebElement shortDescription = driver.findElement(By.id("ShortDescription"));
		String shortDescriptionText = "first Prodcut short description By zinah";
		shortDescription.sendKeys(shortDescriptionText);
		Assert.assertEquals(shortDescription.getAttribute("value"), shortDescriptionText);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.switchTo().frame("FullDescription_ifr");
		WebElement fullDescription = driver.findElement(By.id("tinymce"));
		String fullDescriptionText = "first Prodcut full description By zinah";
		fullDescription.sendKeys(fullDescriptionText);
		Assert.assertEquals(fullDescription.getText(), fullDescriptionText);
		driver.switchTo().defaultContent();

		WebElement skuInput = driver.findElement(By.id("Sku"));
		String sku = "R" + skuRnd.nextInt(9999);
		skuInput.sendKeys(sku);
		Assert.assertEquals(skuInput.getAttribute("value"), sku);

		driver.findElement(By.cssSelector(".k-multiselect-wrap")).click();
		WebElement categoriesSelect = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"SelectedCategoryIds_listbox\"]/li[1]")));
		categoriesSelect.click();
		driver.findElement(By.xpath("//div[@class=\"card-body\"][1]")).click();
		WebElement categoriesTags = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//ul[@id=\"SelectedCategoryIds_taglist\"]/li[1]"))));

		// Assert.assertEquals(categoriesSelect.getText(), categoriesTags.getText());

		Boolean pricesCardIsCollapsed = checkCardIsCollapsed("product-price");
		if (pricesCardIsCollapsed) {
			driver.findElement(By.id("product-price")).click();
		}

		String price = "200";
		WebElement priceInput = driver.findElement(By.cssSelector("#product-price-area input[role=\"spinbutton\"]"));
		actionProvider.moveToElement(priceInput).click().perform();
		WebElement priceInputHidden = driver.findElement(By.id("Price"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		priceInputHidden.sendKeys(price);
		Assert.assertEquals(priceInputHidden.getAttribute("value"), "0" + price);

		WebElement taxExemp = driver.findElement(By.id("IsTaxExempt"));
		taxExemp.click();
		Assert.assertTrue(taxExemp.isSelected());

		WebElement pnlTaxCategory = driver.findElement(By.id("pnlTaxCategory"));
		hasClass(pnlTaxCategory, "d-none");

		Boolean InventoryCardIsCollapsed = checkCardIsCollapsed("product-inventory");
		if (InventoryCardIsCollapsed) {
			driver.findElement(By.id("product-inventory")).click();
		}

		Select inverntoryMethod = new Select(driver.findElement(By.id("ManageInventoryMethodId")));
		inverntoryMethod.selectByValue("1");
		String option = inverntoryMethod.getFirstSelectedOption().getAttribute("value");
		Assert.assertEquals("1", option);

		WebElement saveNewProductBtn = driver.findElement(By.cssSelector("[name = \"save\"]"));
		actionProvider.moveToElement(saveNewProductBtn).build().perform();
		String saveNewProductBtnHover = saveNewProductBtn.getCssValue("background-color");
		// Assert.assertEquals("rgba(70, 126, 159, 1)",saveNewProductBtnHover);+

		saveNewProductBtn.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Product/List");
		assertPageTitle("Products / nopCommerce administration");
		assertPageHeading("Products");
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("The new product has been added successfully."));

		WebElement productSearchCard = driver.findElement(By.cssSelector(".card-search .search-row"));
		Boolean searchProductsOpend = hasClass(productSearchCard, "opened");
		if (!searchProductsOpend) {
			productSearchCard.click();
		}

		WebElement searchProductName = driver.findElement(By.id("SearchProductName"));
		searchProductName.sendKeys(fristProduct);
		WebElement searchProductsBtn = driver.findElement(By.id("search-products"));
		searchProductsBtn.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> producRow = driver.findElements(By.xpath("//table/tbody/tr/td[3]"));
		Assert.assertTrue(producRow.get(0).getText().contains(fristProduct));

		WebElement promotionsLink = driver.findElement(By.xpath("//nav/ul/li[5]"));
		promotionsLink.click();
		hasClass(promotionsLink, "menu-open");
		Boolean promotionsSubMenuIsVisible = driver.findElement(By.xpath("//nav/ul/li[5]/ul")).getCssValue("display")
				.equals("block");
		Assert.assertTrue(promotionsSubMenuIsVisible);

		WebElement discountsLink = driver.findElement(By.cssSelector("ul.nav a[href=\"/Admin/Discount/List\"]"));
		actionProvider.moveToElement(discountsLink).build().perform();
		String discountsLinkHover = discountsLink.getCssValue("color");
		// Assert.assertEquals("rgba(255, 255, 255, 1)", discountsLinkHover);

		discountsLink.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Discount/List");
		assertPageTitle("Discounts / nopCommerce administration");
		assertPageHeading("Discounts");
		checkActiveNavItem("ul.nav a[href=\"/Admin/Discount/List\"]");

		WebElement addNewDiscountBtn = driver.findElement(By.cssSelector("a[href=\"/Admin/Discount/Create\"]"));
		actionProvider.moveToElement(addNewDiscountBtn).build().perform();
		String addNewDiscountBtnHover = addNewDiscountBtn.getCssValue("background-color");
		// Assert.assertEquals("rgba(70, 126, 159, 1)",addNewProductBtnHover);

		addNewDiscountBtn.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Discount/Create");
		assertPageTitle("Add a new discount / nopCommerce administration");
		assertPageHeading("Add a new discount back to discount list");

		Boolean discountInfoCardIsCollapsed = checkCardIsCollapsed("discount-info");
		if (discountInfoCardIsCollapsed) {
			driver.findElement(By.id("discount-info")).click();
		}

		WebElement discountName = driver.findElement(By.id("Name"));
		String discountNametxt = "first discount By zinah " + rnd.nextInt(50);
		discountName.sendKeys(discountNametxt);
		Assert.assertEquals(discountName.getAttribute("value"), discountNametxt);

		Select discountType = new Select(driver.findElement(By.id("DiscountTypeId")));
		discountType.selectByValue("2");
		String discountOption = discountType.getFirstSelectedOption().getAttribute("value");
		Assert.assertEquals("2", discountOption);

//		WebElement usePercentageInput = driver.findElement(By.id("UsePercentage"));
//		usePercentageInput.click();
//		Assert.assertTrue(usePercentageInput.isSelected());

		String discountAmmount = "20";
		WebElement discountAmmountInput = driver
				.findElement(By.cssSelector("#pnlDiscountAmount input[role=\"spinbutton\"]"));
		actionProvider.moveToElement(discountAmmountInput).click().perform();
		WebElement discountAmmountInputtHidden = driver.findElement(By.id("DiscountAmount"));
		discountAmmountInputtHidden.sendKeys(discountAmmount);
		Assert.assertEquals(discountAmmountInputtHidden.getAttribute("value"), "0" + discountAmmount);

		WebElement startDateInput = driver.findElement(By.id("StartDateUtc"));
		String startDatTxt = "12/31/2021 12:00 AM";
		startDateInput.sendKeys(startDatTxt);
		Assert.assertEquals(startDateInput.getAttribute("value"), startDatTxt);

		WebElement endDateInput = driver.findElement(By.id("EndDateUtc"));
		String endDateText = "2/28/2022 12:00 AM";
		endDateInput.sendKeys(endDateText);
		Assert.assertEquals(endDateInput.getAttribute("value"), endDateText);

		WebElement saveNewDiscountBtn = driver.findElement(By.cssSelector("[name = \"save\"]"));
		actionProvider.moveToElement(saveNewDiscountBtn).build().perform();
		String saveNewDiscountBtnHover = saveNewDiscountBtn.getCssValue("background-color");
		// Assert.assertEquals("rgba(70, 126, 159, 1)",saveNewDiscountBtnHover);

		saveNewDiscountBtn.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Discount/List");
		assertPageTitle("Discounts / nopCommerce administration");
		assertPageHeading("Discounts");
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).getText()
				.contains("The new discount has been added successfully."));

		WebElement discountSearchCard = driver.findElement(By.cssSelector(".card-search .search-row"));
		Boolean discountProductsOpend = hasClass(discountSearchCard, "opened");
		if (!discountProductsOpend) {
			discountSearchCard.click();
		}

		WebElement searchDiscountName = driver.findElement(By.id("SearchDiscountName"));
		searchDiscountName.sendKeys(discountNametxt);
		WebElement searchDiscountsBtn = driver.findElement(By.id("search-discounts"));
		searchDiscountsBtn.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> discountRow = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		Assert.assertTrue(discountRow.get(0).getText().contains(discountNametxt));

		WebElement EditDiscountBtn = driver.findElement(By.xpath("//table/tbody/tr/td[7]"));
		actionProvider.moveToElement(EditDiscountBtn).build().perform();
		String EditDiscountBtnHover = EditDiscountBtn.getCssValue("background-color");
		// Assert.assertEquals("rgba(248, 249, 250, 1)",EditDiscountBtnHover);

		EditDiscountBtn.click();
		assertPageUrl("https://admin-demo.nopcommerce.com/Admin/Discount/Edit");
		assertPageTitle("Edit discount details / nopCommerce administration");
		assertPageHeading("Edit discount details - " + discountNametxt);

		Boolean discountInfoCardIsCollapsedAgain = checkCardIsCollapsed("discount-info");
		if (discountInfoCardIsCollapsedAgain) {
			driver.findElement(By.id("discount-info")).click();
		}

		Assert.assertEquals(driver.findElement(By.id("Name")).getAttribute("value"), discountNametxt);
		Select discountTypeInEdit = new Select(driver.findElement(By.id("DiscountTypeId")));
		String discountOptionInEdit = discountTypeInEdit.getFirstSelectedOption().getAttribute("value");
		Assert.assertEquals("2", discountOptionInEdit);

		String startDatTxtNewFormat = "12/31/2021 12:00:00 AM";
		String endDateTextNewFormat = "2/28/2022 12:00:00 AM";
		WebElement startDateInputInEdit = driver.findElement(By.id("StartDateUtc"));
		Assert.assertEquals(startDateInputInEdit.getAttribute("value"), startDatTxtNewFormat);
		WebElement endDateInputInEdit = driver.findElement(By.id("EndDateUtc"));
		Assert.assertEquals(endDateInputInEdit.getAttribute("value"), endDateTextNewFormat);

		Boolean appliedProductsCardIsCollapsed = checkCardIsCollapsed("discount-applied-to-products");
		if (appliedProductsCardIsCollapsed) {
			driver.findElement(By.id("discount-applied-to-products")).click();
		}

		WebElement addNewProductToDiscounBtn = driver.findElement(By.id("btnAddNewProduct"));
		addNewProductToDiscounBtn.click();
		String winHandleBefore = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

//			- Click on the Add new Product
//			    - assert the hover on the  button.
//			    - assert a new windwo opens.
//			    - assert the new window url is "https://admin-demo.nopcommerce.com/Admin/Discount/ProductAddPopup?discountId=3&btnId=btnRefreshProducts&formId=discount-form"
//			    - assert the new window haeading is "Add a new product"

		WebElement productNameSearchInput = driver.findElement(By.id("SearchProductName"));
		productNameSearchInput.sendKeys(fristProduct);
		Assert.assertEquals(productNameSearchInput.getAttribute("value"), fristProduct);

		WebElement searchProductsWindowBtn = driver.findElement(By.id("search-products"));
		searchProductsWindowBtn.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebElement firstProductCheckbox = driver
				.findElement(By.xpath("//table[@id=\"products-grid\"]/tbody/tr/td[1]/input"));
		firstProductCheckbox.click();
		Assert.assertTrue(firstProductCheckbox.isSelected());
		WebElement saveProductToDiscountBtn = driver.findElement(By.cssSelector("[name = \"save\"]"));
		actionProvider.moveToElement(saveProductToDiscountBtn).build().perform();
		String saveProductToDiscountHover = saveProductToDiscountBtn.getCssValue("background-color");
		// Assert.assertEquals("rgba(70, 126, 159, 1)",saveProductToDiscountHover);

		saveProductToDiscountBtn.click();

		driver.switchTo().window(winHandleBefore);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(5000);
		List<WebElement> discountProductRow = driver
				.findElements(By.xpath("//*[@id=\"products-grid\"]/tbody/tr/td[1]"));
		Assert.assertTrue(discountProductRow.get(0).getText().contains(fristProduct));

		// driver.close();
	}

	public static void assertPageUrl(String expectedUrl) {
		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains(expectedUrl));
	}

	public static void assertPageTitle(String expectedTitle) {
		String title = driver.getTitle();
		Assert.assertTrue(title.contains(expectedTitle));
	}

	public static void assertPageHeading(String expectedHeading) {
		String heading = driver.findElement(By.cssSelector(".content-header h1")).getText();
		Assert.assertTrue(heading.contains(expectedHeading));
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
