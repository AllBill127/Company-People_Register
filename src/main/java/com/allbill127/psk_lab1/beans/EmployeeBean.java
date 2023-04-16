package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.entities.Employee;
import com.allbill127.psk_lab1.JPA_DAO.EmployeeDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class EmployeeBean {

    @Inject
    private EmployeeDAO employeeDAO;

    @Getter @Setter
    private Employee employeeToCreate = new Employee();

    @Getter
    private List<Employee> allEmployees;

    @PostConstruct
    public void init(){ loadAllEmployees(); }

    @Transactional
    public void createEmployee() { this.employeeDAO.create(employeeToCreate); }

    private void loadAllEmployees(){
        this.allEmployees = employeeDAO.getAll();
    }
}
