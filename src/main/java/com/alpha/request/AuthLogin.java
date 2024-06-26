package com.alpha.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AuthLogin {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
