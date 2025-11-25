public class Student {
    private String name;
    private int rollNo;
    private String branch;
    private double marks;

    public Student(int rollNo, String name, String branch, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.branch = branch;
        this.marks = marks;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    // Convert student to CSV
    public String toCSV() {
        return rollNo + "," + name + "," + branch + "," + marks;
    }

    // Convert CSV to Student object
    public static Student fromCSV(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 4) return null;

        try {
            int roll = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String branch = parts[2].trim();
            double marks = Double.parseDouble(parts[3].trim());
            return new Student(roll, name, branch, marks);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "RollNo: " + rollNo +
               " | Name: " + name +
               " | Branch: " + branch +
               " | Marks: " + marks;
    }
}
