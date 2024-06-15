# Course Management System

## Overview
The Course Management System is a Java Swing-based application that manages course-related data using file handling. The system consists of 14 classes and manages data through 5 text files. Each file's data is separated by tabs (`\t`) and read into arrays for processing. The data is read and written in classes like `CourseManager`, `ModuleManager`, `StudentFileManager`, and `TutorFileManager`, which have various associations with other classes.

## Classes and Their Roles

### Main Classes
- **MainGui**: Contains the main function to operate the entire project. It has an association with the `SignIn` class.
- **SignIn**: Contains the `signIn()` function for user authentication. It validates usernames and passwords for students, tutors, and admins, and directs users to the appropriate dashboard.
- **TutorSpace**: Provides the dashboard for tutors.
- **StudentSpace**: Provides the dashboard for students.
- **AdminControl**: Provides the dashboard for admins.

### Manager Classes
- **CourseManager**: Manages course data.
- **ModuleManager**: Manages module data.
- **StudentFileManager**: Manages student data.
- **TutorFileManager**: Manages tutor data.

## File Handling
The system uses 5 text files to manage data:
- The data is separated by tabs (`\t`).
- Data is read into arrays and circulated through various classes for manipulation.
- Classes like `CourseManager`, `ModuleManager`, `StudentFileManager`, and `TutorFileManager` handle reading and writing to these files.

## Associations
- **MainGui**: Associated with `SignIn`, `TutorSpace`, `StudentSpace`, and `AdminControl`.
- **SignIn**: Validates and directs to `TutorSpace`, `StudentSpace`, or `AdminControl` based on user type.
- **TutorSpace**: Associated with tutor-related data management.
- **StudentSpace**: Associated with student-related data management.
- **AdminControl**: Associated with overall system management and control.

## How to Run
1. Ensure you have Java installed on your system.
2. Compile the Java files using `javac`.
3. Run the `MainGui` class to start the application.

```sh
javac MainGui.java
java MainGui
