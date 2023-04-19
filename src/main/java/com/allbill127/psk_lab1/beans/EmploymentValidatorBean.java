package com.allbill127.psk_lab1.beans;

import com.allbill127.psk_lab1.entities.Company;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.RequestScoped;
import java.math.BigDecimal;

@RequestScoped
public class EmploymentValidatorBean {
    private static final double maxCapitalPercentForSalary = 0.7;
    public boolean canEmploy(@NotNull Company company, BigDecimal salary){
        BigDecimal currSalarySum = BigDecimal.valueOf(0);

        for (var employee: company.getEmployees()) {
            currSalarySum = currSalarySum.add(employee.getSalary());
        }

        if(currSalarySum.add(salary).doubleValue() > (company.getCapital().doubleValue() * maxCapitalPercentForSalary))
            return false;

        return true;
    }
}
