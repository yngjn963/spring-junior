package hello.hellospring.controller;

/*
Controller: 웹 애플리케이션에서 첫 번째 진입점
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 /hello 로 접근하면 해당 메소드를 호출한다.
    public String hello(Model model) { // 스프링이 Model을 만들어 파라미터로 넘겨준다.
        model.addAttribute("data", "hello!!");

        return "hello"; // 이름이 resources/templates에 있는 동일한 이름의 템플릿을 찾아 렌더링한다.
        /*
        컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다.
        스프링 부트 템플릿엔진 기본 viewName 매핑
        resources:templates/ +{ViewName}+ .html
         */
    }
}
