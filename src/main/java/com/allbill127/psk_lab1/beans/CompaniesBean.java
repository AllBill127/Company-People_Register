package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.JPA_DAO.CompanyDAO;
import com.allbill127.psk_lab1.JPA_DAO.OwnerDAO;
import com.allbill127.psk_lab1.entities.Company;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Long.parseLong;

@Model
public class CompaniesBean {
    @Inject
    private CompanyDAO companyDAO;
    @Inject
    private OwnerDAO ownerDAO;

    @Getter @Setter
    private Company companyToCreate = new Company();
    @Getter @Setter
    private String ownerId = new String();

    @Getter
    private List<Company> allCompanies;

    @PostConstruct
    public void init(){ loadAllCompanies(); }

    @Transactional
    public void createCompany() {
        this.companyDAO.create(companyToCreate);

        long personId = parseLong(ownerId);
        this.ownerDAO.createOwner(personId, companyToCreate.getId());
    }

    private void loadAllCompanies(){
        this.allCompanies = companyDAO.getAll();
    }
}
