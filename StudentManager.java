import java.util.*;
import java.io.*;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    // Add student
    public void addStudent(Student s) {
        students.add(s);
    }

    // View all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Find index by roll number
    private int findIndexByRoll(int rollNo) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNo() == rollNo) return i;
        }
        return -1;
    }

    // Search
    public Student searchByRoll(int rollNo) {
        int idx = findIndexByRoll(rollNo);
        return (idx == -1) ? null : students.get(idx);
    }

    // Update
    public boolean updateStudent(int rollNo, String newName, String newBranch, double newMarks) {
        int idx = findIndexByRoll(rollNo);
        if (idx == -1) return false;

        Student s = students.get(idx);
        s.setName(newName);
        s.setBranch(newBranch);
        s.setMarks(newMarks);

        return true;
    }

    // Delete
    public boolean deleteStudent(int rollNo) {
        int idx = findIndexByRoll(rollNo);
        if (idx == -1) return false;

        students.remove(idx);
        return true;
    }

    // Save to file
    public boolean saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Student s : students) {
                pw.println(s.toCSV());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Load from file
    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) return false;

        ArrayList<Student> loaded = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromCSV(line);
                if (s != null) loaded.add(s);
            }
            this.students = loaded;
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
