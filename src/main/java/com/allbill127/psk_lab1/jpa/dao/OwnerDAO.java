package com.allbill127.psk_lab1.jpa.dao;

import com.allbill127.psk_lab1.jpa.entities.Company;
import com.allbill127.psk_lab1.jpa.entities.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@ApplicationScoped
public class OwnerDAO {
    @Inject
    private EntityManager em;

    public void createOwner(long personId, long companyId){
        Person person = em.find(Person.class, personId);
        Company company = em.find(Company.class, companyId);

        // Create a new row in owner joint table
        Query query = em.createNativeQuery(
                "INSERT INTO OWNER (PERSON_ID, COMPANY_ID) VALUES (:personId, :companyId)"
        );
        query.setParameter("personId", person.getId());
        query.setParameter("companyId", company.getId());
        query.executeUpdate();
    }


}
