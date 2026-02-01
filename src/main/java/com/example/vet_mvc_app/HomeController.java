package com.example.vet_mvc_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String simplePage() {
        return "/HomePage";
    }

}