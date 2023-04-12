package com.allbill127.psk_lab1.usecases;

import com.allbill127.psk_lab1.entities.Teacher;
import com.allbill127.psk_lab1.persistence.TeachersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Teachers {

    @Inject
    private TeachersDAO teachersDAO;

    @Getter @Setter
    private Teacher teacherToCreate = new Teacher();

    @Getter
    private List<Teacher> allTeachers;

    @PostConstruct
    public void init(){ loadAllTeachers(); }

    @Transactional
    public void createTeacher() { this.teachersDAO.persist(teacherToCreate); }

    private void loadAllTeachers(){
        this.allTeachers = teachersDAO.loadAll();
    }
}
