package com.careertracker.service;
import com.careertracker.dao.InterviewDAO;
import com.careertracker.dao.JobApplicationDAO;
import com.careertracker.dao.OfferDAO;

public class AnalyticsService {

    private final JobApplicationDAO applicationDAO = new JobApplicationDAO();
    private final InterviewDAO interviewDAO = new InterviewDAO();
    private final OfferDAO offerDAO = new OfferDAO();

    public void showAnalytics() {
        long totalApplications = applicationDAO.count();
        long interviewsAttended = interviewDAO.countAttended();
        long offersReceived = offerDAO.count();

        double successRate = 0.0;
        if (totalApplications > 0) {
            successRate = (offersReceived * 100.0) / totalApplications;
        }

        System.out.println("\n====== CAREER ANALYTICS ======");
        System.out.println("Total Applications  : " + totalApplications);
        System.out.println("Interviews Attended : " + interviewsAttended);
        System.out.println("Offers Received     : " + offersReceived);
        System.out.printf("Success Rate        : %.2f%%%n", successRate);
    }
}
