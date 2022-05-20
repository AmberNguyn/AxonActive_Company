package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Employee;
import com.axonactive.company.entity.Gender;
import com.axonactive.company.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeServiceImplTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    void getAllEmployee_returnNoData_WhenJustCreateTable()
    {
        List<Employee> employees = employeeService.getAll();
        assertEquals(0, employees.size());
    }

    @Test
    void saveEmployee_shouldReturnOne_WhenAddOneEmployee()
    {
        Employee employee = Employee.builder()
                .employeeIdFake("01")
                .dateOfBirth(LocalDate.of(1995,12,20))
                .firstName("Anh")
                .gender(Gender.FEMALE)
                .lastName("Nguyen")
                .middleName("Ngoc")
                .salary(2000)
                .build();
        employeeService.saveEmployee(employee);
        List<Employee> employees = employeeService.getAll();
        assertEquals(1, employees.size());
    }

    @Test
    void deleteEmployee_shouldReturnZero_whenDeleteDataFromAListOfOne()
    {
        Employee employee = Employee.builder()
                .employeeIdFake("01")
                .dateOfBirth(LocalDate.of(1995,12,20))
                .firstName("Anh")
                .gender(Gender.FEMALE)
                .lastName("Nguyen")
                .middleName("Ngoc")
                .salary(2000)
                .build();
        employeeService.saveEmployee(employee);
        employeeService.deleteEmployee("01");
        List<Employee> employees = employeeService.getAll();
        assertEquals(0, employees.size());
    }

    @Test
    void findEmployeeById_shouldReturnAnEmployee_WhenInputAnId()
    {
        Employee employee = Employee.builder()
                .employeeIdFake("01")
                .dateOfBirth(LocalDate.of(1995,12,20))
                .firstName("Anh")
                .gender(Gender.FEMALE)
                .lastName("Nguyen")
                .middleName("Ngoc")
                .salary(2000)
                .build();
        employeeService.saveEmployee(employee);
        assertEquals(employee, employeeService.findEmployeeById(1).get());
    }
}