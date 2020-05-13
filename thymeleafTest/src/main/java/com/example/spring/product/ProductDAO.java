package com.example.spring.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring.user.UserTestController;

import reactor.util.function.Tuple2;

@Repository
public class ProductDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession; 

	public List<ProductDTO> productList(List<String> searchOption, List<String> priceSearchOptionCD) throws Exception{
		
		LOGGER.info("productDAO---");
//		LOGGER.info("searchOption--->"+searchOption.toString());
//		LOGGER.info("priceSearchOptionCD--->"+priceSearchOptionCD.toString());
			
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("searchOption", searchOption);
		map.put("priceSearchOptionCD", priceSearchOptionCD);
//		
		if(searchOption != null && priceSearchOptionCD != null) {
			LOGGER.info("log-map-searchOption---->"+map.get("searchOption"));
			LOGGER.info("log-map-priceSearchOptionCD---->"+map.get("priceSearchOptionCD"));
		}
		
		return sqlSession.selectList("com.example.spring.product.ProductDAO.selectProductList", map);
	}

}
