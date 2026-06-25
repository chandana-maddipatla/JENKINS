@Regression @Deals
Feature: Amazon Today's Deals

  Background:
    Given user opens amazon website

  @Positive
  Scenario: Navigate to Today's Deals page
    When user clicks Today's Deals
    Then deals page should load successfully