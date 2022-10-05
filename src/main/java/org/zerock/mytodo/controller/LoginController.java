package org.zerock.mytodo.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.zerock.mytodo.dao.MyBatisUtil;
import org.zerock.mytodo.dto.MemberDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginController", value = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SqlSession session
                = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);
        String mid = request.getParameter("mid");
        log.info(mid);
        String mpw = request.getParameter("mpw");
        log.info(mpw);


        String rememberme = request.getParameter("remember-me")==null?"":request.getParameter("remember-me");
        log.info(rememberme);


        MemberDTO memberDTO = new MemberDTO().builder().mid(mid).mpw(mpw).build();
        log.info(memberDTO);
        try {
            MemberDTO memberDTO2 = session.selectOne("org.zerock.mytodo.dao.MemberMapper.check", memberDTO);
//            log.info(memberDTO2);
//            // User-Defined Cookie 이름과 값
//            Cookie loginCookie = new Cookie("login","Hmm");
//            // 만료 시간 지정  = 유통기한 - 초단위
//            loginCookie.setMaxAge(1000*60);
//            // 경로 지정
//
//            response.addCookie(loginCookie); // 필수 코드.

//            세션 방식 ------------------------------------------------------
            HttpSession session2 = request.getSession();
            session2.setAttribute("loginResult", rememberme);

            if(rememberme.equals("on")){
                Cookie loginCookie = new Cookie("loginResult", URLEncoder.encode("Hong Gil Dong","UTF-8"));
                loginCookie.setPath("/");
                loginCookie.setMaxAge(60*60*24*7); // 일주일
                log.info(loginCookie);
                response.addCookie(loginCookie);
            }

            if(session2.getAttribute("wanted") != null){
                response.sendRedirect((String)session2.getAttribute("wanted"));
                session2.removeAttribute("wanted");
            }else {
                response.sendRedirect("/todo/list");
            }

            return;

        }catch (Exception e){
            response.sendRedirect("/login?error=failed");
        }


    }
}
