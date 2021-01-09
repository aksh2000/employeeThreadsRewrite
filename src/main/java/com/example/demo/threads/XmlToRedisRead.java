package com.example.demo.threads;

import com.example.demo.fileHandler.XmlFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlToRedisRead extends Thread {

    @Autowired
    XmlFileHandler xmlFileHandler;


    @Override
    public void run() {
        xmlFileHandler.read();
    }
}
