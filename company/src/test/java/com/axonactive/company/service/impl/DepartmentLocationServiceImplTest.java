package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Department;
import com.axonactive.company.entity.DepartmentLocation;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.service.DepartmentLocationService;
import com.axonactive.company.service.DepartmentService;
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
class DepartmentLocationServiceImplTest {
    @Autowired
    DepartmentLocationService departmentLocationService;

    @Test
    void getAllDepartmentLocation_returnNoData_WhenJustCreateTable(){
        List<DepartmentLocation> departmentLocations = departmentLocationService.getAll();
        assertEquals(0, departmentLocations.size());

    }

    @Test
    void saveDepartmentLocation_returnOneData_WhenAddOne()
    {
        DepartmentLocation departmentLocation = DepartmentLocation.builder()
                .location("HongKong")
                .build();
        departmentLocationService.saveDepartmentLocation(departmentLocation);
        List<DepartmentLocation> departments = departmentLocationService.getAll();
        assertEquals(1, departments.size());
    }

    @Test
    void deleteDepartmentLocation_ShouldReturnNoDate_WhenDeleteOneDataFromAListOfOne()
    {
        DepartmentLocation departmentLocation = DepartmentLocation.builder()
                .location("HongKong")
                .build();
        departmentLocationService.saveDepartmentLocation(departmentLocation);
        departmentLocationService.deleteDepartmentLocation(1);
        List<DepartmentLocation> departmentLocations = departmentLocationService.getAll();
        assertEquals(0,departmentLocations.size());
    }

    @Test
    void testFindDepartmentLocationById_shouldReturnADepartmentLocation_WhenInputTheId()
    {
        DepartmentLocation departmentLocation = DepartmentLocation.builder()
                .location("HongKong")
                .build();
        departmentLocationService.saveDepartmentLocation(departmentLocation);
        assertEquals(departmentLocation, departmentLocationService.findDepartmentLocationById(1).get());
    }


}