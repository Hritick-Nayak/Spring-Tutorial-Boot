package com.myprojects.springboot.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.myprojects.springboot.web.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService todoService;

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET) // it is used as get as it shown when I type a
																		// url // No submision so no post required
	public String todoPage(ModelMap model) {
		String name = (String) model.get("name");
		model.put("todos", todoService.retrieveTodos(name));

		return "list-todos";

	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		String name = (String) model.get("name");
		model.put(name, name);
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @RequestParam String desc) {

		String name = (String) model.get("name");
		model.put(name, name);
		todoService.addTodo(name, desc, new Date(), false);

//		model.put("todos", todoService.retrieveTodos(name)); Dublication of logic

//		return "list-todos";

		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {

//		String name = (String) model.get("name");
//		model.put("todos", todoService.retrieveTodos(name));

		todoService.deleteTodo(id);

		return "redirect:/list-todos";

	}

}
