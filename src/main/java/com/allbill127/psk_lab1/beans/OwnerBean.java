package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.JPA_DAO.OwnerDAO;
import com.allbill127.psk_lab1.entities.Company;
import com.allbill127.psk_lab1.entities.Person;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Long.parseLong;

@Model
public class OwnerBean {
    private static final Logger _logger = Logger.getLogger(OwnerBean.class.getName());
    @Inject
    OwnerDAO ownerDAO;

    @Setter @Getter
    String personIdString = new String();
    @Setter @Getter
    String companyIdString = new String();

    @Transactional
    public String addOwner(){
        if(personIdString == "" || personIdString == null){
            _logger.log(Level.WARNING, "OwnerBean personIdString = null/empty");
            return "personDetails.xhtml";
        }
        if(companyIdString == "" || companyIdString == null){
            _logger.log(Level.WARNING, "OwnerBean companyIdString = null/empty");
            return "personDetails.xhtml";
        }

        long personId = parseLong(personIdString);
        long companyId = parseLong(companyIdString);

        this.ownerDAO.createOwner(personId, companyId);

        //TODO:
        // force reload page. currently it inserts data to DB and selects the data
        // for the page but the employee list does not update
        return "personDetails.xhtml?face-redirect=true";
    }
}
