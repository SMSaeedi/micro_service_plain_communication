package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Employee {
    private Long employeeId;
    private String name;
    private String practiceArea;
    private String designation;
    private String companyInfo;
}