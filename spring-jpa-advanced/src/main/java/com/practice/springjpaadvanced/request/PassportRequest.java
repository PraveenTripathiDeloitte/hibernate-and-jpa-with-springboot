package com.practice.springjpaadvanced.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PassportRequest {

    private Long id;

    @NotNull
    @Pattern(regexp = "[a-z0-9]{9}", message = "Enter valid Passport Number")
    private String passportNumber;
}
