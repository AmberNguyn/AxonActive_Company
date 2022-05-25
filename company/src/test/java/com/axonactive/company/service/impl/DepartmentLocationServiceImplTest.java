package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Department;
import com.axonactive.company.entity.DepartmentLocation;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.service.DepartmentLocationService;
import com.axonactive.company.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    void getAllDepartmentLocation_returnNoData_WhenJustCreateTable() {
        List<DepartmentLocation> departmentLocations = departmentLocationService.getAll();
        assertEquals(0, departmentLocations.size());
    }

    //After save
    @Nested
    class casesTestedAfterSave {
        @Autowired
        DepartmentLocationService departmentLocationService;

        @Autowired
        DepartmentService departmentService;

        DepartmentLocation departmentLocation1;
        DepartmentLocation departmentLocation2;

        @BeforeEach
        void setUp() {
            Department department1 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2000, 01, 01))
                    .build();
            departmentService.saveDepartment(department1);

            departmentLocation1 = DepartmentLocation.builder()
                    .location("HongKong")
                    .department(department1)
                    .build();
            departmentLocationService.saveDepartmentLocation(departmentLocation1);

            Department department2 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2001, 02, 02))
                    .build();

            departmentService.saveDepartment(department2);

            departmentLocation2 = DepartmentLocation.builder()
                    .location("Vietnam")
                    .department(department2)
                    .build();

            departmentLocationService.saveDepartmentLocation(departmentLocation2);
        }

        @Test
        void saveDepartmentLocation_returnTwoData_WhenAddOne() {
            List<DepartmentLocation> departments = departmentLocationService.getAll();
            assertEquals(2, departments.size());
        }

        @Test
        void deleteDepartmentLocation_ShouldReturnOneData_WhenDeleteOneDataFromAListOfTwo() {
            departmentLocationService.deleteDepartmentLocation(1);
            List<DepartmentLocation> departmentLocations = departmentLocationService.getAll();
            assertEquals(1, departmentLocations.size());
        }

        @Test
        void testFindDepartmentLocationById_shouldReturnADepartmentLocation_WhenInputTheId() {
            assertEquals(departmentLocation1, departmentLocationService.findDepartmentLocationById(1).get());
        }

        @Test
        void testFindDepartmentLocationByDepartmentDepartmentId_shouldReturnOneData_WhenFound() {
            assertEquals(1, departmentLocationService.findDepartmentLocationByDepartmentDepartmentId(1).size());
        }

        @Test
        void testFindDepartmentLocationByDepartmentDepartmentId_shouldReturnNoData_WhenNotFound() {
            assertEquals(0, departmentLocationService.findDepartmentLocationByDepartmentDepartmentId(3).size());
        }

        @Test
        void testFindDepartmentLocationByLocation_shouldReturnAListOfOneDepartmentLocation_WhenFound() {
            assertEquals(1, departmentLocationService.findDepartmentLocationByLocation("HongKong").size());
        }

        @Test
        void testFindDepartmentLocationByLocation_shouldReturnAListOfOneDepartmentLocation_WhenNotFound() {
            assertEquals(0, departmentLocationService.findDepartmentLocationByLocation("America").size());
        }

        @Test
        void testFindDepartmentLocationByLocationStartingWith_shouldReturnAListOfDepartmentLocation_whenFound() {
            assertEquals(1, departmentLocationService.findDepartmentLocationByLocationStartingWith("H").size());
        }

        @Test
        void testFindDepartmentLocationByLocationStartingWith_shouldReturnNoData_whenNotFound()
        {
            assertEquals(0, departmentLocationService.findDepartmentLocationByLocationStartingWith("T").size());
        }

        @Test
        void testFindDepartmentLocationByLocationNot_shouldReturnAListOfTwoDepartmentLocation_whenFound()
        {
            assertEquals(2, departmentLocationService.findDepartmentLocationByLocationNot("T").size());
        }

        @Test
        //"H" in any position
        void testFindDepartmentLocationByLocationNot_shouldReturnTwoDepartmentLocation_whenFound()
        {
            assertEquals(2, departmentLocationService.findDepartmentLocationByLocationNot("H").size());
        }


        @Test
        void testFindDepartmentLocationByLocationOrderByDepartmentDepartmentId_shouldReturnAListOfTwoDepartmentOrderByDepartmentId_WhenFound()
        {
            Department department3 = Department.builder()
                    .departmentName("Marketing")
                    .startDate(LocalDate.of(1999, 01, 01))
                    .build();
            departmentService.saveDepartment(department3);

            DepartmentLocation departmentLocation3;
            departmentLocation3 = DepartmentLocation.builder()
                    .location("HongKong")
                    .department(department3)
                    .build();
            departmentLocationService.saveDepartmentLocation(departmentLocation3);

            assertEquals(2, departmentLocationService.findDepartmentLocationByLocationOrderByDepartmentDepartmentIdDesc("HongKong").size());
            assertTrue(departmentLocationService.findDepartmentLocationByLocationOrderByDepartmentDepartmentIdDesc("HongKong").get(0).getDepartment().getDepartmentId() -
                            departmentLocationService.findDepartmentLocationByLocationOrderByDepartmentDepartmentIdDesc("HongKong").get(1).getDepartment().getDepartmentId() >=1
                    );
        }





    }


}