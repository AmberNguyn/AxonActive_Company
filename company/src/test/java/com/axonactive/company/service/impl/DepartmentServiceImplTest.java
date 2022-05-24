package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Department;
import com.axonactive.company.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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

    private Department department1;
    private Department department2;

    //before Save
    @Test
    void testGetAllDepartment_returnNoData_whenJustCreateTable()
    {
        List<Department> departments = departmentService.getAll();
        assertEquals(0, departments.size());
    }

    //after Save
    @Nested
    class casesTestedAfterSave {
        @Autowired
        DepartmentService departmentService;

        private Department department1;
        private Department department2;

        @BeforeEach
        void setUp(){
            department1 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2000,9,9))
                    .build();

            departmentService.saveDepartment(department1);

            department2 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2001,10,10))
                    .build();

            departmentService.saveDepartment(department2);
        }


        @Test
        void testSaveDepartment_returnOneMoreData_WhenAddOne()
        {
            List<Department> departments = departmentService.getAll();
            assertEquals(2, departments.size());
        }

        @Test
        void testDeleteDepartment_shouldReturnOneData_WhenDeleteFromAListOfTwoData()
        {
            departmentService.deleteDepartment(1);
            List<Department> departments = departmentService.getAll();
            assertEquals(1, departments.size());
        }

        @Test
        void testFindDepartmentById_shouldReturnADepartment_whenInputAnId()
        {
            assertEquals(department1, departmentService.findDepartmentById(1).get());
        }

        @Test
        void testFindDepartmentByDepartmentNameIs_shouldReturnAListOfTwoDepartment_WhenFound()
        {
            assertEquals(2, departmentService.findDepartmentByDepartmentNameIs("Sales").size());
        }

        @Test
        void testFindDepartmentByDepartmentNameIs_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(0, departmentService.findDepartmentByDepartmentNameIs("sales").size());
        }

        @Test
        void testFindDepartmentByStartDateIsNotNull_shouldReturnAListOfTwoDepartment_WhenFound()
        {
            assertEquals(2, departmentService.findDepartmentByStartDateIsNotNull().size());
        }









    }
}