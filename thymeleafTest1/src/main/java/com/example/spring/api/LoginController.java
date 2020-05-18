//package com.example.spring.api;
//
//import java.io.IOException;
//import java.text.ParseException;
//
//import javax.servlet.http.HttpSession;
//
//import org.apache.tomcat.util.json.JSONParser;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.github.scribejava.core.model.OAuth2AccessToken;
//
//public class LoginController {
//
//	@RequestMapping(value = "/naverLogin", method = RequestMethod.GET)
//	public ModelAndView naverLogin(HttpSession session) {
//		/* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
//		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
//
//	return new ModelAndView("login/naverLogin", "url", naverAuthUrl);
//	}
//	
//	@RequestMapping(value = "/callback", method = RequestMethod.GET)
//		public String callback(@RequestParam String code, @RequestParam String state, HttpSession session, Model model, UserVO vo) throws Exception {
//			/* 네아로 인증이 성공적으로 완료되면 code 파라미터가 전달되며 이를 통해 access token을 발급 */
//			logger.info("naver login............");
//			JsonParser json = new JsonParser();
//	
//			OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
//			String apiResult = naverLoginBO.getUserProfile(oauthToken);
//			vo = json.changeJson(apiResult); // vo에 userEmail, userGender, userNaver 저장
//	
//			if (service.selectNaver(vo) > 0) { // 세션만들기
//				session.setAttribute("login", vo);
//			} else {
//				service.insertNaver(vo);
//				session.setAttribute("login", vo);
//			}
//	
//		return "/login/callback";
//	}
//}
