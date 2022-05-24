package com.demo.HW3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employee {

    @NotNull(message = " ID Cannot be empty")
    @Size(min = 2 , message = "ID must be more than 2")
    private String ID;

    @NotNull(message = "name Cannot be empty")
    @Size(min = 4 , message = "ride id must be more than 4")
    private String name;

    @NotNull(message = "age type cannot be empty")
    @Min(value = 25,message = "must be more than 25 ")
    private Integer age;

    @AssertFalse(message = "onLeave must be false")
    private boolean onLeave;

    @NotNull(message = "employmentYear type cannot be empty")
    @Positive(message = "employmentYear has to be number ")
    private Integer employmentYear;

    @NotNull(message = "annualLeave Cannot be empty")
    @PositiveOrZero(message = "annualLeave has to be number")
    private Integer annualLeave;

}
