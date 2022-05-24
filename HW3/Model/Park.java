package com.demo.HW3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Park {

    @NotNull(message = "ID Cannot be empty")
    @Size(min = 2,message = "ID Length more than 2 char")
    private String ID;

    @NotNull(message = "rideName Cannot be empty")
    @Size(min = 4,message = "rideName Length more than 4")
    private String rideName;

    @NotNull(message = "rideType Cannot be empty")
    @Pattern(regexp = "rollercoaster | thriller | water")
    private String rideType;

    @NotNull(message = "tickets Cannot be empty")
    @Positive(message = "tickets must be a number ")
    private int tickets;

    @NotNull(message = "price Cannot be empty")
    @Positive(message = "price must be a number ")
    private int price;

}

