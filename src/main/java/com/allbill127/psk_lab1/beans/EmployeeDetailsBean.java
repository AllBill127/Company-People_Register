package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.JPA_DAO.EmployeeDAO;
import com.allbill127.psk_lab1.entities.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import static java.lang.Long.parseLong;

@Model
public class EmployeeDetailsBean {
    @Inject
    private EmployeeDAO employeeDAO;

    @Getter @Setter
    private Employee employee;

    @PostConstruct
    public void init(){ loadEmployee(); }

    private void loadEmployee(){
        String employeeIdString = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("employeeId");
        long employeeId = parseLong(employeeIdString);
        this.employee = employeeDAO.getById(employeeId);
    }
}
