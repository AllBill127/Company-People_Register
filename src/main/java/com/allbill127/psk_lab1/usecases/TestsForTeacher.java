package com.allbill127.psk_lab1.usecases;

import com.allbill127.psk_lab1.entities.Test;
import com.allbill127.psk_lab1.persistence.TestsDAO;
import com.allbill127.psk_lab1.entities.Teacher;
import com.allbill127.psk_lab1.persistence.TeachersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class TestsForTeacher {
    @Inject
    private TeachersDAO teachersDAO;

    @Inject
    private TestsDAO testsDAO;

    @Getter @Setter
    private Teacher teacher;

    @Getter @Setter
    private Test testToCreate = new Test();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teacherId = Integer.parseInt(requestParameters.get("teacherId"));
        this.teacher = teachersDAO.findOne(teacherId);
    }

    @Transactional
    public void createTest(){
        testToCreate.setTeacher(this.teacher);
        testsDAO.persist(testToCreate);
    }
}
