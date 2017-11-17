/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.dao;

import com.yq.core.entity.Table;


public interface TableDao {
	
	/**
	 * 
	 *版本：
	 *功能描述：在数据库中创建一个表格
	 *参数说明：@param table
	 *返回值说明：
	 *更新日期：3:39:33 PM
	 *作者: GUO-QP
	 */
	public void createTabe(Table table);
	
	/**
	 * 
	 *版本：
	 *功能描述：从数据库中删除表格
	 *参数说明：@param id
	 *返回值说明：
	 *更新日期：3:41:23 PM
	 *作者: GUO-QP
	 */
	public void DestoryTable(int id);
	
}
