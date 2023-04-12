package com.allbill127.psk_lab1.models;

import com.allbill127.psk_lab1.entities.Person;
import com.allbill127.psk_lab1.JPA_DAO.PersonDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PersonModel {
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
