import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Grader {

    // public static void main (String[] args) {

    //    Scanner input = new Scanner(System.in);

    //    System.out.println("ENTER NUMBER OF SUBJECTS: ");
    //    int NS = input.nextInt();

    static class Subject {
        String name;
        int marks;
        String grade;

        Subject(String name, int marks) {
            this.name = name;
            this.marks = marks;
            this.grade = calculate(marks);
        }
        private String calculate(int marks) {
            if (marks >= 90) {
                return "A+";
            } else if (marks >= 80) {
                return "A";
            } else if (marks >= 70) {
                return "B";
            } else if (marks >= 60) {
                return "C";
            } else if (marks >= 50) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Subjects
        List<Subject> defaultSubjects = new ArrayList<>();
        defaultSubjects.add(new Subject("Mathematics", 0));
        defaultSubjects.add(new Subject("Physics", 0));
        defaultSubjects.add(new Subject("Chemistry", 0));
        defaultSubjects.add(new Subject("Biology", 0));
        defaultSubjects.add(new Subject("English", 0));
        defaultSubjects.add(new Subject("History", 0));
        defaultSubjects.add(new Subject("Geography", 0));;

        List<Subject> selectedSubjects = new ArrayList<>();
        
        // Subject selection
        System.out.println("Select the subjects you are doing (enter numbers separated by space):");
        for (int i = 0; i < defaultSubjects.size(); i++) {
            System.out.println((i + 1) + ". " + defaultSubjects.get(i).name);
        }
        
        String[] subjectChoices = input.nextLine().split(" ");
        for (String choice : subjectChoices) {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < defaultSubjects.size()) {
                System.out.print("Enter marks for " + defaultSubjects.get(index).name + ": ");
                int marks = input.nextInt();
                input.nextLine(); // consume the newline
                selectedSubjects.add(new Subject(defaultSubjects.get(index).name, marks));
        }
    }

        // Option to add additional subjects
        System.out.println("Do you want to add any additional subjects? (yes/no)");
        String addMore = input.nextLine();
        
        while (addMore.equalsIgnoreCase("yes")) {
            System.out.print("Enter subject name: ");
            String subjectName = input.nextLine();
            System.out.print("Enter marks: ");
            int marks = input.nextInt();
            input.nextLine(); // consume the newline

            selectedSubjects.add(new Subject(subjectName, marks));
            
            System.out.println("Do you want to add another subject? (yes/no)");
            addMore = input.nextLine();
        }


        
        
        

        // if (percentage >= 90) {
        //     grade = "A+";
        // } else if (percentage >= 80) {
        //     grade = "A";
        // } else if (percentage >= 70) {
        //     grade = "B";
        // } else if (percentage >= 60) {
        //     grade = "C";
        // } else if (percentage >= 50) {
        //     grade = "D";
        // } else {
        //     grade = "F";
        // }

        int totalMarks = 0;
        for (Subject subject : selectedSubjects) {
            totalMarks += subject.marks;
        }

        double average = (double) totalMarks / selectedSubjects.size();
        double percentage = (average / 100) * 100;
        String overallGrade = new Subject("", (int) average).grade;

        // Display subjects with marks
        System.out.println("\nSelected Subjects, Marks and Grades:");
        for (Subject subject : selectedSubjects) {
            System.out.println(subject.name + ": " + subject.marks + " : Grade: " + subject.grade);
        }

        // Display
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.println("Average Percentage: " + percentage);
        System.out.println("Overall Grade: " + overallGrade);
        
        input.close();
    }
}


