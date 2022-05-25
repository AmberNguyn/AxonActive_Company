package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Employee;
import com.axonactive.company.entity.Gender;
import com.axonactive.company.entity.Relatives;
import com.axonactive.company.service.EmployeeService;
import com.axonactive.company.service.RelativesService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RelativesServiceImplTest {
    @Autowired
    RelativesService relativesService;

    @Autowired
    EmployeeService employeeService;

    @Test
    void getAllRelatives_returnNoData_WhenJustCreateTable()
    {
        List<Relatives> relatives = relativesService.getAll();
        assertEquals(0, relatives.size());
    }



    @Nested
    class afterSave{

        @Autowired
        RelativesService relativesService;

        @Autowired
        EmployeeService employeeService;

        private Relatives relative1;
        private Relatives relative2;
        private Relatives relative3;

        @BeforeEach
        void setUp()
        {
            Employee employee1 = Employee.builder()
                    .firstName("Luffy")
                    .employeeIdFake("A01")
                    .salary(1000)
                    .build();
            employeeService.saveEmployee(employee1);
            relative1 = Relatives.builder()
                    .fullName("Tong Vu")
                    .gender(Gender.MALE)
                    .phoneNumber("1900 78 78 78")
                    .relationship("Brother")
                    .employee(employee1)
                    .build();
            relativesService.saveRelatives(relative1);

            Employee employee2 = Employee.builder()
                    .firstName("Nami")
                    .employeeIdFake("A02")
                    .salary(2000)
                    .build();
            employeeService.saveEmployee(employee2);
            relative2 = Relatives.builder()
                    .fullName("Anh Nguyen")
                    .gender(Gender.FEMALE)
                    .phoneNumber("1900 90 34 34")
                    .relationship("Sister")
                    .employee(employee2)
                    .build();
            relativesService.saveRelatives(relative2);

            Employee employee3 = Employee.builder()
                    .firstName("Hannah")
                    .employeeIdFake("A03")
                    .salary(3000)
                    .build();
            employeeService.saveEmployee(employee3);
            relative3 = Relatives.builder()
                    .fullName("Thinh Nguyen")
                    .gender(Gender.MALE)
                    .phoneNumber("1100 23 34 34")
                    .relationship("Brother")
                    .employee(employee3)
                    .build();
            relativesService.saveRelatives(relative3);

        }

        @Test
        void saveRelatives_returnFourRelatives_whenAddOneRelativeIntoAListOfThree()
        {
            Relatives relative4 = Relatives.builder()
                    .fullName("Dinh Nguyen")
                    .gender(Gender.MALE)
                    .phoneNumber("1301 23 34 34")
                    .relationship("Brother")
                    .build();
            relativesService.saveRelatives(relative4);
            List<Relatives> relativesList = relativesService.getAll();
            assertEquals(4, relativesList.size());
        }

        @Test
        void deleteRelative_shouldReturnAListOfThree_WhenDeleteOneDataFromAListOfFour()
        {
            Relatives relatives = Relatives.builder()
                    .fullName("Nguyen Ngoc Ngan")
                    .gender(Gender.MALE)
                    .phoneNumber("1900 78 78 78")
                    .relationship("Father")
                    .build();
            relativesService.saveRelatives(relatives);
            relativesService.deleteRelative(1);
            List<Relatives> relativesList = relativesService.getAll();
            assertEquals(3, relativesList.size());
        }

        @Test
        void testFindRelativeById_ShouldReturnARelative_WhenInputARelativeId()
        {
            assertEquals(relative1, relativesService.findRelativeById(1).get());
        }

        @Test
        void testFindRelativeByFullName_ShouldReturnAListOfEmployee_WhenFound()
        {
            assertEquals(1, relativesService.findRelativesByFullName("Anh Nguyen").size());
        }

        @Test
        void testFindRelativeByFullName_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(0, relativesService.findRelativesByFullName("Tu Nguyen").size());
        }

        @Test
        void testFindRelativeByGender_shouldReturnAlistOfMaleRelative_WhenFound()
        {
            assertEquals(2, relativesService.findRelativesByGender("MALE").size());
        }

        @Test
        void testFindRelativeByPhoneNumberStartingWith_shouldReturnAlistOfRelative_whenFound()
        {
            assertEquals(2, relativesService.findRelativesByPhoneNumberStartingWith("1900").size());
        }

        @Test
        void testFindRelativeByPhoneNumberStartingWith_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(0, relativesService.findRelativesByPhoneNumberStartingWith("1200").size());
        }

        @Test
        void testFindRelativeByRelationship_shouldReturnAListOfEmployee_whenFound()
        {
            assertEquals(1, relativesService.findRelativesByRelationship("Sister").size());
        }

        @Test
        void testFindRelativeByRelationship_shouldReturnNoData_whenNotFound()
        {
            assertEquals(0, relativesService.findRelativesByRelationship("Mother").size());
        }

        @Test
        void testFindRelativeByEmployeeEmployeeId_shouldReturnARelative_WhenFound()
        {
            assertEquals(Optional.of(relative3), relativesService.findRelativesByEmployeeEmployeeId(3));
        }

        @Test
        void testFindRelativeByEmployeeEmployeeId_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(Optional.empty(), relativesService.findRelativesByEmployeeEmployeeId(4));
        }

        @Test
        void testFindRelativeByEmployeeEmployeeFirstName_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(0, relativesService.findRelativesByEmployeeFirstName("Anh").size());
        }

        @Test
        void testFindRelativeByEmployeeFirstName_shouldReturnAListOfRelative_WhenFound()
        {
            assertEquals(1, relativesService.findRelativesByEmployeeFirstName("Hannah").size());
        }

        @Test
        void testFindRelativeByPhoneNumberContaining_shouldReturnAListOfTwoRelative_WhenFound()
        {
            assertEquals(2, relativesService.findRelativesByPhoneNumberContaining("1900").size());
        }

        @Test
        void testFindRelativeByPhoneNumberContaining_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(0, relativesService.findRelativesByPhoneNumberContaining("130").size());
        }

        @Test
        void testFindRelativeByEmployeeSalary_shouldReturnAListOfThreeEmployees_WhenFound()
        {
            assertEquals(3, relativesService.findRelativesByEmployeeSalaryLessThan(10000).size());
        }

        @Test
        void testFindRelativeByEmployeeSalary_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(0, relativesService.findRelativesByEmployeeSalaryLessThan(500).size());
        }

        @Test
        void testFindRelativeByFullNameStartingWithIgnoreCase_shouldReturnAListOfRelative_whenFound()
        {
            assertEquals(1, relativesService.findRelativesByFullNameStartingWithIgnoreCase("anh nguyen").size());
        }










    }













}