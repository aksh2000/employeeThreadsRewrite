package com.example.demo.repository;


import com.example.demo.entity.EmployeeRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryRedis extends CrudRepository<EmployeeRedis, Integer> {
}
