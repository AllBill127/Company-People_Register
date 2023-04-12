package com.allbill127.psk_lab1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Teacher.findAll", query = "select t from Teacher as t")
})
@Table(name = "TEACHER")
@Getter @Setter
@EqualsAndHashCode(of={"name"})
public class Teacher {

    public Teacher(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "teacher")
//    @OneToMany
    private List<Test> tests = new ArrayList<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Team team = (Team) o;
//        return Objects.equals(name, team.name);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(name);
//    }
}
