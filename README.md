# NopCommerce_Selenium_Zinah_Aroqi

Testing Creating Products and Adding Discounts into Nop Commerce Website

# Steps:

- Clone the repo
- Import into eclipse
- Run the project.

# Required Testing Steps:

- Navigate to https://admin-demo.nopcommerce.com/
- Login with the default admin user
- Navigate to products page from the left nav.
- Add a new product in Basic Mode (Fill all fields in the Product Info, Price, and Inventory sections).
- Navigate to the Discount section from the left nav.
- Add a new discout assinged to the previously added product applied from 31/12/2021 to 28/2/2022.

# Test Plan

- Navigate to https://admin-demo.nopcommerce.com/ and maximize the window

  - assert the url contains "login"
  - assert the page has a login button
  - assert the email is filled.
  - assert the password is filled.
  - check the Remember me checkbox.
  - assert the rememebr me chekcbox is checked.
  - asserr hover over the login button.

- Click on the login button.

  - assert the url has "admin"
  - assert the page title.
  - assert the user name "John Smith"
  - assert the sidebar menu is visible.
  - assert the current active item in the page is the "Dashboard"
  - assert hover over Catalog

- Click on Catalog link.

  - assert the Catalog is the current active item
  - assert the submenu is visible.
  - assert the arrow changes.

- Click on Products link.

  - assert the url contains "Product/List".
  - assert the page title.
  - assert the Products is the current activ item.
  - assert the page heading is "Products"
  - assert hover over the Add new button.
  - assert the arrow changes.

- CLick on Add new Button.

  - assert the url contians "Product/Create"
  - assert the page heading is "Add a new product"
  - assert the mode button is 'basic'
  - assert the product info tab is open

- Fill the product name.

  - assert filling the product name.
  - assert hovering over the ? button

- Fill the proudct short description.

  - assert filling the short description.
  - assert hovering over the ? button

- Fill the full description textarea.

  - assert the value of the full description.
  - assert hovering over the ? button

- Fill the SKU field.

  - assert filling the SKU.
  - assert hovering over the ? button

- Fill the Categories field.

  - assert filling the categories field.
  - assert hovering over the ? button

- Click on the prices tab.

  - assert the prices tab is open if not then click.
  - assert hovering over the ? button

- Fill the price field.

  - assert hovering over the ? button
  - assert filling the field.
  - Click on increment button.
    - assert incrementing the field.
  - Click on decrement button.
    - assert decrenmenting the field.

- Check Tax exempt checkbox.

  - assert cheking the checkbox.
  - assert hovering over the ? button
  - assert the tax category disappears

- Click on Inventory Tab
  - assert the tab is open if not then click.
- Select the inventory method.

  - assert the selected item "Track Inventory".
  - assert hovering over the ? button

- Fill Stock Quantity.

  - assert hovering over the ? button
  - assert the value of the input.
  - Click on increment button.
    - assert incrementing the field.
  - Click on decrement button.
    - assert decrenmenting the field.

- Click on save button.

  - assert hovering over the button.
  - assert the url contains "Product/List"
  - assert the succes message appears with the text "The new product has been added successfully."
  - assert the new proudct appears in the table BY:
    - assert the search tab is opened.
    - Fill product name and assert that.
    - Click on the Search button.
    - assert there is a row that has the filled proudct info.

- Click on Promotions link

  - assert the Promoitions link is the current active element.
  - assert the submenu appears
  - assert the arrow changes.

- Click on the Discount link.

  - assert the Discoun link is the current active element.
  - assert the arrow changes.
  - assert the url contians "Discount/List"
  - assert the page title.
  - assert the page heading is "Discounts"

- Click on Add new button.

  - assert hover over the button
  - assert the url contins "Discount/Create"
  - assert the page heading is "Add a new discount"

- Fill the Discount name.

  - assert the tooltip
  - assert filling the input.

- Select a discount type

  - assert the selected item is "Assigned to products"
  - assert the tooltip.

- Fill the start date.

  - assert the filled value.
  - assert the tooltip.

- Fill the end date
  - assert the filled value.
  - assert the tooltip.
  - assert the end date is after the start date.

- Click on save.
  - assert hovering over the button.
  - assert the url contains "Discount/List"
  - assert the success message is visible and contains "The new discount has been added successfully."
  - assert the discount appears in the table.
    - assert the search tab is opened.
    - Fill discount name and assert that.
    - Click on the Search button.
    - assert there is a row that has the filled discount info.

- Click on Edit of the new Discount.
    - assert hover over the edit button.
    - assert the url has "Discount/Edit".
    - assert the page title.
    - assert the heading of the page is "Edit discount details".
    - assert the name of the discount in the heading.
    - assert the discount info tab is open.
    - assert the name input.
    - assert the disoucnt type.
    - assert the start date.
    - assert the end date.

- Click on the Applied to products tab.
    - assert the Applied to products tab is open.
    - assert it contains the products table.

- Click on the Add new Product
    - assert the hover on the  button.
    - assert a new windwo opens.
    - assert the new window url is "https://admin-demo.nopcommerce.com/Admin/Discount/ProductAddPopup?discountId=3&btnId=btnRefreshProducts&formId=discount-form"
    - assert the new window haeading is "Add a new product"

- Fill the product name 
    - assert the focus effect.
    - assert the filled value.

- Click the search button.
    - assert the hover effect.
    - assert the product is visible in the table.

- Check the product checkbox.
    - assert the product is checked.

- Click on the save button.
    - assert the hover effect.
    - assert the windows is closed.
    - assert the applied products table is filled with the selected product.

# The End...





