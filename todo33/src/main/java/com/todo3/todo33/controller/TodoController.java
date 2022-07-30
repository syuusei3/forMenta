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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo3.todo33.entity.TodoCards;
import com.todo3.todo33.entity.TodoLists;
import com.todo3.todo33.entity.TodoContents;
import com.todo3.todo33.entity.AccountInfo;
import com.todo3.todo33.mapper.TodoMapper;
import com.todo3.todo33.model.TodoModel;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;




// import java.util.Map;
// import java.util.Arrays;
// import java.util.HashMap;
import java.util.ArrayList;



//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoMapper todoMapper;

    @GetMapping("/logintest")
    public String test() {
        return "Hello World!!";
    }

    
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

            List<TodoLists> listsList = todoMapper.selectAllLists();//id,title
            List<TodoCards> cardsList = todoMapper.selectAllCards();//task_id,card_title,list_id
            List<TodoContents> contentsList = todoMapper.selectAllContents();//id,title
            List<TodoModel> tmpList = new ArrayList<TodoModel>();


            // TodoModel.Card c = new TodoModel.Card(card.card_id, card.card_title, card.list_id);
            // todoModel.cards.add(c);
            //List<Card> tmpcard = new ArrayList<Card>();

            listsList.forEach(list -> {
                tmpList.add(new TodoModel(list.id, list.title));    
            });

            System.out.println("!!!!!!!!!Send Lists !!!!!!");
            tmpList.forEach(todoModel -> {
                cardsList.forEach(card -> {
                    if(todoModel.list_id == card.list_id) {
                        todoModel.cards.add(new TodoModel.Card(card.card_id, card.card_title, card.list_id));
                        
                        // contentsList.forEach(content ->{
                        //     if(card.card_id == content.card_id){
                        //         //content.add(new TodoModel.Card.Content(content.content_id, content.content, content.card_id));
                        //         todoModel.cards.forEach(tmpCard -> {
                        //             tmpCard.contents.add(new TodoModel.Card.Content(content.content_id, content.content, content.card_id));

                        //         });

                        //     }
                        // });
                        
                        System.out.println(todoModel.list_id + ":" + todoModel.list_title + ":" + card.card_title );
                    }
                });
            });
            
            tmpList.forEach(todoModel -> {
            todoModel.cards.forEach(tmpCard -> {
                contentsList.forEach(content ->{
                            //content.add(new TodoModel.Card.Content(content.content_id, content.content, content.card_id));
                                if(tmpCard.card_id == content.card_id){
                                tmpCard.contents.add(new TodoModel.Card.Content(content.content_id, content.content, content.card_id));
                                }
                            });
                    });
            });
            

            return new ResponseEntity<List<TodoModel>>(tmpList, HttpStatus.OK);

		//} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			//return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		//}
    }

    @PostMapping("/addtodo")
    public ResponseEntity<Object> addTodo(@RequestBody TodoLists titleList){
        try {
            System.out.println("!!!!!!!!!Add todo Lists!!!!!!");
            todoMapper.addLists(titleList);
			return new ResponseEntity<Object>(titleList, HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
    @PostMapping("/deletetodo")
    public ResponseEntity<Object> deleteTodo(@RequestBody TodoLists titleList){
        try {
            System.out.println("!!!!!!!!!delete todo Lists!!!!!!");
            System.out.println(titleList);
            todoMapper.deleteLists(titleList);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }


    /////////////////////cards///////////////////
    @PostMapping("/addcards")
    public ResponseEntity<Object> addTodoCards(@RequestBody TodoCards cards){
        try {
            System.out.println("!!!!!!!!!Add cards!!!!!!");
            System.out.println(cards);
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
            System.out.println("!!!!!!!!!Delete cards!!!!!!");
            System.out.println(cards);
            todoMapper.deleteCards(cards);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }



    /////////////////////Contents///////////////////
    @PostMapping("/addcontents")
    public ResponseEntity<Object> addTodoContents(@RequestBody TodoContents contents){
        try {
            System.out.println("!!!!!!!!!Add contents!!!!!!");
            System.out.println(contents);
            todoMapper.addContents(contents);
			return new ResponseEntity<Object>(contents, HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }
    @PostMapping("/deletecontents")
    public ResponseEntity<Object> deleteTodoContents(@RequestBody TodoContents contents){
        try {
            System.out.println("!!!!!!!!!Delete cards!!!!!!");
            System.out.println(contents);
            todoMapper.deleteContents(contents);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }

    /////////////////////Login///////////////////
//    @GetMapping("/login")
//    public ResponseEntity<Object> showLoginForm(){
//        return new ResponseEntity<Object>(HttpStatus.OK);
//    }
    // @Autowired
	// private AuthenticationManager authenticationManager;

    // @PostMapping("/login")
    // public ResponseEntity<Object> cheackTodoAccounts(@RequestBody AccountInfo user) throws Exception {
		
	// 	Authentication authObject = null;
	// 	try {
	// 		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
	// 		SecurityContextHolder.getContext().setAuthentication(authObject);
	// 	} catch (BadCredentialsException e) {
	// 		throw new Exception("Invalid credentials");
	// 	}
		
	// 	return new ResponseEntity<Object>(HttpStatus.OK);
	// }

    @PostMapping("/addaccount")
    public ResponseEntity<Object> addTodoAccounts(@RequestBody AccountInfo info){
        try {
            System.out.println("!!!!!!!!!New Accounts!!!!!!");
            System.out.println(info);
            todoMapper.addUsers(info);
			return new ResponseEntity<Object>(info.email, HttpStatus.OK);
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
    }


}