package com.Adinz.Proposal.PalyLoad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    @Email(message = "Username needs to be an email")
    @NotBlank(message= "email is required")
    private String email;
    @NotBlank(message= "password is required")
    private String password;
}
