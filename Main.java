import java.util.Scanner;

public class Main {

    private static final String DATA_FILE = "students.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        // Load if file exists
        if (manager.loadFromFile(DATA_FILE)) {
            System.out.println("Loaded existing student data.");
        } else {
            System.out.println("No saved data found. Starting fresh.");
        }

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    addStudentFlow(scanner, manager);
                    break;

                case "2":
                    viewAllFlow(manager);
                    break;

                case "3":
                    searchFlow(scanner, manager);
                    break;

                case "4":
                    updateFlow(scanner, manager);
                    break;

                case "5":
                    deleteFlow(scanner, manager);
                    break;

                case "6":
                    if (manager.saveToFile(DATA_FILE))
                        System.out.println("Saved successfully.");
                    else
                        System.out.println("Error occurred.");
                    break;

                case "7":
                    if (manager.loadFromFile(DATA_FILE))
                        System.out.println("Data loaded.");
                    else
                        System.out.println("No file found.");
                    break;

                case "8":
                    manager.saveToFile(DATA_FILE);
                    System.out.println("Exiting program. Data saved.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Save to File");
        System.out.println("7. Load from File");
        System.out.println("8. Exit");
    }

    private static void addStudentFlow(Scanner scanner, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = Integer.parseInt(scanner.nextLine().trim());

            if (manager.searchByRoll(roll) != null) {
                System.out.println("Student with this roll number already exists.");
                return;
            }

            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Branch: ");
            String branch = scanner.nextLine().trim();

            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(scanner.nextLine().trim());

            Student s = new Student(roll, name, branch, marks);
            manager.addStudent(s);

            System.out.println("Student added successfully.");

        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    private static void viewAllFlow(StudentManager manager) {
        var list = manager.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("All Students:");
        for (Student s : list) {
            System.out.println(s);
        }
    }

    private static void searchFlow(Scanner scanner, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = Integer.parseInt(scanner.nextLine().trim());

            Student s = manager.searchByRoll(roll);

            if (s == null) System.out.println("Student not found.");
            else System.out.println("Found: " + s);

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private static void updateFlow(Scanner scanner, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number to Update: ");
            int roll = Integer.parseInt(scanner.nextLine().trim());

            Student s = manager.searchByRoll(roll);
            if (s == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.println("Current: " + s);

            System.out.print("New Name (Enter to keep): ");
            String newName = scanner.nextLine().trim();
            if (newName.isEmpty()) newName = s.getName();

            System.out.print("New Branch (Enter to keep): ");
            String newBranch = scanner.nextLine().trim();
            if (newBranch.isEmpty()) newBranch = s.getBranch();

            System.out.print("New Marks (Enter to keep): ");
            String marksText = scanner.nextLine().trim();
            double newMarks = marksText.isEmpty() ? s.getMarks() : Double.parseDouble(marksText);

            manager.updateStudent(roll, newName, newBranch, newMarks);

            System.out.println("Updated successfully.");

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private static void deleteFlow(Scanner scanner, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number to Delete: ");
            int roll = Integer.parseInt(scanner.nextLine().trim());

            if (manager.deleteStudent(roll))
                System.out.println("Deleted successfully.");
            else
                System.out.println("Student not found.");

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }
}
