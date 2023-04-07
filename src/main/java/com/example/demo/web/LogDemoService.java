package com.example.demo.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import com.example.demo.common.MyLogger;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {
	private final MyLogger myLogger;
	// private final ObjectProvider<MyLogger> myLoggerProvider;
	public void logic(String testId) {
		// MyLogger myLogger = myLoggerProvider.getObject();
		myLogger.log("Service id =" + testId);
	}
}
