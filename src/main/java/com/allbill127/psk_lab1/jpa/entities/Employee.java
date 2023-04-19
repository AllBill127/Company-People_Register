package com.allbill127.psk_lab1.jpa.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private long id;

    @Column (name = "SALARY", nullable = false)
    private BigDecimal salary;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Company company;
}
