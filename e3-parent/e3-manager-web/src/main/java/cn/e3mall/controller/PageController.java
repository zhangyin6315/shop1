package cn.e3mall.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3malll.common.pojo.EasyUIDataFGridResult;

@Controller
public class PageController {
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}

}
	
