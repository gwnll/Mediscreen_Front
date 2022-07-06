package com.mediscreen.front.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
public class Patient {

    private int id;

    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    private Gender gender;

    private String address;

    private String phone;

}
