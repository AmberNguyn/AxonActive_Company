package com.axonactive.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee {

    @Size(min = 1, max = 9)
    @Column(unique = true)
    private String employeeIdFake;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;


    private LocalDate dateOfBirth;


    @Size(min = 1, max = 20)
    private String firstName;

    @Enumerated(EnumType.STRING)

    private Gender gender;


    @Size(min = 1, max = 20)
    private String lastName;


    @Size(min = 1, max = 20)
    private String middleName;


    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


}
