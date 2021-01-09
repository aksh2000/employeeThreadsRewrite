package com.example.demo.collections;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.resources.Constants;

import java.util.ArrayList;
import java.util.List;

public class CollectionPostgresql implements Constants {
    private List<EmployeePostgresql> employees;
    private int writeCount;
    private int readCount;
    private int lastEmployee;


    private CollectionPostgresql() {
        employees = new ArrayList<>();
        lastEmployee = 0;
        readCount = 0;
        writeCount = 0;
    }

    private static CollectionPostgresql instance = null;

    public static synchronized CollectionPostgresql getInstance() {
        if (instance == null) {
            instance = new CollectionPostgresql();
        }
        return instance;
    }


    public synchronized void addEmployee(EmployeePostgresql employee) throws Exception {

        // method to write employee to employees list

        if (lastEmployee >= MAX_FILE_SIZE * 3) {
            throw new Exception("OverFlow");
        }

         employees.add(employee);
        lastEmployeeUpdate();

        System.out.println("Total No of Employees : " + lastEmployee + " : " + employee.toString());

    }

    public synchronized EmployeePostgresql getEmployee() throws Exception {

        if (lastEmployee < 0) {
            throw new Exception("Underflow");
        }

        // method used to read employees

        if(readCount >= MAX_FILE_SIZE * 3){
            throw new Exception("Overflow");
        }

        EmployeePostgresql t = employees.get(readCount);
        readCounterUpdate();
        return t;
    }

    public synchronized void writeCounterUpdate(){
        writeCount++;
    }


    public synchronized void lastEmployeeUpdate() {
        lastEmployee++;
    }

    public synchronized void readCounterUpdate() {
        readCount++;
    }


    public int getReadCount() {
        return readCount;
    }

    public int getWriteCount() {
        return writeCount;
    }

    public List<EmployeePostgresql> getEmployees() {
        return employees;
    }


}


