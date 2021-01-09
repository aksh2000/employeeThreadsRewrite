package com.example.demo.services.Implementations;


import com.example.demo.services.ReadAndWriteService;
import com.example.demo.threads.CsvToPostgresqlRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadAndWriteServiceImplementation implements ReadAndWriteService {


    @Autowired
    CsvToPostgresqlRead csvToPostgresqlRead;

    @Override
    public void readAndWrite() {


        // read threads block
        csvToPostgresqlRead.start();

        try {
            csvToPostgresqlRead.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // write threads block
    }
}
