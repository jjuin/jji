package com.example.spring.product;

import java.util.List;

public class ProductDTO {

	private int prod_no;
	private String prod_name;
	private int prod_category_no;
	private String prod_category_nm;
	private String prod_price;
	
	private List<String> searchOption;
	private List<String> priceSearchOptionCD;
	
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_category_no() {
		return prod_category_no;
	}
	public void setProd_category_no(int prod_category_no) {
		this.prod_category_no = prod_category_no;
	}
	public String getProd_category_nm() {
		return prod_category_nm;
	}
	public void setProd_category_nm(String prod_category_nm) {
		this.prod_category_nm = prod_category_nm;
	}
	public String getProd_price() {
		return prod_price;
	}
	public void setProd_price(String prod_price) {
		this.prod_price = prod_price;
	}
	public List<String> getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(List<String> searchOption) {
		this.searchOption = searchOption;
	}
	public List<String> getPriceSearchOptionCD() {
		return priceSearchOptionCD;
	}
	public void setPriceSearchOptionCD(List<String> priceSearchOptionCD) {
		this.priceSearchOptionCD = priceSearchOptionCD;
	}
	@Override
	public String toString() {
		return "ProductDTO [prod_no=" + prod_no + ", prod_name=" + prod_name + ", prod_category_no=" + prod_category_no
				+ ", prod_category_nm=" + prod_category_nm + ", prod_price=" + prod_price + ", searchOption="
				+ searchOption + ", priceSearchOptionCD=" + priceSearchOptionCD + "]";
	}
	
}
