package com.allbill127.psk_lab1.usecases;

import com.allbill127.psk_lab1.entities.Test;
import com.allbill127.psk_lab1.persistence.TestsDAO;
import com.allbill127.psk_lab1.entities.Student;
import com.allbill127.psk_lab1.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class TestsToStudents {
    @Inject
    private TestsDAO testsDAO;
    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private Test test;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer testId = Integer.parseInt(requestParameters.get("testId"));
        this.test = testsDAO.findOne(testId);
    }

    //TODO
    //Currently creates a new user each time. no check if the name is used
    //Perhaps a better solution is to have a separate student creation page
    //And show student selection inside test
    @Transactional
    public void createStudent(){
        studentToCreate.addTest(this.test);
        studentsDAO.persist(studentToCreate);
    }

}
