/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.entity;

import com.yq.core.common.TreeObj;

public class DataDictionary extends TreeObj{
	
	public static final int FIXED_DATADICTIONARY = 0;
	public static final int SELFDEFIED_DATADICTIONARY = 1;
	
	private int key;
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}	
	
	public DataDictionary(){
		
	}
	
	public DataDictionary(int pid ,String name){
		super.setPid(pid);
		super.setName(name);
	}
}
