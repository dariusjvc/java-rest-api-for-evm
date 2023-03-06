package com.company.bcpayments.controller;

import com.company.bcpayments.service.AdminService;
import com.company.bcpayments.annotation.SwaggerDoc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/admin")
@Slf4j
@SwaggerDoc

public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/name")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public String getName() {
         return adminService.name();
    }

    @GetMapping("/totalMinted")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public String totalTokensCirculating( ) {
        return adminService.totalTokens();
    }
}
