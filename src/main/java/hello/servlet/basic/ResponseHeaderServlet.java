package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //응답코드[status-line]
        response.setStatus(HttpServletResponse.SC_OK); // SC_OK 처럼 쓰는게 좋다

        //[response-header]
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache, no-store,must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-header","hello");

        PrintWriter writer = response.getWriter();
        writer.println("하이");
    }

}
