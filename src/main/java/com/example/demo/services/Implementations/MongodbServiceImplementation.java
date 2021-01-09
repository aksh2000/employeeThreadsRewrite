package com.example.demo.services.Implementations;

import com.example.demo.entity.EmployeeMongodb;
import com.example.demo.repository.EmployeeRepositoryMongodb;
import com.example.demo.services.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongodbServiceImplementation implements MongodbService {

    @Autowired
    EmployeeRepositoryMongodb employeeRepositoryMongodb;


    @Override
    public EmployeeMongodb save(EmployeeMongodb employee) {
        return employeeRepositoryMongodb.save(employee);
    }

    @Override
    public EmployeeMongodb findById(int id) {
        return employeeRepositoryMongodb.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        employeeRepositoryMongodb.deleteById(id);
    }

    @Override
    public List<EmployeeMongodb> findAll() {
        Iterable<EmployeeMongodb> employeeIterable = employeeRepositoryMongodb.findAll();
        List<EmployeeMongodb> employeeList = new ArrayList<>();
        employeeIterable.forEach(employeeList::add);
        return employeeList;
    }
}
