package com.spring.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HelloRequest {

    @ApiModelProperty(value = "The name which should be included the welcome message", example = "Thomas")
    private String name;
}
