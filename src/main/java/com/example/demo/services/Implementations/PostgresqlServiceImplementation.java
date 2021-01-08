package com.example.demo.services.Implementations;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.repository.EmployeeRepositoryPostgresql;
import com.example.demo.services.PostgresqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostgresqlServiceImplementation implements PostgresqlService {

    @Autowired
    EmployeeRepositoryPostgresql employeeRepositoryPostgresql;

    @Override
    public EmployeePostgresql save(EmployeePostgresql employee) {
        return employeeRepositoryPostgresql.save(employee);
    }

    @Override
    public EmployeePostgresql findById(int id) {
        return employeeRepositoryPostgresql.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        employeeRepositoryPostgresql.deleteById(id);
    }

    @Override
    public List<EmployeePostgresql> findAll() {
        Iterable<EmployeePostgresql> employeeIterable = employeeRepositoryPostgresql.findAll();
        List<EmployeePostgresql> employeeList = new ArrayList<>();
        employeeIterable.forEach(employeeList::add);
        return employeeList;
    }
}
