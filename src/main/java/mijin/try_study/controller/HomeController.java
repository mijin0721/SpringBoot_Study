package mijin.try_study.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    //메인 페이지
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
