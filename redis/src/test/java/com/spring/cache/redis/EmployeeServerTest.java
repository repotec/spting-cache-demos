package com.spring.cache.redis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServerTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CacheManager cacheManager;

    @Test
    public void BeforeAdviceAnyLogBeforeAnyArrayOfStudents_test() {
        List<Employee> emps = employeeService.findAll();

        assertEquals(empty(), getCached(""));
    }

    private Optional<Employee> getCached(String key) {
        System.out.println("***********************************");
        Cache cache = cacheManager.getCache("employees");
        System.out.println( cache.getName());
        return ofNullable(cacheManager.getCache("employees")).map(c -> c.get(key, Employee.class));
    }
}
