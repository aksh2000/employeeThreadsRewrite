package com.example.demo.repository;


import com.example.demo.entity.EmployeePostgresql;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryPostgresql extends CrudRepository<EmployeePostgresql, Integer> {

}