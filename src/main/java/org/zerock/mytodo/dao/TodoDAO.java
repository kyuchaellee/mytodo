package org.zerock.mytodo.dao;

import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;
import org.zerock.mytodo.dto.PageRequestDTO;
import org.zerock.mytodo.dto.TodoDTO;
import org.zerock.mytodo.dto.PageRequestDTO;
import org.zerock.mytodo.dto.PageResponseDTO;
import org.zerock.mytodo.dto.TodoDTO;

import java.util.List;

public enum TodoDAO {
    INSTANCE;

    public List<TodoDTO> getList(PageRequestDTO pageRequestDTO){
        @Cleanup SqlSession session
                = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        List<TodoDTO> dtoList = session.selectList("org.zerock.mytodo.dao.TodoMapper.getList",
                pageRequestDTO);

        return dtoList;
    }

    public int getTotal(PageRequestDTO pageRequestDTO){
        @Cleanup SqlSession session
                = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        int total = session.selectOne("org.zerock.mytodo.dao.TodoMapper.getTotal",
                pageRequestDTO);

        return total;
    }

    public TodoDTO getDetail(TodoDTO tno){
        @Cleanup SqlSession session
                = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);
        TodoDTO todoDTO = session.selectOne("org.zerock.mytodo.dao.TodoMapper.selectDetail",
                tno);
        return  todoDTO;
    }

//    public Integer insert(TodoDTO todoDTO) throws Exception{
//        Integer result = null;
//        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//
//        @Cleanup PreparedStatement preparedStatement
//                = connection.prepareStatement(INSERT);
//
//        preparedStatement.setString(1, todoDTO.getTitle());
//        preparedStatement.setString(2,todoDTO.getMemo());
//        preparedStatement.setDate(3, Date.valueOf(todoDTO.getDueDate()));
//
//        //insert/update/delete -> int
//        int count = preparedStatement.executeUpdate();
//        if(count !=1 ){
//            throw new Exception("insert error");
//        } //end if
//        preparedStatement.close();
////        preparedStatement = connection.prepareStatement(Last);
//
////        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
//
////        resultSet.next();
////
////        result = resultSet.getInt(1);
//
//        return result;
//
//    }
}