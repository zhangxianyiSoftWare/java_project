package com.tianfu.service;

import java.util.List;

import com.tianfu.domain.Equipment;

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


	public List<Equipment> findAll();
}
