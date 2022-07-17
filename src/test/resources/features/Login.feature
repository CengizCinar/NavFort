@PERF-1460 @PERF-1484
Feature: Login Functionality

  Background:
    Given User is on login page

  @PERF-1483
  Scenario Outline: Login as a Driver
    When User enters valid "<username>" and "<password>"
    Then Drivers should lands on Quick Launchpad Page
    Examples:
      | username | password    |
      | user21   | UserUser123 |
      | user22   | UserUser123 |
      | user23   | UserUser123 |
      | user24   | UserUser123 |

  @PERF-1487
  Scenario Outline: Login as a SalesManager
    When User enters valid "<username>" and "<password>"
    Then User should lands on Dashboard Page

    Examples:
      | username        | password    |
      | salesmanager101 | UserUser123 |
      | salesmanager102 | UserUser123 |
      | salesmanager103 | UserUser123 |
      | salesmanager253 | UserUser123 |

  @PERF-1488
  Scenario Outline: Login as a StoreManager
    When User enters valid "<username>" and "<password>"
    Then User should lands on Dashboard Page

    Examples:
      | username        | password    |
      | storemanager51  | UserUser123 |
      | storemanager52  | UserUser123 |
      | storemanager53  | UserUser123 |
      | storemanager204 | UserUser123 |

  @PERF-1490
  Scenario Outline: User shouldn't be able to login with INVALID credentials - Negative
    When User enters invalid "<username>" and "<password>"
    Then User should be on Login Page
    Examples:
      | username     | password    |
      | invaliduser1 | UserUser123 |
      | user21       | UserUser124 |
      | salesmanager | UserUser123 |
      | storemanager | UserUser123 |

  @PERF-1491
  Scenario Outline: User should not be allowed to access homepage without providing credentials
    When User acts "<unauthenticated steps>"
    Then User should be on Login Page
    Examples:
      | unauthenticated steps                 |
      | copy paste homepage url after logout  |
      | after logout press the go back button |

  @PERF-1504
  Scenario Outline: User should see proper warning messages for invalid credentials
    When User enters "<username>" and "<password>"
    Then User should see directive message "Please fill out this field." in "<field>"

    Examples:
      | username | password    | field    |
      |          |             | username |
      | user21   |             | password |
      |          | UserUser123 | username |
      | Whatsapp |             | password |

  @PERF-1505
  Scenario Outline: User should see warning message for invalid credentials
    When User enters invalid "<username>" and "<password>"
    Then User should see warning message "Invalid user name or password."
    Examples:
      | username     | password    |
      | invaliduser1 | UserUser123 |
      | user21       | UserUser124 |
      | salesmanager | UserUser123 |
      | storemanager | UserUser123 |

  @PERF-1506
  Scenario Outline: Users should their password in bullet signs
    When User enters any "<password>" in password field
    Then User should see bullet sign in password field
    Examples:
      | password        |
      | UserUser123     |
      | UserUser124     |
      | anyPassword-123 |

  @PERF-1507
  Scenario: User is able to land on Forgot Password page
    When User clicks Forgot your password? link
    Then User is on "Forgot Password" page

  @PERF-1508
  Scenario Outline: User can change their forgotten password
    When User clicks Forgot your password? link
    And User enters valid "<username>"
    Then User should be able to change their password
    Examples:
      | username        |
      | user21          |
      | storemanager88  |
      | salesmanager121 |

  @PERF-1512
  Scenario Outline: Remember me on this computer
    When User clicks Remember me on this computer checkbox
    And User enters "<username>" and "<password>"
    And User closes the browser and opens it again
    Then User should be able to login on this computer
    Examples:
      | username        | password    |
      | user21          | UserUser123 |
      | storemanager88  | UserUser123 |
      | salesmanager121 | UserUser123 |

  @PERF-1509
  Scenario Outline: User can use "Enter" key to switch between fields
    When User enters "<username>" press ENTER key and enters "<password>" and press ENTER key
    Then User should click on Login button

    Examples:
      | username        | password    |
      | user21          | UserUser123 |
      | storemanager88  | UserUser123 |
      | salesmanager121 | UserUser123 |

  @PERF-1510
  Scenario Outline: Users can see their username in the top right corner
    When User enters "<username>" and "<password>"
    Then User should see their "<username>" in the top right corner
    Examples:
      | username        | password    |
      | user21          | UserUser123 |
      | storemanager88  | UserUser123 |
      | salesmanager121 | UserUser123 |









