package com.example.demo.services.Implementations;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.entity.EmployeeRedis;
import com.example.demo.repository.EmployeeRepositoryRedis;
import com.example.demo.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RedisServiceImplementation implements RedisService {

    @Autowired
    EmployeeRepositoryRedis employeeRepositoryRedis;

    @Override
    public EmployeeRedis save(EmployeeRedis employee) {
        return employeeRepositoryRedis.save(employee);
    }

    @Override
    public EmployeeRedis findById(int id) {
        return employeeRepositoryRedis.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        employeeRepositoryRedis.deleteById(id);
    }

    @Override
    public List<EmployeeRedis> findAll() {
        Iterable<EmployeeRedis> employeeIterable = employeeRepositoryRedis.findAll();
        List<EmployeeRedis> employeeList = new ArrayList<>();
        employeeIterable.forEach(employeeList::add);
        return employeeList;
    }
}
