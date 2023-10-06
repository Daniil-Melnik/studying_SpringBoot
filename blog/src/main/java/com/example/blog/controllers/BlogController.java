
package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}