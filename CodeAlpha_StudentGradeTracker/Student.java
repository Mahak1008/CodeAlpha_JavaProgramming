package com.grade;

public class Student {

    String name;
    int rollNo;
    int[] marks;
    int total;
    double average;
    String grade;
    String performance;

    public Student(String name, int rollNo, int[] marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public void calculateGrade() {

        total = 0;

        for (int mark : marks) {
            total += mark;
        }

        average = total / 5.0;

        if (average >= 90) {
            grade = "A+";
            performance = "Excellent";
        }
        else if (average >= 80) {
            grade = "A";
            performance = "Very Good";
        }
        else if (average >= 70) {
            grade = "B";
            performance = "Good";
        }
        else if (average >= 60) {
            grade = "C";
            performance = "Average";
        }
        else if (average >= 50) {
            grade = "D";
            performance = "Needs Improvement";
        }
        else {
            grade = "F";
            performance = "Fail";
        }
    }
}