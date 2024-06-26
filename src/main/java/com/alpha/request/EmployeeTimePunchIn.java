package com.alpha.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeTimePunchIn {
    @NotNull
    private String employeeNo;
   
}
