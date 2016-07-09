Feature: Stack

    Background: A Stack
        Given a Stack of Integer

    Scenario: Preconditions
        Then it should be empty

    Scenario: Enqueue
        When I enqueue 2 to the stack
        Then it should not be empty

    Scenario: Dequeue
        Given a Stack of Integer
        When I enqueue 5 to the stack
        And I dequeue
        Then the result should be 5
        And it should be empty

    Scenario: Dequeue Many itens
        Given a Stack of Integer
        When I enqueue 5 to the stack
        And I enqueue 3 to the stack
        And I dequeue
        Then the result should be 3
        And I dequeue
        And the result should be 5
        And it should be empty