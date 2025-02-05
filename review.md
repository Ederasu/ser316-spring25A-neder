# Code Review Defect List

**Reviewer:** Nicolas Eder 
**GH Repo:** https://github.com/Ederasu/ser316-spring25A-neder

---

## Defect Table

| ID # | Location  |                    Problem Description                       |           Problem           | File and Line Number | Category | Severity |
|------|-----------|--------------------------------------------------------------|-----------------------------|----------------------|----------|----------|
|   1  | Game.java | Game constructor does not use input name properly            |  "Anna" hardcoded           |    Game.java:121     |    FD    |    BR    |
|   2  | Game.java |                                                              |                             |                      |          |          |
|   3  | Main.java | Main class accesses "game.answer", should be a getter method | Should use proper accessors |    Main.java:203     |   CG-    |    MJ    |
|   4  |           |                                                              |                             |                      |          |          |
|   5  |           |                                                              |                             |                      |          |          |
|   6  |           |                                                              |                             |                      |          |          |
|   7  |           |                                                              |                             |                      |          |          |

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
