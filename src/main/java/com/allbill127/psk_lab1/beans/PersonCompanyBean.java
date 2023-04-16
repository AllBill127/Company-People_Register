package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.entities.Company;
import com.allbill127.psk_lab1.JPA_DAO.CompanyDAO;
import com.allbill127.psk_lab1.entities.Person;
import com.allbill127.psk_lab1.JPA_DAO.PersonDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class PersonCompanyBean {
    @Inject
    private PersonDAO personDAO;

    @Inject
    private CompanyDAO companyDAO;

    @Getter @Setter
    private Company company;

    @Getter @Setter
    private Person personToCreate = new Person();

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

    //TODO
    //Currently creates a new person each time. no check if it already exists (has same name and other data)
    //Perhaps a better solution is to have a separate student creation page
    //And show student selection inside company
    @Transactional
    public void createPerson(){
        personToCreate.addCompany(this.company);
        personDAO.create(personToCreate);
    }

}
