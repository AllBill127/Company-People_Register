package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.entities.Person;
import com.allbill127.psk_lab1.JPA_DAO.PersonDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Long.parseLong;

@Model
public class PersonDetailsBean {
    @Inject
    private PersonDAO personDAO;

    @Getter @Setter
    private Person person;

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
}
