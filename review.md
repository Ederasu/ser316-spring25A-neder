# Code Review Defect List

**Reviewer:** Nicolas Eder 
**GH Repo:** https://github.com/Ederasu/ser316-spring25A-neder

---

## Defect Table

| ID # | Location  |                    Problem Description                       |           Problem            | File and Line Number | Category | Severity |
|------|-----------|--------------------------------------------------------------|------------------------------|----------------------|----------|----------|
|   1  | Game.java | Game constructor does not use input name properly            | "Anna" hardcoded             |    Game.java:121     |    FD    |    BR    |
|   2  | Game.java | "a" need to be named more descriptively                      | "a" is too vague             |    Game.java:16      |   CG-4   |    BR    |
|   3  | Main.java | Main class accesses "game.answer", should be a getter method | Should use proper accessors  |    Main.java:203     |   CG-5   |    MJ    |
|   4  | Game.java | "points" variable should be private and have accessors       | "points" needs to be private |    Game.java:13      |   CG-5   |    BR    |
|   5  | Game.java | getName() returns "answer" instead of "name"                 | Returns incorrect variable   |    Game.java:41      |    FD    |    MJ    |
|   6  | Game.java | Overcomplicated range equation, needs to be simplified       | Overcomplicated              |    Game.java:200     |   CS-8   |    LOW   |
|   7  | Game.java | setPoints() initializes with 5 instead of 10                 | Inconsistent starting points |    Game.java:113     |    FD    |    MJ    |
|   8  | Game.java | getAnswer() is not consistent stylistically                  | "{"and "}" on same line      |    Game.java:47      |   CG-7   |    LOW   |


---

### Category:
- **CS** – Code Smell defect.  
- **CG** – Violation of a coding guideline. Provide the guideline number.  
- **FD** – Functional defect. Code will not produce the expected result.  
- **MD** – Miscellaneous defect, for all other defects.  

### Severity:
- **BR**  - Blocker, must be fixed ASAP.  
- **MJ**  – Major, of high importance but not a Blocker.  
- **LOW** – Low.  
