package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeSearchService {

    private static Map<Long, Employee> EmployeeStaticData = null;

    static {
        Stream<String> employeeStream = Stream.of(
                "1,Saeedi-Mahsa,Java,Developer",
                "2,Taghizade-Zahra,Java,Developer",
                "3,Dadashli-Mahsa,Sport,Tennis trainer");

        EmployeeStaticData = employeeStream.map(employeeStr -> {
            String[] info = employeeStr.split(",");
            return createEmployee(new Long(info[0]), info[1], info[2], info[3]);
        }).collect(Collectors.toMap(Employee::getEmployeeId, emp -> emp));
    }

    private static Employee createEmployee(Long id, String name, String practiceArea, String designation) {
        Employee emp = new Employee();

        emp.setEmployeeId(id);
        emp.setName(name);
        emp.setPracticeArea(practiceArea);
        emp.setDesignation(designation);
        emp.setCompanyInfo("Behsazan");

        return emp;
    }

    public Employee findById(Long id) {
        return EmployeeStaticData.get(id);
    }

    public Collection<Employee> findAll() {
        return EmployeeStaticData.values();
    }
}