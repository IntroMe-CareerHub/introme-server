package com.introme.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class googleController {
    @GetMapping("/test")
    public String jwtTest() {
        log.info("테스트 성공");
        return "Test success!";
    }
}
