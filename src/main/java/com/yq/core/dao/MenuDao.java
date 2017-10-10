/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.dao;

import java.util.HashMap;

import org.mybatis.spring.annotation.MapperScan;


@MapperScan
public interface MenuDao {
	
	public HashMap getMenu(int id);

}
