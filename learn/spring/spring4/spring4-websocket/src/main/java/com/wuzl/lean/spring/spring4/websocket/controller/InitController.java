package com.wuzl.lean.spring.spring4.websocket.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/websocket")
public class InitController {
	private static final Logger log = Logger.getLogger(InitController.class);

	/**
	 * 初始化
	 * 
	 * @return
	 */
	@RequestMapping("/init")
	public ModelAndView init() {
		log.info("进入初始化页面，开启websockey旅程 ");
		ModelAndView model = new ModelAndView("index");
		return model;
	}
}
