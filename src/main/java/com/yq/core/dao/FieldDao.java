/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.yq.core.entity.Field;


@MapperScan
public interface FieldDao {
	
	/**
	 * 
	 *版本：
	 *功能描述：给对象添加一个字段
	 *参数说明：@param field
	 *返回值说明：
	 *更新日期：11:11:32 AM
	 *作者: GUO-QP
	 */
	public void addField(Field field);
	
	/**
	 * 
	 *版本：
	 *功能描述：修改对象的 字段类型
	 *参数说明：@param field
	 *返回值说明：
	 *更新日期：11:11:57 AM
	 *作者: GUO-QP
	 */
	public void modifyField(Field field);
	
	/**
	 * 
	 *版本：
	 *功能描述：删除一个字段
	 *参数说明：@param field
	 *返回值说明：
	 *更新日期：11:12:21 AM
	 *作者: GUO-QP
	 */
	public void dropField(Field field);

}
