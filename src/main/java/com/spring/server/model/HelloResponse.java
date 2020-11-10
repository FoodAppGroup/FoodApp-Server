package com.spring.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HelloResponse {

    @ApiModelProperty(value = "The personal welcome message.", example = "Hello Thomas!")
    private String message;
}
