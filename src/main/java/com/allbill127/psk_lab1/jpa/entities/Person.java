package com.allbill127.psk_lab1.jpa.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "select p from Person as p")
})
@Table(name = "PERSON")
@EqualsAndHashCode(of={"name"})
public class Person {
    public Person() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SOC_SEC_NR", nullable = false)
    private String soc_sec_nr;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToMany(mappedBy = "person")
    private List<Employee> employed = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "OWNER",
            joinColumns = @JoinColumn(name="PERSON_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name ="COMPANY_ID", nullable = false))
    private List<Company> ownedCompanies = new ArrayList<>();

    public void addCompany(Company company){
        ownedCompanies.add(company);
        company.getOwners().add(this);
    }
}
