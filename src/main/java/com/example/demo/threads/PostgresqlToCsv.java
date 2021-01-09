package com.example.demo.threads;

import com.example.demo.fileHandler.CsvFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostgresqlToCsv extends Thread {


    @Autowired
    CsvFileHandler csvFileHandler;

    @Override
    public void run() {



        // service call to read all

        csvFileHandler.writeToCsv();

        // file handler to write
    }
}
