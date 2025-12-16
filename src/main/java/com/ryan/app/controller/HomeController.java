package com.ryan.app.controller;

import com.ryan.app.common.CommonException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends BaseController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String execute(Model model) throws CommonException {
		return "home";
	}

}
