package com.example.demo.services.Implementations;


import com.example.demo.services.ReadAndWriteService;
import com.example.demo.threads.CsvToPostgresqlRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadAndWriteServiceImplementation implements ReadAndWriteService {



    @Override
    public void readAndWrite() {

        CsvToPostgresqlRead csvToPostgresqlRead = new CsvToPostgresqlRead();

        // read threads block
        csvToPostgresqlRead.start();




        // write threads block
    }
}
