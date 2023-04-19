package com.allbill127.psk_lab1.jpa.beans;

import com.allbill127.psk_lab1.jpa.entities.Person;
import com.allbill127.psk_lab1.jpa.dao.PersonDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PeopleBean {
    @Inject
    private PersonDAO personDAO;

    @Getter @Setter
    private Person personToCreate = new Person();

    @Getter
    private List<Person> allPeople;

    @PostConstruct
    public void init(){ loadAllPeople(); }

    @Transactional
    public void createPerson() {
        this.personDAO.create(personToCreate);
    }

    private void loadAllPeople(){
        this.allPeople = personDAO.getAll();
    }
}
