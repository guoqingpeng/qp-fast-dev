/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.common;

import java.util.List;

public class TreeObj {
	private int id;
	private int pid;
	private String url;
	private Boolean hasSubTreeobj;
	private int subMenuNum;
	private List<TreeObj> subTreeobjList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Boolean getHasSubTreeobj() {
		return hasSubTreeobj;
	}
	public void setHasSubTreeobj(Boolean hasSubTreeobj) {
		this.hasSubTreeobj = hasSubTreeobj;
	}
	public int getSubMenuNum() {
		return subMenuNum;
	}
	public void setSubMenuNum(int subMenuNum) {
		this.subMenuNum = subMenuNum;
	}
	public List<TreeObj> getSubTreeobjList() {
		return subTreeobjList;
	}
	public void setSubTreeobjList(List<TreeObj> subTreeobjList) {
		this.subTreeobjList = subTreeobjList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
}
