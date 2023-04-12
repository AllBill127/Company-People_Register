package com.allbill127.psk_lab1.persistence;

import com.allbill127.psk_lab1.entities.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TestsDAO {
    @Inject
    private EntityManager em;

    public List<Test> loadAll() {
        return em.createNamedQuery("Test.findAll", Test.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Test test){
        this.em.persist(test);
    }

    public Test findOne(Integer id) {
        return em.find(Test.class, id);
    }
}
