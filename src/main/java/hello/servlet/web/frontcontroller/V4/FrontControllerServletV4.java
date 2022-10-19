package hello.servlet.web.frontcontroller.V4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.V4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.V4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.V4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name ="frontControllerServletV4",urlPatterns = "/front-controller/v4/*") //* -> v2하위에 어떤게 들어와도 무조건 frontController 호출
public class FrontControllerServletV4 extends HttpServlet {

    private Map<java.lang.String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form",new MemberFormControllerV4()); //앞에 요청이 오면 뒤에가 실행되게
        controllerMap.put("/front-controller/v4/members/save",new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");


        java.lang.String requestURI = request.getRequestURI(); // "/front-controller/v1/members/new-form" 이걸 얻을수있다.

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //paramMap
        Map<String, String> paramMap = createParamMap(request);
        Map<String , Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        view.render(model,request,response);
    }

    private static MyView viewResolver(java.lang.String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<java.lang.String, java.lang.String> createParamMap(HttpServletRequest request) {
        Map<java.lang.String, java.lang.String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
