package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Department;
import com.axonactive.company.entity.DepartmentLocation;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.service.DepartmentLocationService;
import com.axonactive.company.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DepartmentLocationServiceImplTest {
    @Autowired
    DepartmentLocationService departmentLocationService;

    DepartmentLocation departmentLocation1;
    DepartmentLocation departmentLocation2;

    //Before save
    @Test
    void getAllDepartmentLocation_returnNoData_WhenJustCreateTable(){
        List<DepartmentLocation> departmentLocations = departmentLocationService.getAll();
        assertEquals(0, departmentLocations.size());
    }

    //After save
    @Nested
    class casesTestedAfterSave
    {
        @Autowired
        DepartmentLocationService departmentLocationService;

        @Autowired
        DepartmentService departmentService;

        DepartmentLocation departmentLocation1;
        DepartmentLocation departmentLocation2;

        @BeforeEach
        void setUp()
        {
            Department department1 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2000,01,01))
                    .build();
            departmentService.saveDepartment(department1);

            departmentLocation1 = DepartmentLocation.builder()
                    .location("HongKong")
                    .department(department1)
                    .build();
            departmentLocationService.saveDepartmentLocation(departmentLocation1);

            Department department2 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2001,02,02))
                    .build();

            departmentService.saveDepartment(department2);

            departmentLocation2 = DepartmentLocation.builder()
                    .location("Vietnam")
                    .department(department2)
                    .build();

            departmentLocationService.saveDepartmentLocation(departmentLocation2);
        }

        @Test
        void saveDepartmentLocation_returnTwoData_WhenAddOne()
        {
            List<DepartmentLocation> departments = departmentLocationService.getAll();
            assertEquals(2, departments.size());
        }

        @Test
        void deleteDepartmentLocation_ShouldReturnOneData_WhenDeleteOneDataFromAListOfTwo()
        {
            departmentLocationService.deleteDepartmentLocation(1);
            List<DepartmentLocation> departmentLocations = departmentLocationService.getAll();
            assertEquals(1,departmentLocations.size());
        }

        @Test
        void testFindDepartmentLocationById_shouldReturnADepartmentLocation_WhenInputTheId()
        {
            assertEquals(departmentLocation1, departmentLocationService.findDepartmentLocationById(1).get());
        }

        @Test
        void testFindDepartmentLocationByDepartmentId_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(1, departmentLocationService.findDepartmentLocationByDepartmentDepartmentId(1).size());
        }

//        @Test
//        void testFindDepartment





    }





}