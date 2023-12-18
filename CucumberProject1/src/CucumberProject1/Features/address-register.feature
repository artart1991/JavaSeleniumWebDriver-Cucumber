Feature: Register user

  Scenario Outline: Address added success
    Given User on mystore-testlab.coderslab.pl not logged in
    When User go to sign in page
    And Enter email ipxvtsvxhpkxctxbst@ckptr.com and enter password qwerty1234 and submit
    And User go to add first address page
    And fulfill registration data with alias <alias> and address <address> and city <city> and postal_code <zip/postal code> and phone <phone> submit
    Then Address successfully added
    Then User check data correctnes alias <alias> and address <address> and city <city> and postal_code <zip/postal code> and phone <phone>
    And User delete address
    Then Address successfully delete
    Examples:
      |alias|address|city|zip/postal code|phone|
      |Qwerty1     |Modlinska1       |Warsaw    |01-314               |+48512675898     |
