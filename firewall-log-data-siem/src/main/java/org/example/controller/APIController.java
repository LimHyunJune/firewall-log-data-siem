package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "API Controller")
public class APIController {

    @Operation(summary = "test api")
    @GetMapping("/test")
    public String test()
    {
        return "ok";
    }
}
