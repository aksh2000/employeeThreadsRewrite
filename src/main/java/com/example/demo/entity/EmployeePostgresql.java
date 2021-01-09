package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "employees")
public class EmployeePostgresql {

    @Id
    private int id;

    private String firstName;
    private String lastName;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBirth;


    private double experience;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public double getExperience() {
        return experience;
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


}
