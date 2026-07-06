# CodeAlpha_StudentGradeTracker

A console-based Java application built for the **CodeAlpha Java Programming Internship — Task 1**. It lets you record student grades, and instantly calculates averages, highs, lows, and letter grades for individuals and the whole class.

## Overview

Student Grade Tracker is a menu-driven command-line program that manages a roster of students and their grades entirely in memory during a session. It's built with core Java (no external libraries) and demonstrates fundamental OOP design, collections usage, and clean input validation.

## Features

- **Add students** and record any number of grades per student
- **Per-student statistics**: average, highest, lowest score, and a computed letter grade (A–F)
- **Class-wide summary report**: a formatted table of every student plus overall class average/highest/lowest
- **Edit** a student's name and **delete** a student record
- Robust input validation (rejects invalid numbers, out-of-range grades, empty names, duplicate names)
- Simple, dependency-free, single-file implementation

## Technologies Used

- **Java** (core language, no external libraries)
- `java.util.ArrayList` / `List` for dynamic data storage
- `java.util.Scanner` for console input

## Requirements

- JDK 17 or later (uses modern `switch` expressions)

## How to Run

```bash
# Clone the repository
git clone https://github.com/<your-username>/CodeAlpha_StudentGradeTracker.git
cd CodeAlpha_StudentGradeTracker

# Compile
javac StudentGradeTracker.java

# Run
java StudentGradeTracker
```

## Menu Options

```
1. Add New Student
2. Add Grade(s) to a Student
3. View Individual Student Report
4. View Full Summary Report (All Students)
5. Edit Student Name
6. Delete Student
0. Exit
```

## Example Session

```
Enter student name: Priya Sharma
Student "Priya Sharma" added successfully.
Would you like to add grades now? (y/n): y
Enter a grade (0-100) for Priya Sharma: 88
Grade 88.0 added.
Add another grade for Priya Sharma? (y/n): n

----- Report for Priya Sharma -----
Grades   : [88.0]
Average  : 88.00
Highest  : 88.00
Lowest   : 88.00
Letter   : B
---------------------------------------
```

## Project Structure

```
CodeAlpha_StudentGradeTracker/
└── StudentGradeTracker.java   # Single-file application (Student class + main logic)
```

## Possible Future Enhancements

- Persist student records to a file or database between runs
- Add a GUI (JavaFX or Swing) as an alternative to the console interface
- Support per-subject grade tracking instead of a flat grade list
- Export summary reports to CSV or PDF

## About This Project

This project was built as part of the **CodeAlpha Java Programming Internship**, satisfying Task 1: *Student Grade Tracker*, from the official task list.

- Organization: [CodeAlpha](https://www.codealpha.tech)
- Contact: services@codealpha.tech
- Internship domain: Java Programming

---
*Built with Java as part of a CodeAlpha internship submission.*
