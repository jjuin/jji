package com.example.spring.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoggerTestController {

	private static final Logger LOGGER = LogManager.getLogger( LoggerTestController.class );
	
	@RequestMapping("/logger")
	@ResponseBody
	public String logExam() {
		LOGGER.debug( "#ex1 - debug log" );
		LOGGER.info( "#ex1 - info log" );
		LOGGER.warn( "#ex1 - warn log" );
		LOGGER.error( "#ex1 - error log" );
		
		return "index";
	}
	
}
