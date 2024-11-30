Feature: Manage scoring via REST API

  Background: the score is reset
    Given the score is reset

  Scenario: Get score for a new game
    When i query the score
    Then i get the following response:
      """
      {
        "A": "0",
        "B": "0"
      }
      """

  Scenario: Score point for a player A
    When i score a point for player A
    Then i get the following response:
      """
      {
        "A": "15",
        "B": "0"
      }
      """

  Scenario: Score point for a player B
    When i score a point for player B
    Then i get the following response:
      """
      {
        "A": "0",
        "B": "15"
      }
      """

  Scenario: Score a sequence of points
    When i score the following sequence: "ABBAB"
    Then i get the following response:
      """
      {
        "A": "30",
        "B": "40"
      }
      """

  Scenario: Resetting the score counter
    Given a score of 40-15
    When i reset the score
    Then i get the following response:
      """
      {
        "A": "0",
        "B": "0"
      }
      """
