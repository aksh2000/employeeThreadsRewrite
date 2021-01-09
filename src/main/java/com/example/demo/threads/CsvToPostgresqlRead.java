package com.example.demo.threads;

import com.example.demo.collections.CollectionPostgresql;
import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.fileHandler.CsvFileHandler;
import com.example.demo.services.Implementations.PostgresqlServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class CsvToPostgresqlRead extends Thread{

    private CollectionPostgresql collectionPostgresql = CollectionPostgresql.getInstance();




    @Override
    public void run() {

        CsvFileHandler handler = new CsvFileHandler();
        handler.readFromFile();

    }
}
