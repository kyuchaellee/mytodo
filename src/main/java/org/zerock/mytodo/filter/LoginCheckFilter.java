package org.zerock.mytodo.filter;

import lombok.extern.log4j.Log4j2;
import org.zerock.mytodo.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"/todo/*"}) // /todo/* : 무엇이든 와도 로그인 화면으로 넘어간다
@Log4j2
public class LoginCheckFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        log.info("init.........");
    }

    public void destroy() {
        log.info("destroy........");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("doFilter...............");
        log.info("doFilter...............");
        log.info("doFilter...............");
        log.info("doFilter...............");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        HttpSession session = req.getSession();
        String requestURL = req.getRequestURI();

//        if(requestURL.equals("/todo/list")){
//            chain.doFilter(request,response);
//            return;
//        }

        if(session == null){
            log.info("fdsfsdafdasfsdfdasfdasf");
            res.sendRedirect("/login");
            return;
        }


        if (session.getAttribute("loginResult") == null) {

            Cookie loginCookie = CookieUtil.findCookie(req,"loginResult");

            if(loginCookie==null){
                res.sendRedirect("/login");
                return;
            }

            // 로그인 쿠키 존재 확인 후 작업
            String value = URLDecoder.decode(loginCookie.getValue(),"UTF-8");
            session.setAttribute("loginResult",value);
            chain.doFilter(request,response);
            return;
//            session.setAttribute("wanted",req);
//            res.sendRedirect("/login");
//            return;
        }



        chain.doFilter(request, response); // 필터에서 제일 중요한 메서드: 필터 역할이 끝나면 다음 필터나 목적지로 가게끔 한다

    }
}