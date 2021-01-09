package com.example.demo.fileHandler;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.resources.Constants;
import com.example.demo.services.Implementations.PostgresqlServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileHandler implements Constants {

    @Autowired
    PostgresqlServiceImplementation postgresqlServiceImplementation;

//    List<EmployeePostgresql> employees = new ArrayList<>();
    // method to read from file

    public synchronized void readFromFile() {

        try {
            File file = new File("/Users/akshithvarma/Downloads/employee.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            int iter = 0;
            while ((line = br.readLine()) != null) {
                EmployeePostgresql emp = new EmployeePostgresql();
                tempArr = line.split(",");
                emp.setFirstName(tempArr[0]);
                emp.setLastName(tempArr[1]);
                emp.setDateOfBirth(tempArr[2]);
                emp.setExperience(tempArr[3]);
//                employees.add(emp);

                postgresqlServiceImplementation.save(emp);

                iter++;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }





    // method to write to csv file
}


//    public static void main(String[] args) {
//        CsvFileHandler csv=new CsvFileHandler();
//        List<EmployeePostgresql> employeePostgresqls = csv.readFromFile();
//
//        for (EmployeePostgresql e: employeePostgresqls
//             ) {
//            System.out.println(e.toString());
//        }
//
//    }