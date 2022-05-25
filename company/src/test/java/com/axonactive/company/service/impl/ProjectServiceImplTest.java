package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Department;
import com.axonactive.company.entity.Project;
import com.axonactive.company.service.DepartmentService;
import com.axonactive.company.service.ProjectService;
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
class ProjectServiceImplTest {
    @Autowired
    ProjectService projectService;
    @Autowired
    DepartmentService departmentService;

    @Test
    void getAllProject_returnNoData_WhenJustCreateTable()
    {
        List<Project> projects = projectService.getAll();
        assertEquals(0, projects.size());
    }

    @Nested
    class afterSave{
        @Autowired
        ProjectService projectService;
        @Autowired
        DepartmentService departmentService;

        private Project project1;
        private Project project2;
        private Project project3;

        @BeforeEach
        void setUp()
        {
            Department department1 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2000,10,10))
                    .build();
            departmentService.saveDepartment(department1);

            project1 = Project.builder()
                    .area("HongKong")
                    .projectName("Techie Plan")
                    .department(department1)
                    .build();
            projectService.saveProject(project1);

            Department department2 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2000,11,11))
                    .build();
            departmentService.saveDepartment(department2);

            project2 = Project.builder()
                    .area("HongKong")
                    .projectName("Techie Plan")
                    .department(department2)
                    .build();
            projectService.saveProject(project2);

            Department department3 = Department.builder()
                    .departmentName("Sales")
                    .startDate(LocalDate.of(2001,9,9))
                    .build();
            departmentService.saveDepartment(department3);

            project3 = Project.builder()
                    .area("Vietnam")
                    .projectName("Flower Plan")
                    .department(department3)
                    .build();
            projectService.saveProject(project3);

        }

        @Test
        void saveProject_shouldReturnOne_whenAddOneProject()
        {
            Project project4 = Project.builder()
                    .area("Area A")
                    .projectName("Techie Plan")
                    .build();
            projectService.saveProject(project4);
            List<Project> projects = projectService.getAll();
            assertEquals(4, projects.size());
        }

        @Test
        void deleteProject_shouldReturnZero_whenDeleteOneProjectFromAListOfOne()
        {
            Project project = Project.builder()
                    .area("Area A")
                    .projectName("Techie Plan")
                    .build();
            projectService.saveProject(project);
            projectService.deleteProject(4);
            List<Project> projects = projectService.getAll();
            assertEquals(3, projects.size());
        }

        @Test
        void testFindProjectById_shouldReturnAProject_whenInputAProjectId()
        {
            assertEquals(project1, projectService.findProjectById(1).get());
        }

        @Test
        void testFindProjectByAreaOrderByArea_shouldReturnAListOfProjectOrderByArea_whenFound()
        {
            assertEquals(2, projectService.findProjectByAreaOrderByProjectName("HongKong").size());
            assertTrue(projectService.findProjectByAreaOrderByProjectName("HongKong").get(0).getProjectName()
                    .compareTo(projectService.findProjectByAreaOrderByProjectName("HongKong").get(1).getProjectName()) >= 0);
        }

        @Test
        void testFindProjectByProjectName_shouldReturnAProject_WhenFound()
        {
            assertEquals(Optional.of(project3), projectService.findProjectByProjectName("Flower Plan"));
        }

        @Test
        void testFindProjectByProjectName_shouldReturnNoData_WhenNotFound()
        {
            assertEquals(Optional.empty(), projectService.findProjectByProjectName("ABC"));
        }

        @Test
        void testFindProjectByDepartmentDepartmentId_shouldReturnAListOfProject_whenFound()
        {
            assertEquals(1, projectService.findProjectByDepartmentDepartmentId(1).size());
        }

        @Test
        void testFindProjectByDepartmentDepartmentId_shouldReturnNoData_whenNotFound()
        {
            assertEquals(0, projectService.findProjectByDepartmentDepartmentId(4).size());
        }

        @Test
        void testFindProjectByDepartmentDepartmentName_shouldReturnAListOfDepartment_whenFound()
        {
            assertEquals(3, projectService.findProjectByDepartmentDepartmentName("Sales").size());
        }

        @Test
        void testFindProjectByDepartmentDepartmentName_shouldReturnNoData_whenNotFound()
        {
            assertEquals(0, projectService.findProjectByDepartmentDepartmentName("HR").size());
        }


    }




















}