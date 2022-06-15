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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.todo3.todo33.entity.TodoLists;
import com.todo3.todo33.entity.TodoCards;

import com.todo3.todo33.mapper.TodoMapper;
import com.todo3.todo33.model.TodoModel;
import com.todo3.todo33.model.TodoModel.Card;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoMapper todoMapper;
    
    @RequestMapping(value="/")
    public String index(Model model) {
        List<TodoLists> list = todoMapper.selectAllLists();
        model.addAttribute("todos",list);
        return "index";
    }
    @RequestMapping(value="/add")
    public String add(TodoLists todo) {
        todoMapper.addLists(todo);
        return "redirect:/";
    }
    @RequestMapping(value="/delete")
    public String delete(TodoLists todo) {
        todoMapper.deleteLists(todo);
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
        List<TodoLists> list = todoMapper.selectAllLists();
        model.addAttribute("todos",list);

        return "test";
    }

    ////////Lists////////
    @GetMapping("/gettodo")
    //@RequestMapping(path = "/gettodo", method = RequestMethod.GET)
    //public TodoResponse getTodo(Model model/){
        public ResponseEntity<List<TodoModel>> getTodo(){

            List<TodoLists> lists = todoMapper.selectAllLists();//id,title
            List<TodoCards> cards = todoMapper.selectAllCards();//id,title
            List<TodoModel> tmpList = new ArrayList<TodoModel>();
            //List<Card> tmpcard = new ArrayList<Card>();

            lists.forEach(list -> {
                tmpList.add(new TodoModel(list.id, list.title));    
            });

            tmpList.forEach(todoModel -> {
                cards.forEach(card -> {
                    if(todoModel.list_id == card.list_id) {
                        //todoModel.cards.add(new Card()));
                        todoModel.cards.add(new TodoModel.Card(card.card_title));
                    }
                });
            });

            //List<TodoModel> tmpList = Arrays.asList(res1, res2, res3);

            return new ResponseEntity<List<TodoModel>>(tmpList, HttpStatus.OK);
/* 
            TodoModel res1 = new TodoModel();
            res1.cards = Arrays.asList("a", "b");
            res1.listName = "AAAAA";

            TodoModel res2 = new TodoModel();
            res2.cards = Arrays.asList("c", "d");
            res2.listName = "BBBBB";

            List<TodoLists> lists = todoMapper.selectAllLists();
            TodoModel res3 = new TodoModel();
            //res3.cards = Arrays.asList("e", "fffff");
            res3.listName = "CCCC";
            lists.forEach(
                list ->  res3.cards = Arrays.asList("e",("{}",[list[0]]))
                );
                */
            //return new ResponseEntity<List<TodoModel>>( Arrays.asList(res1, res2,res3), HttpStatus.OK);
            //return new ResponseEntity<List<TodoModel>>( Arrays.asList(res1, res2, res3), HttpStatus.OK);
        //try {
/* 
            List<TodoLists> lists = todoMapper.selectAllLists();
            List<TodoCards> cards = todoMapper.selectAllCards();//selectALL into list

            System.out.printf("!!!!!!!!cards!!!!!!!");
            // lists.forEach(list->
            //     cards.forEach(card -> logger.info("{}",card));
            //     );

            //logger.info("{}", cards);
            // int num[];
            // Map<Integer, String > aaa = new HashMap<>();
            cards.forEach(card -> logger.info("{}", card.getCardTitle()));

            cards.forEach(card -> logger.info("{}", card.getId()));
            //cards.forEach(card -> list.setCard(card));

            System.out.printf("!!!!!!!!!Lists!!!!!!");
            //lists.add(3);

            //logger.info("{}", lists);
            System.out.printf("!!!!!!!!!Lists todos!!!!!!");
            lists.forEach(list -> logger.info("{}",list));

            System.out.printf("!!!!!!!!!Lists model!!!!!!");

            model.addAttribute("todos",lists);
			logger.info("{}", model);
            
            ObjectMapper mapper = new ObjectMapper();
            String strJson = mapper.writeValueAsString(model);//genarate json

			return new ResponseEntity<Object>(strJson, HttpStatus.OK);

            */

		//} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			//return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		//}
    }

    @PostMapping("/addtodo")
    public ResponseEntity<Object> addTodo(@RequestBody TodoLists todo){
        try {
            todoMapper.addLists(todo);
			return new ResponseEntity<Object>(todo, HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
    @PostMapping("/deletetodo")
    public ResponseEntity<Object> deleteTodo(@RequestBody TodoLists todo){
        try {
            todoMapper.deleteLists(todo);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
    /////////////////////cards///////////////////
    @GetMapping("/getcards")
    public ResponseEntity<Object> getTodoCards(Model model){
        try {
            List<TodoCards> list = todoMapper.selectAllCards();
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

    @PostMapping("/addcards")
    public ResponseEntity<Object> addTodoCards(@RequestBody TodoCards cards){
        try {
            todoMapper.addCards(cards);
			return new ResponseEntity<Object>(cards, HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
    @PostMapping("/deletecards")
    public ResponseEntity<Object> deleteTodoCards(@RequestBody TodoCards cards){
        try {
            todoMapper.deleteCards(cards);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }


}