package com.careertracker.service;

import com.careertracker.dao.OfferDAO;
import com.careertracker.dao.StudentDAO;
import com.careertracker.entity.Offer;
import com.careertracker.entity.Student;
import com.careertracker.util.InputUtil;

import java.time.LocalDate;
import java.util.List;

public class OfferService {

    private final OfferDAO offerDAO = new OfferDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    public void menu() {
        int choice;
        do {
            System.out.println("\n------ OFFERS ------");
            System.out.println("1. Add Offer");
            System.out.println("2. View Offers");
            System.out.println("3. Highest Package Offer");
            System.out.println("4. Update Offer Status");
            System.out.println("5. Delete Offer");
            System.out.println("6. Back");
            choice = InputUtil.readInt("Enter choice");

            switch (choice) {
                case 1: 
                	addOffer();
                	break;
                case 2:
                	viewOffers(); 
                	break;
                case 3: 
                	getHighestPackageOffer(); 
                	break;
                case 4:
                	updateOfferStatus();
                	break;
                case 5: 
                	deleteOffer(); 
                	break;
                case 6: 
                	break;
                default: 
                	System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private void addOffer() {
        int studentId = InputUtil.readInt("Student ID");
        Student student = studentDAO.getById(studentId);
        if (student == null) {
            System.out.println("Student not found. Add the student first.");
            return;
        }
        String company = InputUtil.readString("Company Name");
        double pkg = InputUtil.readDouble("Package (LPA)");
        LocalDate joining = InputUtil.readDate("Joining Date");
        String status = InputUtil.choose("Offer Status:", "PENDING", "ACCEPTED", "REJECTED");

        offerDAO.save(new Offer(student, company, pkg, joining, status));
        System.out.println("Offer added successfully.");
    }

    private void viewOffers() {
        List<Offer> list = offerDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No offers found.");
            return;
        }
        for (Offer o : list) {
            print(o);
        }
    }

    public void getHighestPackageOffer() {
        Offer o = offerDAO.getHighestPackageOffer();
        if (o == null) {
            System.out.println("No offers found.");
            return;
        }
        System.out.println("\n--- Highest Package Offer ---");
        System.out.println("Company : " + o.getCompanyName());
        System.out.println("Package : " + o.getPackageLpa() + " LPA");
    }

    private void updateOfferStatus() {
        int id = InputUtil.readInt("Offer ID");
        Offer o = offerDAO.getById(id);
        if (o == null) {
            System.out.println("Offer not found.");
            return;
        }
        String status = InputUtil.choose("New Offer Status:", "PENDING", "ACCEPTED", "REJECTED");
        o.setOfferStatus(status);
        offerDAO.update(o);
        System.out.println("Offer status updated to " + status);
    }

    private void deleteOffer() {
        int id = InputUtil.readInt("Offer ID");
        if (offerDAO.delete(id)) {
            System.out.println("Offer deleted.");
        } 
        else {
            System.out.println("Offer not found.");
        }
    }

    private void print(Offer o) {
        System.out.println("-----------------------------------");
        System.out.println("Offer ID     : " + o.getOfferId());
        System.out.println("Student      : " + o.getStudent().getName());
        System.out.println("Company      : " + o.getCompanyName());
        System.out.println("Package      : " + o.getPackageLpa() + " LPA");
        System.out.println("Joining Date : " + o.getJoiningDate());
        System.out.println("Offer Status : " + o.getOfferStatus());
    }
}
