package com.allbill127.psk_lab1.mybatis.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Owner {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.OWNER.PERSON_ID
     *
     * @mbg.generated Thu Apr 20 20:34:32 EEST 2023
     */
    private Long personId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.OWNER.COMPANY_ID
     *
     * @mbg.generated Thu Apr 20 20:34:32 EEST 2023
     */
    private Long companyId;
}