# CITS2005 Project: *"The Game"*
### Semester 1, 2025
##### The University of Western Australia
----

This project implements "*the game*" (see PDF) for the CITS2005 Object Oriented Programming unit at the University of Western Australia 

## Project Structure

```
.
├── CIT2005_PROJECT.pdf
└── src
    ├── ai
    │   ├── AI.java
    │   ├── Heuristic.java
    │   ├── Minimax.java
    │   ├── MinPiecesHeuristic.java
    │   └── PlayVsAI.java
    └── game
        ├── Game.java
        ├── GameImpl.java
        ├── Grid.java
        ├── GridImpl.java
        ├── Move.java
        ├── MoveImpl.java
        ├── PathFinder.java
        ├── PieceColour.java
        ├── README.md
        └── tests
            ├── GameTest_Original.java
            ├── GameTest.java
            ├── GridTest.java
            ├── MoveTest.java
            └── Test.java


```

## How to Run

1. **Compile the project:**
   ```sh
   javac src/game/*.java src/game/tests/*.java src/ai/*.java
   ```

2. **Run the AI game:**
   ```sh
   java -cp src ai.PlayVsAI
   ```

3. **Run tests:**
   ```sh
   java -cp src game.tests.GameTest
   java -cp src game.tests.GridTest
   java -cp src game.tests.MoveTest
   ```

## Requirements

- Java 11 or higher
  - Tested with Oracle JDK 24 & OpenJDK 24

## Authors

- Department of Computer Science & Software Engineering (Skeleton)
- [James Oakey](https://github.com/joakee) (Everything else)

## DISCLAIMER

This project is for educational purposes. You may use it as a reference, but please do not submit it as your own work should you be enrolled in CITS2005 and this assignment is reused in the future. See [UWA's Academic Integrity Policy](https://www.uwa.edu.au/policy/-/media/project/uwa/uwa/policy-library/policy/student-administration/academic-integrity/academic-integrity-policy.doc). 

Any faculty who request that this project be removed from GitHub should contact me directly at my student email address.

Any honest students who happen to be doing the exact same assignment any time after 2025 are encouraged to also email me or raise an issue on this repo to remove it. I'd be happy to do so - anything else will just make life harder for all of us.

Peace! ✌️
