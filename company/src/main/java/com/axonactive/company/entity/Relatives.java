package com.axonactive.company.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Relatives {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer relativeId;

    @NotNull
    @Size(min = 0, max = 255)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    @Size(min = 0, max = 255)
    private String phoneNumber;

    @NotNull
    @Size(min = 0, max = 255)
    private String relationship;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
