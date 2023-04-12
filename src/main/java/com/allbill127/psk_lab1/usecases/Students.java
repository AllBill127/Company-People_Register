package com.allbill127.psk_lab1.usecases;

import com.allbill127.psk_lab1.entities.Student;
import com.allbill127.psk_lab1.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Students {
    @Inject
    private StudentsDAO studentsDAO;

    @Getter
    @Setter
    private Student studentToCreate = new Student();

    @Getter
    private List<Student> allStudents;

    @PostConstruct
    public void init(){ loadAllStudents(); }

    @Transactional
    public void createStudent() {
        this.studentsDAO.persist(studentToCreate);
    }

    private void loadAllStudents(){
        this.allStudents = studentsDAO.loadAll();
    }
}
