package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(name = "RequestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //메시지 바디의 내용을 바이트 코드로 받을수있다. 스트링으로 바꿔야겠지?

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//문자 -> 바이트로 변환할때 인코딩정보를 명시해야한다 반대도 마찬가지

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");

    }
}
