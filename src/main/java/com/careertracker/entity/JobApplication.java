package com.careertracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    private String companyName;
    private String jobRole;
    private LocalDate applyDate;
    private String status;   

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interview> interviews = new ArrayList<>();

    public JobApplication() {}
    public JobApplication(Student student, String companyName, String jobRole,
                          LocalDate applyDate, String status) {
        this.student = student;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.applyDate = applyDate;
        this.status = status;
    }

    public int getApplicationId() {
    	return applicationId; 
    }
    public void setApplicationId(int applicationId) { 
    	this.applicationId = applicationId;
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
    public String getJobRole() { 
    	return jobRole; 
   	}
    public void setJobRole(String jobRole) {
    	this.jobRole = jobRole; 
   	}
    public LocalDate getApplyDate() {
    	return applyDate; 
    }
    public void setApplyDate(LocalDate applyDate) { 
    	this.applyDate = applyDate;
   	}
    public String getStatus() { 
    	return status;
   	}
    public void setStatus(String status) { 
    	this.status = status; 
   	}
    public List<Interview> getInterviews() { 
    	return interviews;
   	}
    public void setInterviews(List<Interview> interviews) {
    	this.interviews = interviews; 
   	}
}
