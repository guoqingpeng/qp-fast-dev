/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.entity;


public class Field {
	private int dataId;
	private String cnName;
	private String enName;
	private int belongTable;
	private String tableName;
	private int type;
	private String rule;
	
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public int getBelongTable() {
		return belongTable;
	}
	public void setBelongTable(int belongTable) {
		this.belongTable = belongTable;
	}
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	//这里必须写默认的构造函数，否则fastjson是无法解析的，mongodbtemplate也是无法解析的
	public Field(){
		
	}
	
	public Field(int dataId,String cnName,String enName,int type,int belongTable){
		this.dataId = dataId;
		this.cnName = cnName;
		this.enName = enName;
		this.type = type;
		this.belongTable = belongTable;
	}
	
	public Field(int dataId,String cnName,String enName,int type,int belongTable,String tableName){
		this.dataId = dataId;
		this.cnName = cnName;
		this.enName = enName;
		this.type = type;
		this.belongTable = belongTable;
		this.tableName= tableName;
	}
}
