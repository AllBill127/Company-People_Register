package com.allbill127.psk_lab1.jpa.beans;

import com.allbill127.psk_lab1.jpa.dao.CompanyDAO;
import com.allbill127.psk_lab1.jpa.entities.Company;
import com.allbill127.psk_lab1.jpa.entities.Person;
import com.allbill127.psk_lab1.jpa.dao.PersonDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Long.parseLong;

@Model
public class PersonDetailsBean {
    private static final Logger _logger = Logger.getLogger(PersonDetailsBean.class.getName());
    @Inject
    private PersonDAO personDAO;
    @Inject
    private CompanyDAO companyDAO;

    @Getter
    private Person person;
    @Setter @Getter
    String companyIdString = new String();

    @PostConstruct
    public void init(){ loadPerson(); }

    private void loadPerson(){
        String personIdString = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("personId");
        long personId = parseLong(personIdString);
        this.person = personDAO.getById(personId);
    }

    @Transactional
    public void addOwner(){
        if(companyIdString == "" || companyIdString == null){
            _logger.log(Level.WARNING, "PersonDetailsBean addOwner() companyIdString = null/empty");
            return;
        }

        long companyId = parseLong(companyIdString);
        Company company = this.companyDAO.getById(companyId);
        person.getOwnedCompanies().add(company);
        this.personDAO.update(person);
    }
}
