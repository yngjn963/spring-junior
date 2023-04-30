package hello.hellospring.controller;

public class MemberForm {
    public String getName() {
        return name; // input name 값과 매핑
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
