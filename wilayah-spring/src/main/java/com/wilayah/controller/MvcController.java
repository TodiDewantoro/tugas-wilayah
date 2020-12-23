package com.wilayah.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MvcController {
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index() {
	    return "index.html";
	}
}
