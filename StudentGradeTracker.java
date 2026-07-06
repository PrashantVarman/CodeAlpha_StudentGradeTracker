import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CodeAlpha - Java Programming Internship
 * TASK 1: Student Grade Tracker (Console-Based)
 *
 * Features:
 *  - Add students and record grades for multiple subjects
 *  - Calculate each student's average, highest, and lowest score
 *  - Calculate class-wide average, highest, and lowest score
 *  - Display a full summary report of all students
 *  - Edit / delete a student's record
 *  - Simple, menu-driven console interface using Scanner
 */
public class StudentGradeTracker {

    // ---------------------------------------------------------------
    // Inner class representing a single student and their grades
    // ---------------------------------------------------------------
    static class Student {
        private final String name;
        private final List<Double> grades;

        public Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<Double> getGrades() {
            return grades;
        }

        public void addGrade(double grade) {
            grades.add(grade);
        }

        public double getAverage() {
            if (grades.isEmpty()) return 0.0;
            double sum = 0;
            for (double g : grades) sum += g;
            return sum / grades.size();
        }

        public double getHighest() {
            if (grades.isEmpty()) return 0.0;
            double max = grades.get(0);
            for (double g : grades) if (g > max) max = g;
            return max;
        }

        public double getLowest() {
            if (grades.isEmpty()) return 0.0;
            double min = grades.get(0);
            for (double g : grades) if (g < min) min = g;
            return min;
        }

        public String getLetterGrade() {
            double avg = getAverage();
            if (avg >= 90) return "A";
            if (avg >= 80) return "B";
            if (avg >= 70) return "C";
            if (avg >= 60) return "D";
            return "F";
        }
    }

