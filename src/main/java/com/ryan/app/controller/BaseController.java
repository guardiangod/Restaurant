package com.ryan.app.controller;

import javax.servlet.http.HttpServletResponse;

import com.ryan.app.common.CommonException;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

	@ExceptionHandler(CommonException.class)
	public @ResponseBody
	String handleException(CommonException le, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return le.getMessage();
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public ModelAndView handleException(HttpSessionRequiredException le, HttpServletResponse response) {
		final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", 
        		"Please log-in/sign-up with HPE to write reviews about restaurants or register your own restaurant!");
        modelAndView.setViewName("error");
        return modelAndView;
	}
}
