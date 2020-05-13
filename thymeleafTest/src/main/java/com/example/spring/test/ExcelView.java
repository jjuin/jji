//package com.example.spring.test;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.core.io.ClassPathResource;
//
//public class ExcelView extends AbstractExcelView{
//	@Override 
//	protected void buildExcelDocument(Map<String, Object> modal, 
//										HSSFWorkbook workbook, 
//										HttpServletRequest request, 
//										HttpServletResponse response) throws Exception {
//		response.setHeader("Content-Type", "application/octet-stream"); 
//		response.setHeader("Content-Disposition", "attachment; filename=excel99.xls"); 
//		OutputStream os = null; 
//		InputStream is = null; 
//		
//		try { 
//			// 엑셀 템플릿 파일이 존재하는 위치 (classpath 하위) 
//			is = new ClassPathResource("/excel.xls").getInputStream(); 
//			os = response.getOutputStream(); 
//			XLSTransformer transformer = new XLSTransformer(); 
//			Workbook excel = transformer.transformXLS(is, modal); 
//			excel.write(os); os.flush(); 
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e.getMessage()); 
//			} 
//		finally {
//				if(os != null) 
//					try {
//					os.close(); 
//					} catch (IOException e) { } 
//				if(is != null) 
//					try {
//						is.close(); 
//						} catch (IOException e) { } 
//		} 
//		
//		
//	}
//
//}
