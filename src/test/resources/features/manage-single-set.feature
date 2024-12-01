Feature: Manage Score for a single Set

  Scenario: Get the Score of a new set
    Given a new set
    When I get the score of the set
    Then the set score should be:
    """
    {
      "setScore": {
        "A": 0,
        "B": 0
      },
      "currentGameScore": {
        "A": "0",
        "B": "0"
      },
      "isOver": false,
      "winner": null
    }
    """

  Scenario: Get the Score of a finished set
    Given a finished set
    When I get the score of the set
    Then the set score should be:
    """
    {
      "setScore": {
        "A": 6,
        "B": 2
      },
      "currentGameScore": null,
      "isOver": true,
      "winner": "A"
    }
    """

  Scenario: Score a point for player A and not modification of current game score or set score
    Given the set 4-2/15-40
    When player A scores a point for the set
    Then the set score should be:
    """
    {
      "setScore": {
        "A": 4,
        "B": 2
      },
      "currentGameScore": {
        "A": "30",
        "B": "40"
      },
      "isOver": false,
      "winner": null
    }
    """

