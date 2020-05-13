package com.example.spring.board;

import java.util.Date;

public class BoardDTO {

	private String DD;
	private int RNUM;
	private int BNO;
	private int RE_PNO;
	private int RE_DEPTH;
	private int GROUP_ID;
	private int RE_SEQ;
	private String TITLE;
	private String CONTENT;
	private String WRITER;
	private int WRITER_KEY;
	private Date REGDATE;
	private Date UPDATEDATE;
	private int VIEWCNT;
	private String B_ISSHOW;
	private int REPLY_CNT;
	
	
	public String getDD() {
		return DD;
	}
	public void setDD(String dD) {
		DD = dD;
	}
	public int getRNUM() {
		return RNUM;
	}
	public void setRNUM(int rNUM) {
		RNUM = rNUM;
	}
	public int getBNO() {
		return BNO;
	}
	public void setBNO(int bNO) {
		BNO = bNO;
	}
	public int getRE_PNO() {
		return RE_PNO;
	}
	public void setRE_PNO(int rE_PNO) {
		RE_PNO = rE_PNO;
	}
	public int getRE_DEPTH() {
		return RE_DEPTH;
	}
	public void setRE_DEPTH(int rE_DEPTH) {
		RE_DEPTH = rE_DEPTH;
	}
	public int getGROUP_ID() {
		return GROUP_ID;
	}
	public void setGROUP_ID(int gROUP_ID) {
		GROUP_ID = gROUP_ID;
	}
	public int getRE_SEQ() {
		return RE_SEQ;
	}
	public void setRE_SEQ(int rE_SEQ) {
		RE_SEQ = rE_SEQ;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getWRITER() {
		return WRITER;
	}
	public void setWRITER(String wRITER) {
		WRITER = wRITER;
	}
	public int getWRITER_KEY() {
		return WRITER_KEY;
	}
	public void setWRITER_KEY(int wRITER_KEY) {
		WRITER_KEY = wRITER_KEY;
	}
	public Date getREGDATE() {
		return REGDATE;
	}
	public void setREGDATE(Date rEGDATE) {
		REGDATE = rEGDATE;
	}
	public Date getUPDATEDATE() {
		return UPDATEDATE;
	}
	public void setUPDATEDATE(Date uPDATEDATE) {
		UPDATEDATE = uPDATEDATE;
	}
	public int getVIEWCNT() {
		return VIEWCNT;
	}
	public void setVIEWCNT(int vIEWCNT) {
		VIEWCNT = vIEWCNT;
	}
	public String getB_ISSHOW() {
		return B_ISSHOW;
	}
	public void setB_ISSHOW(String b_ISSHOW) {
		B_ISSHOW = b_ISSHOW;
	}
	public int getREPLY_CNT() {
		return REPLY_CNT;
	}
	public void setREPLY_CNT(int rEPLY_CNT) {
		REPLY_CNT = rEPLY_CNT;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [DD=" + DD + ", RNUM=" + RNUM + ", BNO=" + BNO + ", RE_PNO=" + RE_PNO + ", RE_DEPTH="
				+ RE_DEPTH + ", GROUP_ID=" + GROUP_ID + ", RE_SEQ=" + RE_SEQ + ", TITLE=" + TITLE + ", CONTENT="
				+ CONTENT + ", WRITER=" + WRITER + ", WRITER_KEY=" + WRITER_KEY + ", REGDATE=" + REGDATE
				+ ", UPDATEDATE=" + UPDATEDATE + ", VIEWCNT=" + VIEWCNT + ", B_ISSHOW=" + B_ISSHOW + ", REPLY_CNT="
				+ REPLY_CNT + "]";
	}
	
	
}
