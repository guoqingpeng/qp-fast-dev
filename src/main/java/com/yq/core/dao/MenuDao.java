/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.yq.core.common.TreeObj;


@MapperScan
public interface MenuDao {
	
	public List<TreeObj> getTopMenus();
	public List<TreeObj> getSubMenus(int id);
	public List<TreeObj> getNotTopSubMenus();
	
}
