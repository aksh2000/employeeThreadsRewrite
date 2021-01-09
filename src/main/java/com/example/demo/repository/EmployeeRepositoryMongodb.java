package com.example.demo.repository;


import com.example.demo.entity.EmployeeMongodb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryMongodb extends MongoRepository<EmployeeMongodb, Integer> {
}
