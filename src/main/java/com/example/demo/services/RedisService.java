package com.example.demo.services;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.entity.EmployeeRedis;

import java.util.List;

public interface RedisService {
    EmployeeRedis save(EmployeeRedis employee);

    EmployeeRedis findById(int id);

    void deleteById(int id);

    List<EmployeeRedis> findAll();
}
