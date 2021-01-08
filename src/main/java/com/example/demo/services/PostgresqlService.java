package com.example.demo.services;

import com.example.demo.entity.EmployeePostgresql;

import java.util.List;

public interface PostgresqlService {

    EmployeePostgresql save(EmployeePostgresql employee);

    EmployeePostgresql findById(int id);

    void deleteById(int id);

    List<EmployeePostgresql> findAll();
}
