package com.allbill127.psk_lab1.persistence;

import com.allbill127.psk_lab1.entities.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TeachersDAO {

    @Inject
    private EntityManager em;

    public List<Teacher> loadAll() {
        return em.createNamedQuery("Teacher.findAll", Teacher.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Teacher teacher){
        this.em.persist(teacher);
    }

    public Teacher findOne(Integer id) {
        return em.find(Teacher.class, id);
    }
}
