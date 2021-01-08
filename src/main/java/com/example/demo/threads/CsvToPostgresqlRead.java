package com.example.demo.threads;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.fileHandler.CsvFileHandler;
import com.example.demo.services.Implementations.PostgresqlServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class CsvToPostgresqlRead extends Thread{



    @Autowired
    PostgresqlServiceImplementation postgresqlServiceImplementation;

    @Override
    public void run() {

        CsvFileHandler handler = new CsvFileHandler();

        List<EmployeePostgresql> employeePostgresqlList = handler.readFromFile();


        for(int i = 0; i<employeePostgresqlList.size(); i++){

            EmployeePostgresql e = employeePostgresqlList.get(i);
            System.out.println(e.toString());

            postgresqlServiceImplementation.save(e);
        }



    }
}
