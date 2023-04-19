package com.allbill127.psk_lab1.jpa.dao;

import com.allbill127.psk_lab1.jpa.entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class EmployeeDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Employee getById(long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> getAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public void create(Employee employee){
        this.em.persist(employee);
    }
}
