package com.tianfu.entity;

import java.util.Date;

public class Equipment 
{
	private String name;
	private Date  login_time;
	private Date  logout_time;
	private String state;
	private String done_thing;
	private String image_path;
	private String category;
	public Equipment(String name, Date login_time, Date logout_time, String state,
			String done_thing) {
		super();
		this.name = name;
		this.login_time = login_time;
		this.logout_time = logout_time;
		this.state = state;
		this.done_thing = done_thing;
	}
	
	public Equipment() {
		super();
		this.name = null;
		this.login_time = null;
		this.logout_time = null;
		this.state = null;
		this.done_thing = null;
		this.image_path = null;
	}

	
	

	@Override
	public String toString() {
		return "Equipment [name=" + name + ", login_time=" + login_time + ", logout_time=" + logout_time + ", state="
				+ state + ", done_thing=" + done_thing + ", image_path=" + image_path + ", category=" + category + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public Date getLogout_time() {
		return logout_time;
	}

	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDone_thing() {
		return done_thing;
	}

	public void setDone_thing(String done_thing) {
		this.done_thing = done_thing;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	public boolean isEmpty() 
	{
		return name == null;
	}
}
