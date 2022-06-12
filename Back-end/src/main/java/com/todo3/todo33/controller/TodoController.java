package com.todo3.todo33.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.todo3.todo33.entity.Todo;
import com.todo3.todo33.mapper.TodoMapper;

import com.fasterxml.jackson.databind.ObjectMapper;




//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoMapper todoMapper;

    @RequestMapping(value="/")
    public String index(Model model) {
        List<Todo> list = todoMapper.selectAll();
        model.addAttribute("todos",list);

        return "index";
    }

    @RequestMapping(value="/add")
    public String add(Todo todo) {
        todoMapper.add(todo);
        return "redirect:/";
    }
    @RequestMapping(value="/delete")
    public String delete(Todo todo) {
        todoMapper.delete(todo);
        return "redirect:/";
    }
    // @RequestMapping(value="/delete")
    // public String delete(Todo todo) {
    //     todoMapper.delete(todo);
    //     return "redirect:/";
    // }
    




    ////////for confirmation////////

    @GetMapping("/hello")
	public String hello() {
		return "hellooooooooooooooooooo!";
	}

	@GetMapping(value="/test")
    public String test(Model model) {
        List<Todo> list = todoMapper.selectAll();
        model.addAttribute("todos",list);

        return "test";
    }


    ////////for confirmation////////
    @GetMapping("/gettodo")
    public ResponseEntity<Object> getTodo(Model model){
        try {

            List<Todo> list = todoMapper.selectAll();
            model.addAttribute("todos",list);
			logger.info("{}", model);
            
            ObjectMapper mapper = new ObjectMapper();
            String strJson = mapper.writeValueAsString(model);

			return new ResponseEntity<Object>(strJson, HttpStatus.OK);

		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }

    @PostMapping("/addtodo")
    public ResponseEntity<Object> addTodo(@RequestBody Todo todo){
        try {
            todoMapper.add(todo);
			return new ResponseEntity<Object>(todo, HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
    @PostMapping("/deletetodo")
    public ResponseEntity<Object> deleteTodo(@RequestBody Todo todo){
        try {
            todoMapper.delete(todo);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
}