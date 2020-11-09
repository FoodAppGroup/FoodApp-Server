package com.spring.server.controller;

import com.spring.server.model.HelloRequest;
import com.spring.server.model.HelloResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ApiOperation("Return a personal welcome message, if given a name")
    @RequestMapping(value = "/hello2",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloResponse> hello(@RequestBody HelloRequest request) {
        HelloResponse helloResponse = new HelloResponse();
        helloResponse.setMessage("Hello " + request.getName() + "!");
        return ResponseEntity.ok(helloResponse);
    }
}
