package com.example.demo.webclient.service;

import com.example.demo.webclient.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EmployeeService {

    public Mono<Employee> getEmployee(Integer id) {
        log.info("EmployeeService: (id:{id})", id);
        Employee e = Employee.builder().id(id).name("user").age(4).build();
        return Mono.just(e);
    }

}
