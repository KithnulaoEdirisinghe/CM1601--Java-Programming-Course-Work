# CM1601 — Malabe Tuk-Tuk & Three-Wheeler Spares Depot

A Java + JavaFX desktop application for managing inventory, dealers, and sales for a vehicle spare parts business, built for the CM1601 Programming Fundamentals coursework.

## Requirements

- **JDK 21** (developed and tested with Eclipse Temurin 21.0.11) — a JDK, not just a JRE, is required, since Maven needs `javac` to compile the project.
- No separate JavaFX SDK install is needed — JavaFX 21.0.6 (`javafx-controls`, `javafx-fxml`) is pulled in automatically by Maven as a project dependency.
- No local Maven install is needed either — the project includes the Maven Wrapper (`mvnw` / `mvnw.cmd`), which downloads the correct Maven version automatically on first use.
- An internet connection is required the first time the project is built, so Maven can download the wrapper and the project's dependencies (JavaFX, JUnit).

## How to Run the Application

1. Extract the submitted `.zip` file and open a terminal **in the extracted project's root folder** (the same folder that contains `pom.xml`).
2. Run:
   - Windows: `mvnw.cmd clean javafx:run`
   - macOS/Linux: `./mvnw clean javafx:run`

This compiles the project and launches the JavaFX application (`FX.MainApplication`) directly — no separate packaging or install step is needed.

**Important:** the application must be launched with the project's root folder as the current working directory. It reads and writes several files by relative path — `inventory_legacy.txt`, `dealers_legacy.txt`, `inventory_cleaned.txt`, `dealers_cleaned.txt`, `audit_log.txt`, and the `images/` folder — rather than an absolute one, so it will look for (or create) those files wherever the terminal's working directory happens to be when it's launched.

## How to Run the Tests

- Windows: `mvnw.cmd test`
- macOS/Linux: `./mvnw test`

This runs the full JUnit 5 test suite (10 test classes, 38 test cases) via Maven Surefire. All 38 are expected to pass.

## Versions Used

| Component | Version |
|---|---|
| JDK | 21 (Eclipse Temurin 21.0.11) |
| JavaFX | 21.0.6 (`javafx-controls`, `javafx-fxml`) |
| JUnit | 5.12.1 (JUnit Jupiter) |
| Maven | via Maven Wrapper — no separate install required |

## Git Repository

https://github.com/KithnulaoEdirisinghe/CM1601--Java-Programming-Course-Work

## Assumptions Needed to Run the Project

- The terminal's working directory must be the project root when launching the app or running the tests, since every data file (`inventory_legacy.txt`, `dealers_legacy.txt`, `inventory_cleaned.txt`, `dealers_cleaned.txt`, `audit_log.txt`) and the `images/` folder are referenced by relative path, not an absolute one.
- `inventory_cleaned.txt`/`dealers_cleaned.txt` are only regenerated from the legacy `.txt` files if they don't already exist. Both cleaned files are included in this submission, so the application will use them directly on first launch rather than re-parsing the legacy files.
- A JDK (not just a JRE) must be installed and available on the `PATH`, or `JAVA_HOME` must point to one, for the Maven Wrapper to build the project.
- On Windows, if `mvnw.cmd` fails with a batch-parsing error, ensure the extracted project folder's path does not contain parentheses or other special characters — this is a known limitation of the Maven Wrapper's batch script, unrelated to the project itself.
