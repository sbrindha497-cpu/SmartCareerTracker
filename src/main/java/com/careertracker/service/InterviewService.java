package com.careertracker.service;

import com.careertracker.dao.InterviewDAO;
import com.careertracker.dao.JobApplicationDAO;
import com.careertracker.entity.Interview;
import com.careertracker.entity.JobApplication;
import com.careertracker.util.InputUtil;

import java.time.LocalDate;
import java.util.List;

public class InterviewService {

    private final InterviewDAO interviewDAO = new InterviewDAO();
    private final JobApplicationDAO applicationDAO = new JobApplicationDAO();

    public void menu() {
        int choice;
        do {
            System.out.println("\n------ INTERVIEWS ------");
            System.out.println("1. Schedule Interview");
            System.out.println("2. View Interviews");
            System.out.println("3. Update Interview Result");
            System.out.println("4. Delete Interview");
            System.out.println("5. Back");
            choice = InputUtil.readInt("Enter choice");

            switch (choice) {
                case 1:
                	scheduleInterview(); 
                	break;
                case 2: 
                	viewInterviews();
                	break;
                case 3: 
                	updateResult();
                	break;
                case 4:
                	deleteInterview();
                	break;
                case 5: 
                	break;
                default: 
                	System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private void scheduleInterview() {
        int appId = InputUtil.readInt("Application ID");
        JobApplication app = applicationDAO.getById(appId);
        if (app == null) {
            System.out.println("Application not found.");
            return;
        }
 
        String company = app.getCompanyName();
        System.out.println("Company        : " + company);

        LocalDate date = InputUtil.readDate("Interview Date");
        String type = InputUtil.choose("Interview Type:", "Aptitude", "Technical", "HR", "Managerial");

        interviewDAO.save(new Interview(app, company, date, type, "PENDING"));
        System.out.println("Interview scheduled (Result : PENDING).");
    }

    private void viewInterviews() {
        List<Interview> list = interviewDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No interviews found.");
            return;
        }
        for (Interview i : list) {
            System.out.println("-----------------------------------");
            System.out.println("Interview ID   : " + i.getInterviewId());
            System.out.println("Application ID : " + i.getApplication().getApplicationId());
            System.out.println("Company        : " + i.getCompanyName());
            System.out.println("Date           : " + i.getInterviewDate());
            System.out.println("Type           : " + i.getInterviewType());
            System.out.println("Result         : " + i.getResult());
        }
    }

    private void updateResult() {
        int id = InputUtil.readInt("Interview ID");
        Interview i = interviewDAO.getById(id);
        if (i == null) {
            System.out.println("Interview not found.");
            return;
        }
        String result = InputUtil.choose("New Result:", "PASSED", "FAILED", "PENDING");
        i.setResult(result);
        interviewDAO.update(i);
        System.out.println("Interview result updated to " + result);
    }

    private void deleteInterview() {
        int id = InputUtil.readInt("Interview ID");
        if (interviewDAO.delete(id)) {
            System.out.println("Interview deleted.");
        } else {
            System.out.println("Interview not found.");
        }
    }
}