    // ---------------------------------------------------------------
    // Main program state
    // ---------------------------------------------------------------
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   STUDENT GRADE TRACKER  (CodeAlpha)");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGradesToStudent();
                case 3 -> viewStudentReport();
                case 4 -> viewSummaryReport();
                case 5 -> editStudentName();
                case 6 -> deleteStudent();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting... Thank you for using Student Grade Tracker!");
                }
                default -> System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }

    // ---------------------------------------------------------------
    // Menu
    // ---------------------------------------------------------------
    private static void printMenu() {
        System.out.println("\n----------------- MENU -----------------");
        System.out.println("1. Add New Student");
        System.out.println("2. Add Grade(s) to a Student");
        System.out.println("3. View Individual Student Report");
        System.out.println("4. View Full Summary Report (All Students)");
        System.out.println("5. Edit Student Name");
        System.out.println("6. Delete Student");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------------");
    }

    // ---------------------------------------------------------------
    // Option 1: Add a new student
    // ---------------------------------------------------------------
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.\n");
            return;
        }
        if (findStudent(name) != null) {
            System.out.println("A student with this name already exists.\n");
            return;
        }

        Student student = new Student(name);
        students.add(student);
        System.out.println("Student \"" + name + "\" added successfully.");

        System.out.print("Would you like to add grades now? (y/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        if (resp.equals("y") || resp.equals("yes")) {
            addGradesLoop(student);
        }
    }

    // ---------------------------------------------------------------
    // Option 2: Add grade(s) to an existing student
    // ---------------------------------------------------------------
    private static void addGradesToStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found. Please add a student first.\n");
            return;
        }
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        Student student = findStudent(name);

        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }
        addGradesLoop(student);
    }

    private static void addGradesLoop(Student student) {
        boolean addingMore = true;
        while (addingMore) {
            double grade = readDouble("Enter a grade (0-100) for " + student.getName() + ": ");
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100. Not added.");
            } else {
                student.addGrade(grade);
                System.out.println("Grade " + grade + " added.");
            }
            System.out.print("Add another grade for " + student.getName() + "? (y/n): ");
            String resp = scanner.nextLine().trim().toLowerCase();
            addingMore = resp.equals("y") || resp.equals("yes");
        }
        System.out.println();
    }

    // ---------------------------------------------------------------
    // Option 3: View one student's report
    // ---------------------------------------------------------------
    private static void viewStudentReport() {
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        Student student = findStudent(name);

        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }
        printStudentReport(student);
    }

    private static void printStudentReport(Student student) {
        System.out.println("\n----- Report for " + student.getName() + " -----");
        if (student.getGrades().isEmpty()) {
            System.out.println("No grades recorded yet.");
        } else {
            System.out.println("Grades   : " + student.getGrades());
            System.out.printf("Average  : %.2f%n", student.getAverage());
            System.out.printf("Highest  : %.2f%n", student.getHighest());
            System.out.printf("Lowest   : %.2f%n", student.getLowest());
            System.out.println("Letter   : " + student.getLetterGrade());
        }
        System.out.println("---------------------------------------\n");
    }

    // ---------------------------------------------------------------
    // Option 4: Full summary report for all students
    // ---------------------------------------------------------------
    private static void viewSummaryReport() {
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }

        System.out.println("\n===================== CLASS SUMMARY REPORT =====================");
        System.out.printf("%-15s %-10s %-10s %-10s %-8s%n", "Name", "Average", "Highest", "Lowest", "Grade");
        System.out.println("------------------------------------------------------------------");

        double classSum = 0;
        double classHighest = Double.MIN_VALUE;
        double classLowest = Double.MAX_VALUE;
        int totalGradesRecorded = 0;

        for (Student s : students) {
            System.out.printf("%-15s %-10.2f %-10.2f %-10.2f %-8s%n",
                    s.getName(), s.getAverage(), s.getHighest(), s.getLowest(), s.getLetterGrade());

            for (double g : s.getGrades()) {
                classSum += g;
                totalGradesRecorded++;
                if (g > classHighest) classHighest = g;
                if (g < classLowest) classLowest = g;
            }
        }

        System.out.println("------------------------------------------------------------------");
        if (totalGradesRecorded > 0) {
            double classAverage = classSum / totalGradesRecorded;
            System.out.printf("Class Average : %.2f%n", classAverage);
            System.out.printf("Class Highest : %.2f%n", classHighest);
            System.out.printf("Class Lowest  : %.2f%n", classLowest);
        } else {
            System.out.println("No grades recorded for any student yet.");
        }
        System.out.println("Total Students : " + students.size());
        System.out.println("===================================================================\n");
    }

    // ---------------------------------------------------------------
    // Option 5: Edit a student's name
    // ---------------------------------------------------------------
    private static void editStudentName() {
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }
        System.out.print("Enter current student name: ");
        String name = scanner.nextLine().trim();
        Student student = findStudent(name);

        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("New name cannot be empty.\n");
            return;
        }
        if (findStudent(newName) != null) {
            System.out.println("Another student already has that name.\n");
            return;
        }

        // Since name is final, replace the student object with a new one that has the same grades
        Student updated = new Student(newName);
        for (double g : student.getGrades()) updated.addGrade(g);
        students.set(students.indexOf(student), updated);

        System.out.println("Name updated from \"" + name + "\" to \"" + newName + "\".\n");
    }

    // ---------------------------------------------------------------
    // Option 6: Delete a student
    // ---------------------------------------------------------------
    private static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }
        System.out.print("Enter student name to delete: ");
        String name = scanner.nextLine().trim();
        Student student = findStudent(name);

        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }
        System.out.print("Are you sure you want to delete \"" + name + "\"? (y/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        if (resp.equals("y") || resp.equals("yes")) {
            students.remove(student);
            System.out.println("Student \"" + name + "\" deleted.\n");
        } else {
            System.out.println("Deletion cancelled.\n");
        }
    }

    // ---------------------------------------------------------------
    // Helper methods
    // ---------------------------------------------------------------
    private static Student findStudent(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
