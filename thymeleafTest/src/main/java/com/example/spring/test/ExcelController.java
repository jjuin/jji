//package com.example.spring.test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.View;
//
//public class ExcelController {
//
//	@RequestMapping(value ="/", method = RequestMethod.GET) 
//	public String dispHomeView(ModelMap model) { 
//		
//		return "home"; 
//		
//	}
//	
//	// 데이터를 엑셀로 추출하여 프론트엔드에 전달한다. 
//	@RequestMapping(value = "/excel", method = RequestMethod.GET) 
//	public View viewExcel(Model model) { 
//		// 임의의 데이터를 만듬. 
//		List<String> listData = new ArrayList<String>(); 
//		listData.add("홍길동"); 
//		listData.add("나그네"); 
//		listData.add("홍길동"); 
//		listData.add("홍길동"); 
//		listData.add("홍길동"); 
//		listData.add("홍길동"); 
//		listData.add("홍길동"); 
//		
//		// 차트를 만들기 위한 통계자료도 구한다. 
//		List<Map<String, Object>> listStat = new ArrayList<Map<String, Object>>(); 
//		Map<String, Object> mapStat = new HashMap<String, Object>(); 
//		mapStat.put("name", "홍길동"); 
//		mapStat.put("count", 6); 
//		listStat.add(mapStat); 
//		mapStat = new HashMap<String, Object>(); 
//		mapStat.put("name", "나그네"); 
//		mapStat.put("count", 1); 
//		listStat.add(mapStat); 
//		
//		// 데이터를 담는 다. 
//		model.addAttribute("data", listData); 
//		model.addAttribute("stat", listStat); 
//		
//		// 엑셀을 출력한다. 
//		return new ExcelView();
//		
//	}
//
//
//}
