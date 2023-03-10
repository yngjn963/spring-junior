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

        /*
        스프링 웹 개발 기초
        정적 컨텐츠: 서버에서 별다른 작업 없이 파일을 그대로 웹 브라우저에 내려주는 방식
        MVC와 템플릿 엔진: 서버에서 html에 필요한 작업을 수행하여 전달하는 방식
        API: JSON 데이터 구조 포맷으로 클라이언트에 데이터를 전달하는 방식

        정적 컨텐츠
        스프링은 요청이 오면 컨트롤러에서 우선적으로 찾는다.
        관련 컨트롤러가 없을 경우 static 하위에서 정적 컨텐츠를 찾아 반환한다.

        */
    }
}
