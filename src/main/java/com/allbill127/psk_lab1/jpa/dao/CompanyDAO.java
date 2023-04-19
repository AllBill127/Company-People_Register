package com.allbill127.psk_lab1.jpa.dao;

import com.allbill127.psk_lab1.jpa.entities.Company;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CompanyDAO {
    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Company getById(long id) {
        return em.find(Company.class, id);
    }

    public List<Company> getAll() {
        return em.createNamedQuery("Company.findAll", Company.class).getResultList();
    }

    public void create(Company company){
        this.em.persist(company);
    }

    public void update(Company company){
        this.em.merge(company);
    }
}
