package com.alpha.request;

import com.alpha.model.auth.TokenAuthorization;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthLoginResp implements Serializable {

    private TokenAuthorization authorization;
    private String token;

}
