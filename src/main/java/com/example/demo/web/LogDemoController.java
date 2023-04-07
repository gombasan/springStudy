package com.example.demo.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.MyLogger;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
	private final LogDemoService logDemoService;
	private final MyLogger myLogger;
	// private final ObjectProvider<MyLogger> myLoggerProvider;

	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {
		// MyLogger myLogger = myLoggerProvider.getObject();
		String requestURI = request.getRequestURI();
		myLogger.setRequestURL(requestURI);

		myLogger.log("controller test");
		logDemoService.logic("testId");

		return "OK";

	}
}
