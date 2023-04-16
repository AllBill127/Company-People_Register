package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.JPA_DAO.CompanyDAO;
import com.allbill127.psk_lab1.entities.Company;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Long.parseLong;

@Model
public class CompanyDetailsBean {
    @Inject
    private CompanyDAO companyDAO;

    @Getter @Setter
    private Company company;

    @PostConstruct
    public void init(){ loadCompany(); }

    private void loadCompany(){
        String companyIdString = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("companyId");
        long companyId = parseLong(companyIdString);
        this.company = companyDAO.getById(companyId);
    }
}
