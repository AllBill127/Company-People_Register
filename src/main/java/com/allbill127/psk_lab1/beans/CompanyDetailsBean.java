package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.JPA_DAO.CompanyDAO;
import com.allbill127.psk_lab1.JPA_DAO.EmployeeDAO;
import com.allbill127.psk_lab1.JPA_DAO.OwnerDAO;
import com.allbill127.psk_lab1.JPA_DAO.PersonDAO;
import com.allbill127.psk_lab1.entities.Company;
import com.allbill127.psk_lab1.entities.Employee;
import com.allbill127.psk_lab1.entities.Person;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Long.parseLong;

@Model
public class CompanyDetailsBean {
    private static final Logger _logger = Logger.getLogger(CompanyDetailsBean.class.getName());
    @Inject
    private EmploymentValidatorBean employmentValidator;
    @Inject
    private CompanyDAO companyDAO;
    @Inject
    private PersonDAO personDAO;
    @Inject
    private EmployeeDAO employeeDAO;
    @Inject
    private OwnerDAO ownerDAO;

    @Getter
    private Company company;
    @Getter @Setter
    private Employee employee = new Employee();
    @Getter @Setter
    private String personIdString;

    @PostConstruct
    public void init(){ loadCompany(); }

    private void loadCompany(){
        String companyIdString = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("companyId");
        long companyId = parseLong(companyIdString);
        this.company = companyDAO.getById(companyId);
    }

    @Transactional
    public String addEmployee() throws IOException {
        Object[] logParams = {this.getClass().getName(),
                company.getId(),
                personIdString};
        _logger.log(Level.WARNING,
                "{0} addEmployee() companyIdString={1} personIdString={2}",
                logParams);

        if(personIdString == "" || personIdString == null){
            _logger.log(Level.WARNING, "{0} addEmployee() personIdString = null/empty", this.getClass().getName());
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect("companyDetails.xhtml?companyId="+company.getId()+"&error=missing-personId");
            return null;
        }

        if(!employmentValidator.canEmploy(this.company, this.employee.getSalary())){
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect("companyDetails.xhtml?companyId="+company.getId()+"&error=not-enough-capital");
            return null;
        }

        long personId = parseLong(personIdString);
        Person person = this.personDAO.getById(personId);

        this.employee.setCompany(company);
        this.employee.setPerson(person);

        // update currently loaded company
        this.company.getEmployees().add(employee);
        this.employeeDAO.create(employee);

        personIdString = null;

        return "companyDetails.xhtml?face-redirect=true";
    }

    @Transactional
    public void addOwner() {
        Object[] logParams = {this.getClass().getName(),
                company.getId(),
                personIdString};
        _logger.log(Level.WARNING,
                "{0} addOwner() companyIdString={1} personIdString={2}",
                logParams);

        if(personIdString == "" || personIdString == null){
            _logger.log(Level.WARNING, "{0} addOwner() personIdString = null/empty", this.getClass().getName());
            return;
        }

        long personId = parseLong(personIdString);
        Person person = this.personDAO.getById(personId);

        this.company.getOwners().add(person);
        this.ownerDAO.createOwner(personId, company.getId());
    }
}
