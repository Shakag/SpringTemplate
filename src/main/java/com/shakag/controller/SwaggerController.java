package com.shakag.controller;

import com.shakag.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "SwaggerController info")
public class SwaggerController {

    @PostMapping(value = "/sg")
    @ApiOperation(value = "sg method info")
    public SysUser sg(SysUser userVO){
        return new SysUser();
    }
}
