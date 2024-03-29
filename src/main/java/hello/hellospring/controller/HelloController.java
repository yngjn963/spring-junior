package hello.hellospring.controller;

/*
Controller: 웹 애플리케이션에서 첫 번째 진입점
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 /hello 로 접근하면 해당 메소드를 호출한다.
    public String hello(Model model) { // 스프링이 Model을 만들어 파라미터로 넘겨준다.
        model.addAttribute("data", "hello!!");

        return "hello"; // 이름이 resources/templates에 있는 동일한 이름의 템플릿을 찾아 렌더링한다.
        /*
        컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면(템플릿 html)을 찾아서 처리한다.
        스프링 부트 템플릿엔진 기본 viewName 매핑
        resources:templates/ +{ViewName}+ .html
         */

        /*
        스프링 웹 개발 기초
        정적 컨텐츠: 서버에서 별다른 작업 없이 파일을 그대로 웹 브라우저에 내려주는 방식
        MVC와 템플릿 엔진: 서버에서 html에 필요한 작업을 수행하여 전달하는 방식
        API: JSON 데이터 구조 포맷으로 클라이언트에 데이터를 전달하는 방식(html이 아닌 데이터를 바로 내리는 방법)

        정적 컨텐츠
        스프링은 요청이 오면 컨트롤러에서 우선적으로 찾는다.
        관련 컨트롤러가 없을 경우 static 하위에서 정적 컨텐츠를 찾아 반환한다.

        MVC와 템플릿 엔진
        MVC: Model, View, Controller
        Controller: @Controller
        View: resources/template/hello-template.html
        * 과거에는 Controller와 View를 분리하지 않고 개발: 모델 원 방식
        분리하는 이유: 관심사 분리, 역할과 책임
        - View는 화면을 그리는데 집중해야 한다.
        - Model/Controller는 비즈니스 로직, 내부적인 처리에 집중해야 한다.
       */
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) { // required 기본은 true, required를 생략했을 경우 name parameter를 get 방식으로 던져야 한다.
        model.addAttribute("name", name); // key, value

        return "hello-template";
    }
    // 그냥 실행 시 오류 발생: Required String parameter 'name' is not present

    @GetMapping("hello-string")
    @ResponseBody // http의 body부에 return 값을 직접 넣어주겠다!
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }
    // 템플릿 엔진은 view라는 템플릿이 있고 그 템플릿을 조작하는 방식이라면, API 방식은 view 없이 데이터를 그대로 클라이언트에 내린다.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();

        hello.setName(name);

        return hello; // 문자가 아닌 객체 반환
        // json 구조의 데이터를 반환한다.
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        // getter, setter: private 필드에 접근 >> 프로퍼티 접근 방식, 자바 빈 규약
    }
    // @ResponseBody: http 응답에 값을 그대로 던진다.
    // 뷰 리졸버가 아닌 HttpMessageConverter가 동작
    // 문자가 반환되면 StringConverter(기본 문자처리: StringHttpMessageConverter)
    // 문자가 아닌 객체가 오면(객체가 return)? json 형식으로 반환하는 JsonConverter가 동작한다(기본 객체처리: MappingJackson2HttpMessageConverter).
    // * Jackson: 많이 사용하는 Json 라이브러리(그 외 Google의 Gson 등이 있다).
    // * 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서 HttpMessageConverter가 선택된다. HTTP에서 xml만 취급한다면 xml로 반환하는 Converter도 사용할 수 있다..
}
