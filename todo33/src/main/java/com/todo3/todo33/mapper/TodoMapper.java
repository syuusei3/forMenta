package com.todo3.todo33.mapper;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import org.apache.ibatis.annotations.Mapper;

import com.todo3.todo33.entity.TodoCards;
import com.todo3.todo33.entity.TodoLists;

//this file is interface for gettig method?????
@Mapper
public interface TodoMapper {
    ////Lists////
    public List<TodoLists> selectAllLists();
    public void addLists(TodoLists todo); //addLists
    public void deleteLists(TodoLists todo);//deleteLists

    ///////cards//////
    public List<TodoCards> selectAllCards();
    public void addCards(TodoCards cards); //addCards
    public void deleteCards(TodoCards cards);//deleteCards
}


