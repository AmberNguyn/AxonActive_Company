package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Employee;
import com.axonactive.company.entity.Gender;
import com.axonactive.company.service.EmployeeService;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private Employee employee;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .employeeIdFake("01")
                .dateOfBirth(LocalDate.of(1995, 12, 20))
                .firstName("Anh")
                .gender(Gender.FEMALE)
                .lastName("Nguyen")
                .middleName("Ngoc")
                .salary(2000)
                .build();

        employee1 = Employee.builder()
                .employeeIdFake("02")
                .dateOfBirth(LocalDate.of(1992, 11, 11))
                .firstName("An")
                .gender(Gender.FEMALE)
                .lastName("Tong")
                .middleName("Phan Hoang")
                .salary(10000)
                .build();

        employee2 = Employee.builder()
                .employeeIdFake("03")
                .dateOfBirth(LocalDate.of(1999, 12, 11))
                .firstName("Thinh")
                .gender(Gender.FEMALE)
                .lastName("Nguyen")
                .middleName("Hung")
                .salary(10000)
                .build();
    }


    @Test
    void getAllEmployee_returnNoData_WhenJustCreateTable() {
        List<Employee> employees = employeeService.getAll();
        assertEquals(0, employees.size());
    }

    @Test
    void saveEmployee_shouldReturnOne_WhenAddOneEmployee() {
        employeeService.saveEmployee(employee);
        List<Employee> employees = employeeService.getAll();
        assertEquals(1, employees.size());
    }

    @Test
    void deleteEmployee_shouldReturnZero_whenDeleteDataFromAListOfOne() {
        employeeService.saveEmployee(employee);
        employeeService.deleteEmployee("01");
        List<Employee> employees = employeeService.getAll();
        assertEquals(0, employees.size());
    }

    @Test
    void findEmployeeById_shouldReturnAnEmployee_WhenInputAnId() {
        employeeService.saveEmployee(employee);
        assertEquals(employee, employeeService.findEmployeeById(1).get());
    }

    @Test
    void findByFirstNameAndLastName_shouldReturnZero_WhenNotFound() {
        employeeService.saveEmployee(employee);
        assertEquals(0, employeeService.findByFirstNameAndLastName("Linh", "Tran").size());
    }

    @Test
    void findByFirstNameAndLastName_shouldReturnAListOfEmployee_WhenFound() {
        employeeService.saveEmployee(employee);
        assertNotNull(employeeService.findByFirstNameAndLastName("Anh", "Nguyen"));
    }

    @Test
    void findByFirstNameOrLastName_shouldReturnZero_whenNotFound() {
        employeeService.saveEmployee(employee);
        assertEquals(0, employeeService.findByFirstNameOrLastName("Linh", "Linh").size());
    }

    @Test
    void findByFirstNameOrLastName_shouldReturnAListOfOne_WhenFound() {
        employeeService.saveEmployee(employee);
        assertNotNull(employeeService.findByFirstNameOrLastName("Anh", "Anh"));
    }

    @Test
    void findByFirstNameLike_shouldReturnNoData_WhenNotFound() {
        employeeService.saveEmployee(employee);
        assertEquals(0, employeeService.findByFirstNameLike("nguyen").size());
    }

    @Test
    void findByFirstNameLike_shouldReturnAListOfNames_WhenFound() {
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee);
        assertNotNull(employeeService.findByFirstNameLike("anh"));
    }

    @Test
    void findByFirstNameNotLike_shouldReturnNoData_WhenFoundNameMatchesInput() {
        employeeService.saveEmployee(employee);
        assertEquals(0, employeeService.findByFirstNameNotLike("Anh").size());
    }

    @Test
    void findByFirstNameNotLike_shouldReturnAListOfNames_WhenFoundNamesDoNotMatchInput() {
        employeeService.saveEmployee(employee);
        assertNotNull(employeeService.findByFirstNameNotLike("linh"));
    }

    @Test
    void findByFirstNameAndLastNameLike_shouldReturnNoData_WhenNotFound() {
        employeeService.saveEmployee(employee);
        assertEquals(0, employeeService.findByFirstNameAndLastNameLike("anh", "nguyen").size());
    }

    @Test
    void findByFistNameAndLastNameLike_shouldReturnAListOfNames_WhenFound() {
        employeeService.saveEmployee(employee);
        assertNotNull(employeeService.findByFirstNameAndLastNameLike("Anh", "Nguyen"));
    }

    @Test
    void findEmployeeByFirstNameIgnoreCase_shouldReturnAListOfNames_WhenFound() {
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee);
        assertNotNull(employeeService.findEmployeeByFirstNameIgnoreCase("anh"));
    }

    @Test
    void findEmployeeByFirstNameContaining_shouldReturnNoData_WhenNotFound() {
        employeeService.saveEmployee(employee);
        assertEquals(0, employeeService.findEmployeeByFirstNameContaining("uyen").size());
//        assertEquals(0, employeeService.findEmployeeByFirstNameContaining("kl").size());

    }

    @Test
    void findEmployeeByFirstNameStartingWith_shouldReturnAListOfNames_WhenFound() {
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee1);
        assertEquals(2, employeeService.findEmployeeByFirstNameStartingWith("A").size());
    }

    @Test
    void findEmployeeByFirstNameStartingWith_shouldReturnNoData_WhenNotFound() {
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee);
        assertEquals(0, employeeService.findEmployeeByFirstNameStartingWith("V").size());
    }

    @Test
    void findEmployeeByDateOfBirthBefore_shouldReturnNoData_WhenNotFound() {
        employeeService.saveEmployee(employee);
        assertNotNull(employeeService.findEmployeeByDateOfBirthBefore(LocalDate.of(1999, 01, 01)));
    }

    @Test
    void findEmployeeByDateOfBirthBefore_shouldReturnAListOfDays_WhenFound() {
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee1);
        assertEquals(2, employeeService.findEmployeeByDateOfBirthBefore(LocalDate.of(2000, 01, 01)).size());
    }

    @Test
    void findEmployeeByDateOfBirthBetween_shouldReturnAListOfEmployee_WhenFound() {
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
        assertEquals(1, employeeService.findEmployeeByDateOfBirthBetween(LocalDate.of(1992, 01, 01), (LocalDate.of(1995, 01, 01))).size());
    }

    @Test
    void findEmployeeByDateOfBirthBetween_shouldReturnNoData_WhenNotFound() {
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
        Assertions.assertNotNull(employeeService.findEmployeeByDateOfBirthBetween(LocalDate.of(1993, 01, 01), (LocalDate.of(1993, 12, 31))));
    }


    @Test
    void findEmployeeBySalaryOrderByLastNameDesc_shouldReturnAListOfEmployees_whenMatchConditions() {
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
        assertEquals(2, employeeService.findEmployeeBySalaryOrderByLastNameDesc(10000).size());
        assertTrue((employeeService.findEmployeeBySalaryOrderByLastNameDesc(10000).get(0).getLastName().compareTo(employeeService.findEmployeeBySalaryOrderByLastNameDesc(10000).get(1).getLastName())) > 0);
    }


}