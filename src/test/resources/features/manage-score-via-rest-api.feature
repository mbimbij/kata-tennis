Feature: Manage scoring via REST API

  Background: the score is reset
    Given the score is reset

  Scenario: Should get score for a new game
    When i query the score
    Then i get the following response:
      """
      {
        "A": "0",
        "B": "0"
      }
      """
