package com.alpha.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import com.alpha.entity.AppRoleUser;

@Builder
@Data
@Getter
@Setter
public class CreateUserModel {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
    
    
    

}
