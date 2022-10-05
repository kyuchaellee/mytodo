package org.zerock.mytodo.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.mytodo.dao.TodoDAO;
import org.zerock.mytodo.dto.PageRequestDTO;
import org.zerock.mytodo.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "TodoDetailController", value = "/todo/detail")
@Log4j2
public class TodoDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        TodoDTO todoDTO= TodoDTO.builder().tno(parseInt(request.getParameter("tno"))).build();

        TodoDTO todoDTO2 = TodoDAO.INSTANCE.getDetail(todoDTO);
        log.info(todoDTO2);
        request.setAttribute("detail",todoDTO2);
        request.getRequestDispatcher("/WEB-INF/views/todo/detail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
