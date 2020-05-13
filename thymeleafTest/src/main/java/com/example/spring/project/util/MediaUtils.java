package com.example.spring.project.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

//파일의 타입이 이미지인지 구별해 주는 메서드
public class MediaUtils {

	private static Map<String, MediaType> mediaTypeMap;
	//자동로딩
	//meidaTypeMap에 이미지 확장자명에 따른 MINEType저장
	static {
		mediaTypeMap = new HashMap<>();
		mediaTypeMap.put("JPG",MediaType.IMAGE_JPEG);
		mediaTypeMap.put("GIF",MediaType.IMAGE_GIF);
		mediaTypeMap.put("PNG",MediaType.IMAGE_PNG);
	}
	
	//파일 타입
	public static MediaType getMediaType(String type) {
		 // 이미지 MINEType 꺼내서 반환, 이미지 파일이 아닐 경우 null 반환
		return mediaTypeMap.get(type.toUpperCase());
	}
}
