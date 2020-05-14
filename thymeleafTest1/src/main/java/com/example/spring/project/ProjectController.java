package com.example.spring.project;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.api.MemberDTO;
import com.example.spring.project.util.AES256Util;
import com.example.spring.user.UserTestController;

@Controller
@RequestMapping("/project/*")
public class ProjectController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	ProjectService service;
	
	@Autowired
	AES256Util aesUtil;
	
	private String aec_cus_id = ""; //암호화 된 아이디
	public static String cus_name = ""; //회원 이름
	public static String sessionID = ""; //세션 아이디
	
	private String keyword = "";
	private String searchOption = "";

	@RequestMapping("index")
	public String index(Model model, MemberDTO memberDTO, HttpSession session) {
		
		LOGGER.info("index-cus_id--->"+memberDTO.getCus_id()); //아이디 확인
		if(memberDTO.getCus_id() != null) { //로그인 되었다면
			try {
				aec_cus_id = aesUtil.encrypt(memberDTO.getCus_id()); //아이디 암호화
				cus_name = (String) session.getAttribute("cus_name"); //이름 지정
				session.setAttribute("cus_id", aec_cus_id); 
								
				LOGGER.info("index-cus_id 암호화--->"+session.getAttribute("cus_id")); //아이디 확인
				LOGGER.info("index-cus_name session--->"+cus_name); //아이디 확인

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}
		}
		
		sessionID = (String) session.getAttribute("cus_id");
		LOGGER.info("sessionID 확인--->"+sessionID); //아이디 확인
		LOGGER.info("cus_name 확인--->"+cus_name); //아이디 확인
		
		model.addAttribute("sessionID", sessionID);
		model.addAttribute("cus_name", cus_name);
		
		return "project/index";
	} 
	
	//--------------------------------------------------------------------
	
	private String no = ""; //뷰에서 받아온 no임시저장변수'
	
	private List<BatchInfoFile> list =null; //xml파일 data담을 list변수

	//list화면
	@RequestMapping(value = "batchList", method = RequestMethod.GET)
	public String batchList(Model model) throws Exception {
		
		model.addAttribute("keyword", this.keyword);
		model.addAttribute("searchOption", this.searchOption);
		return "test/ajaxTest";
	}

	//xml데이터 가져오기
	@RequestMapping(value = "batchListXML", method = RequestMethod.POST)
	@ResponseBody
	public List<BatchInfoFile> batchListXML(@RequestParam(value="keyword", defaultValue="") String keyword,
																@RequestParam(value="searchOption", defaultValue="JobName") String searchOption,
																@RequestParam(value="search_sort_list[]", required=false) List<String> search_sort_list,
																@RequestParam(value="search_asc_list[]", required=false) List<String> search_asc_list) throws Exception {
		
		this.keyword = keyword;
		this.searchOption = searchOption;
		list = service.xmlGetData(keyword, searchOption, search_sort_list, search_asc_list);
		
		return list;
	} 
	
	//상세화면
	@RequestMapping(value = "batchInfoDetailForm", method = RequestMethod.GET)
	public String batchInfoDetailForm(Model model, @RequestParam(value="no") String no) throws Exception{
		
		model.addAttribute("no", no);
		return "test/infoDetailForm";
	}
	
	//상세처리
	@RequestMapping(value = "batchInfoDetail", method = RequestMethod.POST)
	@ResponseBody
	public List<String> batchInfoDetail(BatchInfoFile batchInfoFile,
											@RequestParam(value="keyword", defaultValue = "") String keyword) throws Exception{
		no = batchInfoFile.getNo();
		
		List<String> resultList = new ArrayList<String>();
		
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getNo().equals(no)){ //받아온 no값이 일치하는 list
				//log.info("no값 추출-->"+list.get(i).getNo());
				//resultList에 상세요소들 추가
				resultList.add(list.get(i).getTitle());
				resultList.add(list.get(i).getCommonCd());
				resultList.add(list.get(i).getBatchId());
				resultList.add(list.get(i).getCall_URL());
				resultList.add(list.get(i).getJob_Schedule());
				resultList.add(list.get(i).getERPuseYN());
				resultList.add(list.get(i).getDBuseYN());
				resultList.add(list.get(i).getInterfaceID());
				resultList.add(list.get(i).getTasklet());
				resultList.add(list.get(i).getSummary());
				resultList.add(list.get(i).getDetail());
				resultList.add(list.get(i).getRemark());
			}
		}
		return resultList;
	}
	
}
