package com.example.demo.threads;

import com.example.demo.fileHandler.JsonFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonToMongo extends Thread {

    @Autowired
    JsonFileHandler jsonFileHandler;

    @Override
    public void run() {
        jsonFileHandler.readFromFile();
    }
}
