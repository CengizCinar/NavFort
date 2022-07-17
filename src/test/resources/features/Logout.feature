@PERF-1461 @PERF-1531
Feature: As a user I should be able to log out

  @PERF-1530
  Scenario Outline: Users can log out by clicking on the logout button
    When "<User>" is logged in
    When User clicks on the logout button
    Then User should be logged out

    Examples:
      | User          |
      | driver        |
      | sales manager |
      | store manager |

  @PERF-1532
  Scenario Outline: Users can not access homepage by clicking back button
    When "<User>" is logged in
    When User clicks on the logout button
    And User should be logged out
    And User clicks on the back button
    Then User should not be able to access the homepage

    Examples:
      | User          |
      | driver        |
      | sales manager |
      | store manager |

  @PERF-1533
  Scenario Outline: Users should be logged out when they are away from the keyboard
    When "<User>" is logged in
    When User is away from keyboard for 3 minutes
    Then User should be logged out

    Examples:
      | User          |
      | driver        |
      | sales manager |
      | store manager |

  @PERF-1574
    Scenario Outline: Users should be logged out when they close all tabs
      When "<User>" is logged in
      And User closes the browser and opens it again
      Then User should be logged out

      Examples:
        | User          |
        | driver        |
        | sales manager |
        | store manager |