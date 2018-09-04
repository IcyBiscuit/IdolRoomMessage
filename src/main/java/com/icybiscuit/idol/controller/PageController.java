package com.icybiscuit.idol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(path = {"/", "/rank"})
    public String rank() {

        return "rank";
    }

    @GetMapping(path = "/member")
    public String member() {
        return "member";
    }
}
