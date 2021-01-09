package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "emplpoyeeData")
public class EmployeeMongodb {

    @Id
    private int id;

    private String firstName;
    private String lastName;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBirth;

    private double salary;
    private double experience;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public double getExperience() {
        return experience;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {

        try {
            Date newDate = new SimpleDateFormat("dd/mm/yy").parse(dateOfBirth);
            this.dateOfBirth = newDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setExperience(String experience) {
        double parsedExperience = Double.parseDouble(experience);
        this.experience = parsedExperience;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
