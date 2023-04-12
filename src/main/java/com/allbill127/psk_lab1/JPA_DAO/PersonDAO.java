package com.allbill127.psk_lab1.JPA_DAO;

import com.allbill127.psk_lab1.entities.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PersonDAO {
    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Person getById(long id) {
        return em.find(Person.class, id);
    }

    public List<Person> getAll() {
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    public void create(Person person){
        this.em.persist(person);
    }
}
