package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
    /*
    스프링 컨테이너에서 먼저 템플릿을 찾은 뒤, 없으면 static에서 찾는다.
     */
}
