package service;

import interfaces.EmployeeDTO;

public class EmployeeService {

    public void save(EmployeeDTO employeeDTO) {
        System.out.println("Employee save " + employeeDTO);
    }
}
