package com.allbill127.psk_lab1.mybatis.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Person {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PERSON.ID
     *
     * @mbg.generated Thu Apr 20 20:34:32 EEST 2023
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PERSON.EMAIL
     *
     * @mbg.generated Thu Apr 20 20:34:32 EEST 2023
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PERSON.NAME
     *
     * @mbg.generated Thu Apr 20 20:34:32 EEST 2023
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PERSON.SOC_SEC_NR
     *
     * @mbg.generated Thu Apr 20 20:34:32 EEST 2023
     */
    private String socSecNr;
}