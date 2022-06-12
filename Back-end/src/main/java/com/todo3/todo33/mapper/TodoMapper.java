package com.todo3.todo33.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todo3.todo33.entity.Todo;



//this file is interface for gettig method?????

@Mapper
public interface TodoMapper {
    public List<Todo> selectAll();
    public void add(Todo todo); //addLists
    public void delete(Todo todo);//deleteLists
    // public List<TodoCards> selectAll();
    // public void add(TodoCards todo); //addLists
    // public void delete(TodoCards todo);//deleteList
}

