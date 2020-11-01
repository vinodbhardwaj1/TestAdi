Feature: Purchase items

  Scenario: Navigate to Phones, Laptops and Monitors
    Given user launch the browser "chrome"
    And user navigate to url "https://www.demoblaze.com/index.html"
    Then user click on "Phones"
    Then user click on "Laptops"
    Then user click on "Monitors"


  Scenario: Purchase items and verify amount
    Given user launch the browser "chrome"
    And user navigate to url "https://www.demoblaze.com/index.html"
    When user click on "Laptops"
    * user click on "SoniVaioi5"
    * user click on "AddToCard"
    * user accept product added alert
    * user click on "Home"
    * user click on "Laptops"
    * user click on "Delli7"
    * user click on "AddToCard"
    * user accept product added alert
    * user click on "Cart"
    * user click on "DeleteDelli7"
    * user store value of "TotalPrice"
    * user click on "PlaceOrder"
    * user enter "Name" as "vinod kumar"
    * user enter "Country" as "india"
    * user enter "City" as "Gurugram"
    * user enter "CreditCard" as "420000000000"
    * user enter "Month" as "October"
    * user enter "Year" as "2020"
    * user click on "Purchase"
    Then user capture purchase id and amount
    And user verify purchase amount
    * user click on "OK"
