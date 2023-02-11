package com.company.bcpayments.controllers;

import com.company.bcpayments.services.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
@Slf4j
@Api(value = "/", tags = "Description")
@RequestMapping("api/v1/admin")
@ResponseStatus(HttpStatus.OK)

public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation(value = "Return the token name")
    @RequestMapping(value = "/name",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameter(name = "authentication", hidden = true)
    @ResponseBody
    public String getName() {
         return adminService.name();
    }

    @ApiOperation(value = "Tota token minted")
    @RequestMapping(value = "/totalMinted",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameter(name = "authentication", hidden = true)
    @ResponseBody
    public String totalTokensCirculating( ) {
        return adminService.totalTokens();
    }

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }
}
