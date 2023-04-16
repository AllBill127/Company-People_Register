package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.JPA_DAO.CompanyDAO;
import com.allbill127.psk_lab1.JPA_DAO.EmployeeDAO;
import com.allbill127.psk_lab1.JPA_DAO.PersonDAO;
import com.allbill127.psk_lab1.entities.Company;
import com.allbill127.psk_lab1.entities.Employee;
import com.allbill127.psk_lab1.entities.Person;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Long.parseLong;

@Model
public class CompanyEmployeeBean {
    private static final Logger _logger = Logger.getLogger(CompanyEmployeeBean.class.getName());
    @Inject
    private CompanyDAO companyDAO;
    @Inject
    private PersonDAO personDAO;
    @Inject
    private EmployeeDAO employeeDAO;

    @Getter @Setter
    private String personIdString = new String();
    @Getter @Setter
    private String companyIdString = new String();
    @Getter @Setter
    private Employee employee = new Employee();

    @Transactional
    public String createEmployee() {
        Object[] logParams = {this.getClass().getName(),
                companyIdString,
                personIdString};
        _logger.log(Level.WARNING, "CompanyEmployeeBean companyIdString={1} personIdString={2}",
                logParams);

        if(companyIdString == "" || companyIdString == null){
            _logger.log(Level.WARNING, "CompanyEmployeeBean companyIdString = null/empty");
            return "companyDetails.xhtml";
        }
        if(personIdString == "" || personIdString == null){
            _logger.log(Level.WARNING, "CompanyEmployeeBean personIdString = null/empty");
            return "companyDetails.xhtml";
        }

        long companyId = parseLong(companyIdString);
        long personId = parseLong(personIdString);
        Company company = this.companyDAO.getById(companyId);
        Person person = this.personDAO.getById(personId);

        this.employee.setCompany(company);
        this.employee.setPerson(person);
        this.employeeDAO.create(employee);

        //TODO:
        // force reload page. currently it inserts data to DB and selects the data
        // for the page but the employee list does not update
        return "companyDetails.xhtml?face-redirect=true";
    }
}
