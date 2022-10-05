package org.zerock.mytodo.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.mytodo.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        log.trace("t-----------------------");
        log.debug("d-----------------------");
        log.info("i-----------------------");
        log.warn("w-----------------------");
        log.error("e-----------------------");
        log.fatal("f-----------------------");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        StringBuffer buffer = (StringBuffer) request.getServletContext().getAttribute("Sample");
        buffer.append("A");

        Cookie loginCookie = CookieUtil.findCookie(request,"login");
        if(loginCookie != null){
            log.info(loginCookie.getValue());
            String value = loginCookie.getValue();
            value += '%'+value;

            loginCookie.setValue(value);
            response.addCookie(loginCookie);

        }
        request.getRequestDispatcher("/WEB-INF/views/hello.jsp");
    }

    public void destroy() {
    }
}