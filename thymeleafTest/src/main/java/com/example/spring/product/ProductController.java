package com.example.spring.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.user.UserTestController;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	//상품메인 페이지
	@ResponseBody
	@RequestMapping(value="main", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView productMain(@RequestParam(value="searchOption", required=false) List<String> searchOption,
									@RequestParam(value="priceSearchOptionCD", required=false) List<String> priceSearchOptionCD) throws Exception{
//	@ResponseBody
//	@RequestMapping(value="main", method = {RequestMethod.POST, RequestMethod.GET})
//	public ModelAndView productMain(@ModelAttribute String[] searchOption,
//									@ModelAttribute String[] priceSearchOptionCD) throws Exception{
//	
		LOGGER.info("searchOption=>"+searchOption);
		LOGGER.info("priceSearchOptionCD=>"+priceSearchOptionCD);
		
		List<ProductDTO> productList = productService.productList(searchOption, priceSearchOptionCD);
		
		LOGGER.info("productLsit-log->"+productList.toString());
		
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("productList", productList);
		
		if(productList.isEmpty()) { 
			LOGGER.info("productList가 비어있음.");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("product/productMain");
			mav.addObject("productList", productList);
//			model.addAttribute("productList", productList);
			return mav;
		}
		
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("productList", productList);
//		map.put("searchOption", searchOption);
//		map.put("priceSearchOptionCD", priceSearchOptionCD);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("productList", productList);
		mav.setViewName("product/productMain");
//		model.addAttribute("productList", productList);
		
		return mav;
		
	}
	
	
	//신상품 조회
	@RequestMapping(value = "newProduct")
	public ModelAndView newProduct() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/productMain");
		return mav;
	}

}
