package com.alpha.respond;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponse {

    private String responseCode;
    private String responseDesc;
    private Object data;

}
