package com.tianfu.domain;

public class PageInfo 
{
	Integer page_curr;
	Integer page_size;
	Integer page_num;
	Integer obj_cursor_start;

	public Integer getPage_curr() {
		return page_curr;
	}
	public void setPage_curr(Integer page_curr) {
		this.page_curr = page_curr;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	@Override
	public String toString() {
		return "PageInfo [page_curr=" + page_curr + ", page_size=" + page_size + "]";
	}
	
	public Integer getObj_cursor_start() {
		return obj_cursor_start;
	}
	public void setObj_cursor_start(Integer obj_cursor_start) {
		this.obj_cursor_start = obj_cursor_start;
	}
	public PageInfo() {
		super();
	}
	public Integer getPage_num() {
		return page_num;
	}
	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}
	
}
