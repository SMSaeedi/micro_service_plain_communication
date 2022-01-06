package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmployeeInfo {
    private Long employeeId;
    private String name;
    private String practiceArea;
    private String designation;
    private String companyInfo;
}