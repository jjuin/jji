package com.example.spring.project.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.spring.user.UserTestController;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	 	LOGGER.info("Interceptor > preHandle");
	 	try {
	 		//로그인 상태 아니면 홈으로 이동
	 		if(request.getSession().getAttribute("cus_id") == null) {
	 			//ex) http://locahost:8000/project/member/loginForm
	 			response.sendRedirect("/project/member/needLogin");
	 			return false;
	 		}
	 		else {
	 			return true;
	 		}
	 	}
	 	catch(Exception e){
	 		e.printStackTrace();
	 	}
		return super.preHandle(request, response, handler);
    }

   @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	LOGGER.info("Interceptor > postHandle");
    
    	super.postHandle(request, response, handler, modelAndView);
    }

   @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
    	LOGGER.info("Interceptor > afterCompletion" );
    }
}
