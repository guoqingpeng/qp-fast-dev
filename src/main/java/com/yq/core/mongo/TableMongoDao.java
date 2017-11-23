/**
 *功能描述
 *当前版本
 *上一个版本
 *创建日期
 *修改日期
 *文件的描述
 */
package com.yq.core.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yq.core.entity.Table;

@Repository
public class TableMongoDao extends MongoDaoBase<Table> {
	
	/**
	 * 
	 *版本：
	 *功能描述：从mongodb中获取所有的对象数据
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:57:07 PM
	 *作者: GUO-QP
	 */
	public List<Table> getAllTable(){
		
		List<Table> allTables = mongoTemplate.findAll(Table.class);
		
		return allTables;
	}

}
