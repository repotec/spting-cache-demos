package com.spring.cache.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(value="employees")
    public List<Employee> findAll(){
        log.info("findAll");
        return employeeRepository.findAll();
    }

    @Caching(evict = {
            @CacheEvict(value="employee", allEntries=true),
            @CacheEvict(value="employees", allEntries=true)})
    public Employee insertOrUpdateEmployee(Employee employee){
        log.info("findAll");
        return employeeRepository.save(employee);
    }

    @CachePut(value = "employee")
    public void deleteEmployee(long employeeId){
        log.info("findAll");
        employeeRepository.deleteById(employeeId);
    }
}
