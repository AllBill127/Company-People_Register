package com.allbill127.psk_lab1.models;

import com.allbill127.psk_lab1.entities.Company;
import com.allbill127.psk_lab1.JPA_DAO.CompanyDAO;
import com.allbill127.psk_lab1.entities.Employee;
import com.allbill127.psk_lab1.JPA_DAO.EmployeeDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class CompanyEmployeeModel {
    @Inject
    private CompanyDAO companyDAO;

    @Inject
    private EmployeeDAO employeeDAO;

    @Getter @Setter
    private Company company;

    @Getter @Setter
    private Employee employeeToCreate = new Employee();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
        long companyId = Long.parseLong(requestParameters.get("companyId"));
        this.company = companyDAO.getById(companyId);
    }

    @Transactional
    public void createEmployee(){
        employeeToCreate.setCompany(this.company);
        employeeDAO.create(employeeToCreate);
    }
}
