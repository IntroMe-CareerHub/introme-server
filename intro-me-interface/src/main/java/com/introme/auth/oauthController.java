package com.introme.auth;

import com.introme.user.entity.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class oauthController {
    private final HttpSession httpSession;

    @GetMapping(value = "/oauth/login")
    public String index2(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("userName", user.getName());

        return "index2";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
