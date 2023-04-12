package com.allbill127.psk_lab1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(of={"name"})
@Table(name = "COMPANY")
@NamedQueries({
        @NamedQuery(name = "Company.findAll", query = "select c from Company as c")
})
public class Company {
    public Company(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CAPITAL", nullable = false)
    private BigDecimal capital;

    @ManyToMany(mappedBy = "ownedCompanies", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Person> owners = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
}
