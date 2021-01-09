package com.example.demo.services;

import com.example.demo.entity.EmployeeMongodb;

import java.util.List;

public interface MongodbService {

    EmployeeMongodb save(EmployeeMongodb employee);

    EmployeeMongodb findById(int id);

    void deleteById(int id);

    List<EmployeeMongodb> findAll();
}
