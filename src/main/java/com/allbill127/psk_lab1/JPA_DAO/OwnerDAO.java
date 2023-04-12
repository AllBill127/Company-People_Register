package com.allbill127.psk_lab1.JPA_DAO;

import com.allbill127.psk_lab1.entities.Company;
import com.allbill127.psk_lab1.entities.Person;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
