package com.grade;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        String[] subjects = {
                "Java",
                "DBMS",
                "DSA",
                "Web Technology",
                "Soft Computing"
        };

        while (true) {

            System.out.println("\n==============================================================");
            System.out.println("                 STUDENT GRADE TRACKER");
            System.out.println("==============================================================");
            System.out.println("1. Add Students");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Class Statistics");
            System.out.println("5. Exit");
            System.out.println("==============================================================");
            System.out.print("Enter Your Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                System.out.print("\nHow many students do you want to add? : ");
                int count = sc.nextInt();

                for (int i = 1; i <= count; i++) {

                    System.out.println("\n-------------- Student " + i + " --------------");

                    sc.nextLine();

                    System.out.print("Enter Name : ");
                    String name = sc.nextLine();

                    int rollNo;

                    while (true) {

                        System.out.print("Enter Roll Number : ");
                        rollNo = sc.nextInt();

                        boolean duplicate = false;

                        for (Student s : students) {

                            if (s.rollNo == rollNo) {
                                duplicate = true;
                                break;
                            }

                        }

                        if (!duplicate)
                            break;

                        System.out.println("Roll Number already exists! Try again.");

                    }

                    int[] marks = new int[5];

                    System.out.println("\nEnter Marks");

                    for (int j = 0; j < 5; j++) {

                        System.out.print(subjects[j] + " : ");
                        marks[j] = sc.nextInt();

                    }

                    Student student = new Student(name, rollNo, marks);

                    student.calculateGrade();

                    students.add(student);

                    System.out.println("\nStudent Added Successfully!");

                    System.out.println("------------------------------------");
                    System.out.println("Total Marks : " + student.total + " / 500");
                    System.out.printf("Percentage : %.2f%%\n", student.average);
                    System.out.println("Grade      : " + student.grade);
                    System.out.println("Performance: " + student.performance);
                    System.out.println("------------------------------------");

                }

                break;

            case 2:

                if (students.isEmpty()) {

                    System.out.println("\nNo Student Records Found.");

                } else {

                    System.out.println("\n==================================================================================================");
                    System.out.printf("%-8s %-18s %-10s %-12s %-8s %-20s%n",
                            "Roll", "Name", "Total", "Percentage", "Grade", "Performance");
                    System.out.println("==================================================================================================");

                    for (Student s : students) {

                    	System.out.printf("%-8d %-18s %-10d %-12s %-8s %-20s%n",
                    	        s.rollNo,
                    	        s.name,
                    	        s.total,
                    	        String.format("%.2f%%", s.average),
                    	        s.grade,
                    	        s.performance);

                    }

                    System.out.println("==================================================================================================");

                }

                break;
            case 3:

                if (students.isEmpty()) {

                    System.out.println("\nNo Student Records Found.");

                } else {

                    System.out.print("\nEnter Roll Number to Search : ");
                    int searchRoll = sc.nextInt();

                    boolean found = false;

                    for (Student s : students) {

                        if (s.rollNo == searchRoll) {

                            found = true;

                            System.out.println("\n==============================================");
                            System.out.println("              STUDENT REPORT");
                            System.out.println("==============================================");
                            System.out.println("Name           : " + s.name);
                            System.out.println("Roll Number    : " + s.rollNo);

                            System.out.println("\n--------------- MARKS ----------------");

                            for (int i = 0; i < subjects.length; i++) {
                                System.out.printf("%-18s : %d%n", subjects[i], s.marks[i]);
                            }

                            System.out.println("--------------------------------------");
                            System.out.println("Total Marks     : " + s.total + " / 500");
                            System.out.printf("Percentage      : %.2f%%%n", s.average);
                            System.out.println("Grade           : " + s.grade);
                            System.out.println("Performance     : " + s.performance);
                            System.out.println("==============================================");

                            break;
                        }

                    }

                    if (!found) {
                        System.out.println("\nStudent Not Found.");
                    }

                }

                break;

            case 4:

                if (students.isEmpty()) {

                    System.out.println("\nNo Student Records Found.");

                } else {

                    double highest = students.get(0).average;
                    double lowest = students.get(0).average;
                    double sum = 0;

                    String topper = students.get(0).name;

                    for (Student s : students) {

                        sum += s.average;

                        if (s.average > highest) {
                            highest = s.average;
                            topper = s.name;
                        }

                        if (s.average < lowest) {
                            lowest = s.average;
                        }

                    }

                    System.out.println("\n==============================================");
                    System.out.println("             CLASS STATISTICS");
                    System.out.println("==============================================");
                    System.out.println("Total Students      : " + students.size());
                    System.out.printf("Highest Percentage  : %.2f%%%n", highest);
                    System.out.printf("Lowest Percentage   : %.2f%%%n", lowest);
                    System.out.printf("Class Average       : %.2f%%%n", sum / students.size());
                    System.out.println("Topper              : " + topper);
                    System.out.println("==============================================");

                }

                break;

            case 5:

                System.out.println("\nThank You for using Student Grade Tracker.");
                sc.close();
                return;

            default:

                System.out.println("\nInvalid Choice! Please Enter Again.");

            }

        }

    }

}