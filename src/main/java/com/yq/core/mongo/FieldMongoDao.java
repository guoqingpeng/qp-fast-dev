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

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yq.core.entity.Field;


@Repository
public class FieldMongoDao extends MongoDaoBase<Field>{
	
	/**
	 * 
	 *版本：
	 *功能描述：查找出某个对象下的所有的字段
	 *参数说明：@param tableId
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：5:43:21 PM
	 *作者: GUO-QP
	 */
	public List<Field> findFieldsOfTable(int tableId){
		
		Query query =new Query(
				Criteria.
				where("tableId").
				is(tableId));
		
		List<Field> fields = mongoTemplate.find(query, Field.class);
		return fields;
	}

}
