package com.tianfu.domain;

public enum EquipWorkState 
{
	WORKING("working"),
	END("ended"),
	BROKEN("broken");	
	
	private String desc;
	
	EquipWorkState(String name)
	{
		this.desc = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
