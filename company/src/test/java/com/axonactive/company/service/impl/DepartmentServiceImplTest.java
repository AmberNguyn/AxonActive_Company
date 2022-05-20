package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Department;
import com.axonactive.company.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DepartmentServiceImplTest {
    @Autowired
    DepartmentService departmentService;

    @Test
    void testGetAllDepartment_returnNoData_whenJustCreateTable()
    {
        List<Department> departments = departmentService.getAll();
        assertEquals(0, departments.size());
    }

    @Test
    void testSaveDepartment_returnOneMoreData_WhenAddOne()
    {
        Department department = Department.builder()
                .departmentName("Sales")
                .startDate(LocalDate.of(2000,9,9))
                .build();
        departmentService.saveDepartment(department);
        List<Department> departments = departmentService.getAll();
        assertEquals(1, departments.size());
    }

    @Test
    void testDeleteDepartment_shouldReturnNoData_WhenDeleteFromAListOfOneData()
    {
        Department department = Department.builder()
                .departmentName("Sales")
                .startDate(LocalDate.of(2000,9,9))
                .build();
        departmentService.saveDepartment(department);
        departmentService.deleteDepartment(1);
        List<Department> departments = departmentService.getAll();
        assertEquals(0, departments.size());
    }

    @Test
    void testFindDepartmentById_shouldReturnADepartment_whenInputAnId()
    {
        Department department = Department.builder()
                .departmentName("Sales")
                .startDate(LocalDate.of(2000,9,9))
                .build();
        departmentService.saveDepartment(department);
        assertEquals(department, departmentService.findDepartmentById(1).get() );
    }


}