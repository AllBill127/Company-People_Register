package com.allbill127.psk_lab1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(of={"name"})
@Table(name = "TEST")
@NamedQueries({
        @NamedQuery(name = "Test.findAll", query = "select t from Test as t join t.")
})
public class Test {
    public Test(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "tests", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> students = new ArrayList<>();


}
