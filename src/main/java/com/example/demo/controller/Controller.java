package com.example.demo.controller;


import com.example.demo.services.ReadAndWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class Controller {


    @Autowired
    ReadAndWriteService readAndWriteService;


    @GetMapping(value = "/startService")
    String start(){

        readAndWriteService.readAndWrite();

        return "All actions performed";

    }
}
