package com.careertracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewId;

    @ManyToOne
    @JoinColumn(name = "applicationId")
    private JobApplication application;

    private String companyName;
    private LocalDate interviewDate;
    private String interviewType;   
    private String result;         

    public Interview() {}

    public Interview(JobApplication application, String companyName,
                     LocalDate interviewDate, String interviewType, String result) {
        this.application = application;
        this.companyName = companyName;
        this.interviewDate = interviewDate;
        this.interviewType = interviewType;
        this.result = result;
    }

    public int getInterviewId() {
    	return interviewId;
    }
    public void setInterviewId(int interviewId) {
    	this.interviewId = interviewId; 
   	}
    public JobApplication getApplication() {
    	return application;
   	}  
    public void setApplication(JobApplication application) { 
    	this.application = application; 
   	}
    public String getCompanyName() {
    	return companyName; 
   	}
    public void setCompanyName(String companyName) { 
    	this.companyName = companyName;
   	}
    public LocalDate getInterviewDate() {
    	return interviewDate; 
   	}
    public void setInterviewDate(LocalDate interviewDate) {
    	this.interviewDate = interviewDate;
   	}
    public String getInterviewType() {
    	return interviewType; 
   	}
    public void setInterviewType(String interviewType) {
    	this.interviewType = interviewType;
   	}
    public String getResult() {
    	return result;
   	}
    public void setResult(String result) { 
    	this.result = result; 
   	}  
}
