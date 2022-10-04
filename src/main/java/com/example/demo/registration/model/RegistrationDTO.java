package com.example.demo.registration.model;

import com.example.demo.registration.validator.FieldMatch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "password", second = "repeatPassword", message = "The passwords do not match")
public class RegistrationDTO {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;

}
