package com.example.spring.product;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.user.UserTestController;

@Service
public class ProductService {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	@Autowired
	ProductDAO productDAO;

	public List<ProductDTO> productList(List<String> searchOption, List<String> priceSearchOptionCD) throws Exception{
		
		LOGGER.info("productService---");
		
		return productDAO.productList(searchOption, priceSearchOptionCD);
	}

}
