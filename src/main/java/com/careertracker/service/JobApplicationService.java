package com.careertracker.service;

import com.careertracker.dao.JobApplicationDAO;
import com.careertracker.dao.StudentDAO;
import com.careertracker.entity.JobApplication;
import com.careertracker.entity.Student;
import com.careertracker.util.InputUtil;

import java.time.LocalDate;
import java.util.List;

public class JobApplicationService {

    private final JobApplicationDAO applicationDAO = new JobApplicationDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    public void menu() {
        int choice;
        do {
            System.out.println("\n------ JOB APPLICATIONS ------");
            System.out.println("1. Apply Job");
            System.out.println("2. View All Applications");
            System.out.println("3. View Application By ID");
            System.out.println("4. Update Status");
            System.out.println("5. Delete Application");
            System.out.println("6. Back");
            choice = InputUtil.readInt("Enter choice");

            switch (choice) {
                case 1:
                	applyJob();
                	break;
                case 2:
                	viewAll();
                	break;
                case 3:
                	viewById();
                	break;
                case 4: 
                	updateStatus(); 
                	break;
                case 5: 
                	deleteApplication(); 
                	break;
                case 6:
                	break;
                default: 
                	System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private void applyJob() {
        int studentId = InputUtil.readInt("Student ID");
        Student student = studentDAO.getById(studentId);
        if (student == null) {
            System.out.println("Student not found. Add the student first.");
            return;
        }
        String company = InputUtil.readString("Company Name");
        String role = InputUtil.readString("Job Role");
        LocalDate date = InputUtil.readDate("Apply Date");
        String status = InputUtil.choose("Status:","APPLIED", "SHORTLISTED", "INTERVIEW", "SELECTED", "REJECTED");
        applicationDAO.save(new JobApplication(student, company, role, date, status));
        System.out.println("Application saved successfully.");
    }

    private void viewAll() {
        List<JobApplication> list = applicationDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }
        for (JobApplication a : list) {
            print(a);
        }
    }

    private void viewById() {
        int id = InputUtil.readInt("Application ID");
        JobApplication a = applicationDAO.getById(id);
        if (a == null) {
            System.out.println("Application not found.");
        } else {
            print(a);
        }
    }

    private void updateStatus() {
        int id = InputUtil.readInt("Application ID");
        JobApplication a = applicationDAO.getById(id);
        if (a == null) {
            System.out.println("Application not found.");
            return;
        }
        String status = InputUtil.choose("New Status:","APPLIED", "SHORTLISTED", "INTERVIEW", "SELECTED", "REJECTED");
        a.setStatus(status);
        applicationDAO.update(a);
        System.out.println("Status updated to " + status);
    }

    private void deleteApplication() {
        int id = InputUtil.readInt("Application ID");
        if (applicationDAO.delete(id)) {
            System.out.println("Application deleted.");
        } else {
            System.out.println("Application not found.");
        }
    }

    private void print(JobApplication a) {
        System.out.println("-----------------------------------");
        System.out.println("Application ID : " + a.getApplicationId());
        System.out.println("Student        : " + a.getStudent().getName() + " (ID " + a.getStudent().getStudentId() + ")");
        System.out.println("Company        : " + a.getCompanyName());
        System.out.println("Role           : " + a.getJobRole());
        System.out.println("Apply Date     : " + a.getApplyDate());
        System.out.println("Status         : " + a.getStatus());
    }
}
