package com.allbill127.psk_lab1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select t from Student as t")
})
@Table(name = "STUDENT")
@EqualsAndHashCode(of={"name"})
public class Student {
    public Student() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "STUDENT_TEST",
            joinColumns = @JoinColumn(name="STUDENT_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name ="TEST_ID", nullable = false))
    private List<Test> tests = new ArrayList<>();

    public void addTest(Test test){
        tests.add(test);
        test.getStudents().add(this);
    }
}
