package org.zerock.mytodo;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.mytodo.dao.TodoDAO;
import org.zerock.mytodo.dto.PageRequestDTO;
import org.zerock.mytodo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;

@Log4j2
public class MyBatisTest {

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("12")
                .from(LocalDate.of(2022,10,01))
                .to(LocalDate.of(2022,10,10))
                .complete(true)
                .build();

        List<TodoDTO> list = TodoDAO.INSTANCE.getList(pageRequestDTO);
        list.forEach(todoDTO-> log.info(todoDTO));
    }
}
