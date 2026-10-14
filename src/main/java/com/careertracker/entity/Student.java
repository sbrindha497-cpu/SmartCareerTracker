package com.careertracker.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;
    private String college;
    private String qualification;
    private String skills;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> applications = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers = new ArrayList<>();

    public Student() {}

    public Student(String name, String college, String qualification,
                   String skills, String email, String phone) {
        this.name = name;
        this.college = college;
        this.qualification = qualification;
        this.skills = skills;
        this.email = email;
        this.phone = phone;
    }

    public int getStudentId() {
    	return studentId;
    }
    public void setStudentId(int studentId) { 
    	this.studentId = studentId;
    }
    public String getName() { 
    	return name; 
    }
    public void setName(String name) { 
    	this.name = name; 
    }
    public String getCollege() { 
    	return college; 
    }
    public void setCollege(String college) { 
    	this.college = college;
    }
    public String getQualification() { 
    	return qualification;
    }
    public void setQualification(String qualification) {
    	this.qualification = qualification;
    }
    public String getSkills() { 
    	return skills; 
    }
    public void setSkills(String skills) {
    	this.skills = skills; 
    }
    public String getEmail() {
    	return email; 
   	}
    public void setEmail(String email) { 
    	this.email = email; 
   	}
    public String getPhone() { 
    	return phone;
   	}
    public void setPhone(String phone) {
    	this.phone = phone; 
   	}
    public List<JobApplication> getApplications() { 
    	return applications; 
    }
    public void setApplications(List<JobApplication> applications) { 
    	this.applications = applications;
   	}
    public List<Offer> getOffers() {
    	return offers; 
   	}
    public void setOffers(List<Offer> offers) { 
    	this.offers = offers; 
    }
}
