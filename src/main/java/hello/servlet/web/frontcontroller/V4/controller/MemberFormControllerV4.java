package hello.servlet.web.frontcontroller.V4.controller;

import hello.servlet.web.frontcontroller.V4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<java.lang.String, java.lang.String> paramMap, Map<java.lang.String, Object> model) {
        return "new-form";
    }


}
