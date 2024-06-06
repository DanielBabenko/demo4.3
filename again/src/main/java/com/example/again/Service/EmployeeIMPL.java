package com.example.again.Service;

import com.example.again.DTO.EmployeeDTO;
import com.example.again.Entity.Employee;
import com.example.again.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeIMPL implements EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public String addEmployee(EmployeeDTO employeeDTO)
    {

        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getAddress(),
                employeeDTO.getMobile()
        );
        employeeRepo.save(employee);
        return employee.getEmployeename();
    }
}
