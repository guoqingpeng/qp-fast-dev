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
	private String name;
	private int id;
	private int pid;
	private int position;
	private String url = "";
	private int urlType = 1;
	private Boolean hasSubTreeobj = false;
	private int subMenuNum = 0;
	private List<TreeObj> subTreeobjList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUrlType() {
		return urlType;
	}
	public void setUrlType(int urlType) {
		this.urlType = urlType;
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

}
