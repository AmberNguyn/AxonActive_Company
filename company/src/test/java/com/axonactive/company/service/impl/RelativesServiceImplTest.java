package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Gender;
import com.axonactive.company.entity.Relatives;
import com.axonactive.company.service.RelativesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RelativesServiceImplTest {
    @Autowired
    RelativesService relativesService;

    @Test
    void getAllRelatives_returnNoData_WhenJustCreateTable()
    {
        List<Relatives> relatives = relativesService.getAll();
        assertEquals(0, relatives.size());
    }

    @Test
    void saveRelatives_returnOneRelative_whenAddOneRelative()
    {
        Relatives relatives = Relatives.builder()
                .fullName("Nguyen Ngoc Ngan")
                .gender(Gender.MALE)
                .phoneNumber("1900 78 78 78")
                .relationship("Father")
                .build();
        relativesService.saveRelatives(relatives);
        List<Relatives> relativesList = relativesService.getAll();
        assertEquals(1, relativesList.size());
    }

    @Test
    void deleteRelative_shouldReturnZero_WhenDeleteOneDataFromAListOfOne()
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
        assertEquals(0, relativesList.size());
    }

    @Test
    void testFindRelativeById_ShouldReturnARelative_WhenInputARelativeId()
    {
        Relatives relatives = Relatives.builder()

                .fullName("Nguyen Ngoc Ngan")
                .gender(Gender.MALE)
                .phoneNumber("1900 78 78 78")
                .relationship("Father")
                .build();
        relativesService.saveRelatives(relatives);
//        assertEquals(1, relativesService.getAll().size());
        assertEquals(relatives, relativesService.findRelativeById(1).get());
    }

}