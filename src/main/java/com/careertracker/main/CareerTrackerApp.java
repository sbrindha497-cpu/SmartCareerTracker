package com.careertracker.main;

import com.careertracker.service.AnalyticsService;
import com.careertracker.service.InterviewService;
import com.careertracker.service.JobApplicationService;
import com.careertracker.service.OfferService;
import com.careertracker.service.StudentService;
import com.careertracker.util.HibernateUtil;
import com.careertracker.util.InputUtil;

public class CareerTrackerApp {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        JobApplicationService applicationService = new JobApplicationService();
        InterviewService interviewService = new InterviewService();
        OfferService offerService = new OfferService();
        AnalyticsService analyticsService = new AnalyticsService();

        int choice;
        do {
            System.out.println("\n==================================");
            System.out.println("      SMART CAREER TRACKER");
            System.out.println("==================================");
            System.out.println("1. Student Profile");
            System.out.println("2. Job Applications");
            System.out.println("3. Interviews");
            System.out.println("4. Offers");
            System.out.println("5. Career Analytics");
            System.out.println("6. Exit");
            choice = InputUtil.readInt("Enter choice");
            
            switch (choice) {
                case 1: 
                	studentService.menu(); 
                	break;
                case 2: 
                	applicationService.menu(); 
                	break;
                case 3:
                	interviewService.menu(); 
                	break;
                case 4: 
                	offerService.menu(); 
                	break;
                case 5: 
                	analyticsService.showAnalytics(); 
                	break;
                case 6: 
                	System.out.println("Thank you! Best of luck for your career."); 
                	break;
                default: 
                	System.out.println("Invalid choice.");
            }
        }
        while (choice != 6);
        HibernateUtil.shutdown();
    }
}
