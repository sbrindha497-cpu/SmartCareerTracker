package com.careertracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    private String companyName;
    private double packageLpa;       
    private LocalDate joiningDate;
    private String offerStatus;

    public Offer() {}

    public Offer(Student student, String companyName, double packageLpa,
                 LocalDate joiningDate, String offerStatus) {
        this.student = student;
        this.companyName = companyName;
        this.packageLpa = packageLpa;
        this.joiningDate = joiningDate;
        this.offerStatus = offerStatus;
    }

    public int getOfferId() { 
    	return offerId; 
    }
    public void setOfferId(int offerId) { 
    	this.offerId = offerId; 
   	}
    public Student getStudent() {
    	return student;
   	}
    public void setStudent(Student student) { 
    	this.student = student; 
   	}
    public String getCompanyName() { 
    	return companyName;
   	}
    public void setCompanyName(String companyName) {
    	this.companyName = companyName; 
   	}
    public double getPackageLpa() { 
    	return packageLpa; 
   	}
    public void setPackageLpa(double packageLpa) { 
    	this.packageLpa = packageLpa; 
   	}
    public LocalDate getJoiningDate() { 
    	return joiningDate; 
   	}
    public void setJoiningDate(LocalDate joiningDate) {
    	this.joiningDate = joiningDate; 
   	}
    public String getOfferStatus() {
    	return offerStatus; 
   	}
    public void setOfferStatus(String offerStatus) { 
    	this.offerStatus = offerStatus;
   	}
}
