package org.zerock.mytodo.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.zerock.mytodo.dao.MyBatisUtil;
import org.zerock.mytodo.dao.TodoDAO;
import org.zerock.mytodo.dto.PageRequestDTO;
import org.zerock.mytodo.dto.PageResponseDTO;
import org.zerock.mytodo.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.zerock.mytodo.dao.TodoDAO;
import org.zerock.mytodo.dto.PageRequestDTO;
import org.zerock.mytodo.dto.PageResponseDTO;
import org.zerock.mytodo.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@WebServlet(name = "TodoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    protected int getInt(String value, int defaultValue, Predicate<Integer> predicate){
        try {
            int intValue = Integer.parseInt(value);

            if(predicate != null) {
                boolean result = predicate.test(intValue);

                if (!result) {
                    return defaultValue;
                }
            }

            return intValue;
        }catch(Exception e){
            return defaultValue;
        }
    }

    protected  LocalDate getLocalDate(String value,boolean end){
        try{
            LocalDate localDate = LocalDate.parse(value);
            return localDate;
        }catch (Exception e){
            if(end){
                return LocalDate.of(2100,12,31);
            }else {
                return LocalDate.of(1970, 01, 01);
            }
        }
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/" +page+".jsp").forward(request,response);

    }
    protected void redirect(HttpServletResponse response, String queryString) throws ServletException, IOException {

        response.sendRedirect(queryString);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page = getInt(request.getParameter("page"), 1, (value) -> {
            if(value < 0){
                return false;
            }else {
                if(value > 100){
                    return false;
                }
            }
            return true;
        });

        int size = getInt(request.getParameter("size"), 10, null);

        LocalDate from = getLocalDate(request.getParameter("from"),false);
        LocalDate to = getLocalDate(request.getParameter("to"),true);

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(page)
                .size(size)
                .from(from)
                .to(to)
                .build();

        log.info(pageRequestDTO);

        List<TodoDTO> dtoList = TodoDAO.INSTANCE.getList(pageRequestDTO);
        log.info(dtoList);
        request.setAttribute("dtoList", dtoList);

        PageResponseDTO responseDTO
                = new PageResponseDTO(pageRequestDTO, TodoDAO.INSTANCE.getTotal(pageRequestDTO));

        request.setAttribute("pageDTO", responseDTO);

        dispatch(request,response, "/todo/list");

    }

}