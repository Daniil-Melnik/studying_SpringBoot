
package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.Auto;
import com.example.blog.DataBase;


@Controller
public class BlogController {

	@GetMapping("/blog")
	public String blog(Model model) {
		model.addAttribute("title", "Блог");
		Iterable<Auto> auto_list = DataBase.getAutos();
		model.addAttribute("autos", auto_list);
		return "blog-main";
	}

	@GetMapping("/blog/add")
	public String blogAdd(Model model) {
		return "blog-add";
	}

	@PostMapping("/blog/add")
	public String blogPostAdd(@RequestParam String name, @RequestParam String comand, Model model){
		System.out.println( "QQQQQQQ" + name + " " + " " + comand);
		Auto auto = new Auto();
		auto.setAuto(0, name, comand, "full_text", "https://i.ytimg.com/vi/BnvuQe6KcU8/maxresdefault.jpg");
		DataBase.addAuto(auto);
		auto = null;
		Iterable<Auto> auto_list = DataBase.getAutos();
		model.addAttribute("autos", auto_list);
		return "blog-main";
	}
}