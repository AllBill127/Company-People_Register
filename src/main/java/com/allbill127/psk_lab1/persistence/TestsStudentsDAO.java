package com.allbill127.psk_lab1.persistence;

import com.allbill127.psk_lab1.entities.Student;
import com.allbill127.psk_lab1.entities.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TestsStudentsDAO {
    @Inject
    private EntityManager em;

    public void createTestStudent(Integer studentId, Integer testId){
        Student student = em.find(Student.class, studentId);
        Test test = em.find(Test.class, testId);

        // Create a new row in the book_author join table
        Query query = em.createNativeQuery(
                "INSERT INTO TEST_STUDENT (TEST_ID, STUDENT_ID) VALUES (:testId, :studentId)"
        );
        query.setParameter("studentId", student.getId());
        query.setParameter("testId", test.getId());
        query.executeUpdate();
    }


}
