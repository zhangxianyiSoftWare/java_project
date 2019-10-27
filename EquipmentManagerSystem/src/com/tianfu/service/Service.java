package com.tianfu.service;

import java.util.List;

import com.tianfu.domain.AsyncRefreshRequest;
import com.tianfu.domain.Equipment;
import com.tianfu.domain.PageBean;
import com.tianfu.domain.PageInfo;

public interface Service 
{
	/*
     * login 
     * param user
     * */
    public Object login(Object obj);
    /*
     * regiest
     * param user
     * */
    public Object regiest(Object obj);
	public Integer update(Object obj);
	public Integer delete(Object obj);

	
	public PageBean<Equipment> findAll(PageInfo info);
	public PageBean<Equipment> findByPageInfo(PageInfo page_info);
	public PageBean<Equipment> findByPageInfo(AsyncRefreshRequest asyncRequest);
}
