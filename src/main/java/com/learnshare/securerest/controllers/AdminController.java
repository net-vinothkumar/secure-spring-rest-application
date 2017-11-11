package com.learnshare.securerest.controllers;

import com.learnshare.securerest.bean.HelloMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Api(value = "admin", description = "Rest API for administrative operations", tags = "Admin API")
public class AdminController {

    @RequestMapping(value = "/greet/{name}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Say 'Hi' to the admin user", response = HelloMessage.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
    public HelloMessage sayHello(@PathVariable String name) {
        System.out.println(name + " trying to login.");
        HelloMessage helloMessage = new HelloMessage(name, "Hi Admin " + name + ", Happy Learning...");
        return helloMessage;
    }

}
