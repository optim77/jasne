package com.example.dstay.main.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class BlankController implements ErrorController {

    private static final String PATH = "/error";

    @GetMapping(value = PATH)
    public String error(){
        return "<html><p></html>";
    }
}
