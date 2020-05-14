package com.example.spring.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class ProjectService {

	//BatchInfoFile임시변수
	private String no = "";
	private String commonCd = "";
	private String JobName = "";
	private String day = "";
	private String hour = "";
	private String minute = "";
	private String Tasklet = "";
	private String title = "";
	private String batchId = "";
	private String Call_URL = ""; //call url
	private String Job_Schedule = ""; //Job_Schedule
	private String ERPuseYN = "";//erp 연동여부
	private String DBuseYN = ""; //중계 db연동여부
	private String interfaceID = "";
	private String summary = "";
	private String detail = "";
	private String remark = "";

	private String first_str = null; //xml의 첫줄을 담을 임시변수
	private String str = null; //xml의 데이터를 담을 임시변수
	
	private static String sub_nValue = "값없음"; //null일경우 대체값
	
	//정렬사용 변수
	private String sort_value = ""; //정렬값 임시변수
	private String asc_value = ""; //오름/내림차순 임시변수
	
	private String commonCdTitle = "commonCd";
	private String JobNameTitle = "JobName";
	private String dayTitle = "day";
	private String hourTitle = "hour";
	
	private String ascTitle = "ascSort";
	private String descTitle = "descSort";
	private int i =0;
			
	private BatchInfoFile fileList = null;
	private List<BatchInfoFile> list = null;
	
	private Comparator<BatchInfoFile> comparatorValue = null; //내림차순 객체변수
//	private Comparator<BatchInfoFile> comparatorValue2 = null; //내림차순 객체변수
	private Comparator<BatchInfoFile> comparatorResult = null; //내림차순 객체변수
	private Comparator<BatchInfoFile> comparatorResult2 = null; //내림차순 객체변수
	
	//파일관련 변수
	File inputFile = null;
	File outFile = null;
	FileInputStream inputStream = null;
	FileOutputStream outputStream = null;
	BufferedReader br = null;
	BufferedWriter bw = null;

	public List<BatchInfoFile> xmlGetData(String keyword, String searchOption, List<String> search_sort_list,
			List<String> search_asc_list) throws Exception{
		//파일 읽기
		fileInfoRead();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setIgnoringElementContentWhitespace(true);//공백무시
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		Document doc = dBuilder.parse("c:\\file2.xml"); //파싱  -> xml에서 null이 나오는 부분
		doc.getDocumentElement().normalize();
		
		list = new ArrayList<BatchInfoFile>();
		NodeList nList = doc.getElementsByTagName("productBatch");
		for (int temp = 0; temp < nList.getLength(); temp++) {		
			Node nNode = nList.item(temp);
			
			fileList = new BatchInfoFile();

			elementValueGet(nNode);
			
			if(!keyword.equals("")){ //keyword가 있으면
				if(searchOption.equals("JobName")){ //searchOption이 JobName이면
					if(fileList.getJobName().contains(keyword)) list.add(fileList); //list에 추가
				}
				else{ //searchOption이 공통코드이면
					if(fileList.getCommonCd().contains(keyword)) list.add(fileList); //list에 추가
				}
			}//keyword
			else{//검색 안했을시 모두 포함
				list.add(fileList); //list에 추가
			}
		}//for문
		
		//정렬
	   if(search_sort_list != null) { //정렬기준이 있다면
		   for(i= 0; i<search_sort_list.size(); i++) {
			   sort_value = search_sort_list.get(i);
			   System.out.println("sort_value--------->"+sort_value);
			   asc_value = search_asc_list.get(i);
			   System.out.println("asc_value-------->"+asc_value);
			   
			   if(i == 0) {
				   returnSortValue(sort_value, list, fileList);
				   
				   if(search_asc_list.get(0).equals(ascTitle))
					   comparatorResult = Comparator.comparing(BatchInfoFile::getTemp1);
				   else comparatorResult = comparatorResult.reversed();
			   }
			   else {//2개이상이라면
				   comparatorValue = sortDivision(sort_value, asc_value, i);
//				   comparatorResult2 = comparatorResult.thenComparing(comparatorValue2);
				   if(i == 1) {
					   comparatorResult2 = comparatorResult.thenComparing(comparatorValue);
				   }
				   else {
					   comparatorResult2 = comparatorResult2.thenComparing(comparatorValue);
				   }
			   }
		   }//for문
		   
		   list.sort(comparatorResult2);
	   }
	   for(int i=0; i<list.size(); i++) {
		   System.out.println("i----------->"+i);
		   System.out.println("list=============>"+list.get(i).commonCd+"----"+list.get(i).JobName+"----"+list.get(i).temp1);
	   }
	   return list;
	} //xmlGetData
	
	//xml파일읽기
	public void fileInfoRead() throws Exception{
		try {
			inputFile = new File("c:\\file.xml");
			outFile = new File("c:\\file2.xml");

			inputStream = new FileInputStream(inputFile);
			outputStream = new FileOutputStream(outFile);
			br = new BufferedReader(new InputStreamReader(inputStream));
			bw = new BufferedWriter(new OutputStreamWriter(outputStream));

			//xml 첫줄은 그대로
			first_str = br.readLine();
			bw.write(first_str + "\r\n");
			
			while ((str = br.readLine()) != null) {
				str = replaceAllXmlStr(str); //전체 xml에서 특수문자 제거
				str = str.replaceFirst("&lt;", "<"); //처음위치 < 원래대로
				str = str.replaceFirst("&gt;", ">"); //처음위치 > 원래대로
				str = replaceLast(str, "&lt;", "<"); //마지막위치 < 원래대로
				str = replaceLast(str, "&gt;", ">"); //마지막위치 > 원래대로
				bw.write(str + "\r\n");
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
			br.close();
		}
	}
	
	//특수문자 전체 치환
	public String replaceAllXmlStr(String str) {
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\'", "&apos;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}

	//마지막위치 특수문자 원래대로
	public String replaceLast(String str, String toReplace, String replacement) {
		int pos = str.lastIndexOf(toReplace);
		if (pos > -1) {
			return str.substring(0, pos) + replacement + str.substring(pos + toReplace.length(), str.length());
		} else {
			return str;
		}
	}
		
	//xml데이터 BatchInfoFile에 set
	public void elementValueGet(Node nNode) {
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			no = getTagValue("no", eElement);
			commonCd = getTagValue("commonCd", eElement);
			JobName = getTagValue("JobName", eElement);
			day = getTagValue("day", eElement);
			hour = getTagValue("hour", eElement);
			minute = getTagValue("minute", eElement);
			Tasklet = getTagValue("Tasklet", eElement);
			title = getTagValue("title", eElement);
			batchId = getTagValue("batchId", eElement);
			Call_URL = getTagValue("Call_URL", eElement);
			Job_Schedule = getTagValue("Job_Schedule", eElement);
			ERPuseYN = getTagValue("ERPuseYN", eElement);
			DBuseYN = getTagValue("DBuseYN", eElement);
			interfaceID = getTagValue("interfaceID", eElement);
			summary = getTagValue("summary", eElement);
			detail = getTagValue("detail", eElement);
			remark = getTagValue("remark", eElement);

			fileList.setNo(no);
			fileList.setCommonCd(commonCd);
			fileList.setJobName(JobName);
			fileList.setDay(day);
			fileList.setHour(hour);
			fileList.setMinute(minute);
			fileList.setTasklet(Tasklet);
			fileList.setTitle(title);
			fileList.setBatchId(batchId);
			fileList.setCall_URL(Call_URL);
			fileList.setJob_Schedule(Job_Schedule);
			fileList.setERPuseYN(ERPuseYN);
			fileList.setDBuseYN(DBuseYN);
			fileList.setInterfaceID(interfaceID);
			fileList.setSummary(summary);
			fileList.setDetail(detail);
			fileList.setRemark(remark);
		}
	}
	
	//값 추출
	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if(ObjectUtils.isEmpty(nValue)){ //값없을시 임의값 넣어줌
			return sub_nValue;
		}
		return nValue.getNodeValue();
	}
	
	//첫번째 값 list temp1에 저장
	public void returnSortValue(String sort_value, List<BatchInfoFile> list, BatchInfoFile fileList) { 
		String temp_value = "";
		for(int j=0; j<list.size(); j++) {
	    	if(sort_value.equals(commonCdTitle)) {
	    		temp_value = list.get(j).getCommonCd();
	    	}	
	    	else if(sort_value.equals(JobNameTitle)) {
	    		temp_value = list.get(j).getJobName();
	    	}
	    	else if(sort_value.equals(dayTitle)) {
	    		temp_value = list.get(j).getDay();
	    	}
	    	else if(sort_value.equals(hourTitle)) {
	    		temp_value = list.get(j).getHour();
	    	}
	    	else {//분
	    		temp_value = list.get(j).getMinute();
	    	}
	    	System.out.println("temp_value----------->"+temp_value);
	    	list.get(j).setTemp1(temp_value);
		}
		System.out.println("list.size()--------->"+list.size());
	}

	//각각 정렬 옵션 오름/내림차순별로 저장
	public Comparator<BatchInfoFile> sortDivision(String sort_value, String asc_value, int i) { //i가 1이상 값 오름차순
		System.out.println("asc_value------------->"+asc_value);
		if(sort_value.equals(commonCdTitle)) comparatorValue = Comparator.comparing(BatchInfoFile::getCommonCd);
		else if(sort_value.equals(JobNameTitle)) comparatorValue = Comparator.comparing(BatchInfoFile::getJobName);
		else if(sort_value.equals(dayTitle)) comparatorValue = Comparator.comparing(BatchInfoFile::getDay);
		else if(sort_value.equals(hourTitle)) comparatorValue = Comparator.comparing(BatchInfoFile::getHour);
		else comparatorValue = Comparator.comparing(BatchInfoFile::getMinute);
		
		if(asc_value.equals(descTitle)) {
			comparatorValue = comparatorValue.reversed();
		}
		return comparatorValue;
	}
	
}
