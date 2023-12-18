Feature: Register user

  Scenario: Product add
    Given User on mystore-testlab.coderslab.pl not logged in
    When User go to sign in page
    And Enter email ipxvtsvxhpkxctxbst@ckptr.com and enter password qwerty1234 and submit
    And User go to clothes page
    And Find needed product and submit
    Then Checking if a discount is available
    And Select size
    And Select quantity 5
    And Add products to the basket
    And User go the checkout page
    And Confirm address
    And Select delivery method
    And Select payment method and submit
    Then Order successfully added
    And Make a screenshot of order


