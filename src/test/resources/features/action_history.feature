Feature: Filters abd actions verification

  Scenario: Economic event verification
    Given Open page "https://www.mql5.com/en/economic-calendar"
    Then Filter by period "Current month"
    Then Filter by importance "Medium"
    Then Filter by currency "CHF - Swiss frank"
    Then Open first "CHF" currency event from filtered result
    And Verify action priority is "Medium"
    And Verify country is "Switzerland"
    And Log history for the last 1 year
