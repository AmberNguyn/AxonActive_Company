package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Project;
import com.axonactive.company.service.ProjectService;
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
class ProjectServiceImplTest {
    @Autowired
    ProjectService projectService;

    @Test
    void getAllProject_returnNoData_WhenJustCreateTable()
    {
        List<Project> projects = projectService.getAll();
        assertEquals(0, projects.size());
    }

    @Test
    void saveProject_shouldReturnOne_whenAddOneProject()
    {
        Project project = Project.builder()
                .area("Area A")
                .projectName("Techie Plan")
                .build();
        projectService.saveProject(project);
        List<Project> projects = projectService.getAll();
        assertEquals(1, projects.size());
    }

    @Test
    void deleteProject_shouldReturnZero_whenDeleteOneProjectFromAListOfOne()
    {
        Project project = Project.builder()
                .area("Area A")
                .projectName("Techie Plan")
                .build();
        projectService.saveProject(project);
        projectService.deleteProject(1);
        List<Project> projects = projectService.getAll();
        assertEquals(0, projects.size());
    }

    @Test
    void testFindProjectById_shouldReturnAProject_whenInputAProjectId()
    {
        Project project = Project.builder()
                .area("Area A")
                .projectName("Techie Plan")
                .build();
        projectService.saveProject(project);
        assertEquals(project, projectService.findProjectById(1).get());
    }

}