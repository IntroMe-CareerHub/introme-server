package com.introme.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class oauthController {
    private static final Logger log = LoggerFactory.getLogger(oauthController.class);
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
