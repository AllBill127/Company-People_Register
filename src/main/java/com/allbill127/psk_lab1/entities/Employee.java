package com.allbill127.psk_lab1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee as e")
})
@Table(name = "EMPLOYEE")
@Getter @Setter
@EqualsAndHashCode(of={"name"})
public class Employee {

    public Employee(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "SALARY", nullable = false)
    private BigDecimal salary;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Company companies;
}
