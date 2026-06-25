@Regression @Search
Feature: Amazon Product Search

  Background:
    Given user opens amazon website

  @Positive
  Scenario: Search for Mobiles and verify results appear
    When user searches for product "Mobiles"
    Then search results should be displayed

  @Positive
  Scenario: Search for Mobiles and get last displayed item details
    When user searches for product "Mobiles"
    And user scrolls to the last displayed item
    Then last item details should be displayed

  @Positive
  Scenario Outline: Search multiple products and verify results
    When user searches for product "<product>"
    Then search results should be displayed
    And the page title should contain "<product>"

    Examples:
      | product |
      | iPhone  |
