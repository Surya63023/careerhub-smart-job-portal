package com.smartjobportal.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BrowserLauncher {

	@EventListener(ApplicationReadyEvent.class)
	public void openBrowser() {

		try {

			Runtime.getRuntime().exec("cmd /c start chrome http://localhost:8080/html/login.html");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}