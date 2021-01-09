package com.example.demo.threads;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.fileHandler.CsvFileHandler;
import com.example.demo.services.Implementations.PostgresqlServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CsvToPostgresqlRead extends Thread{

    @Autowired
    CsvFileHandler handler;

    @Override
    public void run() {

        handler.readFromFile();

    }
}
