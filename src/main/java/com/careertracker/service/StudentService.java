package com.careertracker.service;

import com.careertracker.dao.StudentDAO;
import com.careertracker.entity.Student;
import com.careertracker.util.InputUtil;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO = new StudentDAO();

    public void menu() {
        int choice;
        do {
            System.out.println("\n------ STUDENT PROFILE ------");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student By ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back");
            choice = InputUtil.readInt("Enter choice");

            switch (choice) {
                case 1: 
                	addStudent(); 
                	break;
                case 2: 
                	viewAllStudents();
                	break;
                case 3: 
                	viewStudentById(); 
                	break;
                case 4: 
                	updateStudent(); 
                	break;
                case 5: 
                	deleteStudent(); 
                	break;
                case 6:
                	break;
                default: 
                	System.out.println("Invalid choice.");
            }
        }while (choice != 6);
    }

    private void addStudent() {
        String name = InputUtil.readString("Name");
        String college = InputUtil.readString("College");
        String qualification = InputUtil.readString("Qualification");
        String skills = InputUtil.readString("Skills");
        String email = InputUtil.readString("Email");
        String phone = InputUtil.readString("Phone");

        studentDAO.save(new Student(name, college, qualification, skills, email, phone));
        System.out.println("Student added successfully.");
    }

    private void viewAllStudents() {
        List<Student> students = studentDAO.getAll();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            printStudent(s);
        }
    }

    private void viewStudentById() {
        int id = InputUtil.readInt("Student ID");
        Student s = studentDAO.getById(id);
        if (s == null) {
            System.out.println("Student not found.");
        } 
        else {
            printStudent(s);
        }
    }

    private void updateStudent() {
        int id = InputUtil.readInt("Student ID");
        Student s = studentDAO.getById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        s.setEmail(InputUtil.readString("New Email"));
        s.setPhone(InputUtil.readString("New Phone"));
        s.setSkills(InputUtil.readString("New Skills"));
        studentDAO.update(s);
        System.out.println("Student updated successfully.");
    }

    private void deleteStudent() {
        int id = InputUtil.readInt("Student ID");
        if (studentDAO.delete(id)) {
            System.out.println("Student deleted (with applications, interviews and offers).");
        } 
        else {
            System.out.println("Student not found.");
        }
    }

    private void printStudent(Student s) {
        System.out.println("-----------------------------------");
        System.out.println("Student ID    : " + s.getStudentId());
        System.out.println("Name          : " + s.getName());
        System.out.println("College       : " + s.getCollege());
        System.out.println("Qualification : " + s.getQualification());
        System.out.println("Skills        : " + s.getSkills());
        System.out.println("Email         : " + s.getEmail());
        System.out.println("Phone         : " + s.getPhone());
    }
}
