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
		
		Query query = new Query(
				Criteria.
				where("belongTable").
				is(tableId).andOperator(Criteria.where("enName").ne("dataId")));
		
		//query.sort().on("dataId", Order.DESCENDING);
		List<Field> fields = mongoTemplate.find(query, Field.class);
		return fields;
	}
	
	
	/**
	 * 
	 *版本：
	 *功能描述：批量注册字段
	 *参数说明：@param fields
	 *返回值说明：
	 *更新日期：11:34:59 AM
	 *作者: GUO-QP
	 */
	public void batchInsertFields(List<Field> fields){
		
		mongoTemplate.insert(fields, Field.class);
		
	}
	
	
	/**
	 * 
	 *版本：
	 *功能描述：删除对象下面所有的字段
	 *参数说明：
	 *返回值说明：
	 *更新日期：11:48:16 AM
	 *作者: GUO-QP
	 */
	public void batchDeleteFields(int tableId){
		
		Query query =new Query(
				Criteria.
				where("belongTable").
				is(tableId));
		
		mongoTemplate.remove(query, Field.class);
	}
	
	
	/**
	 * 
	 *版本：
	 *功能描述：批量删除指定的字段
	 *参数说明：@param fields
	 *返回值说明：
	 *更新日期：1:51:18 PM
	 *作者: GUO-QP
	 */
	public void batchDeleteSomeFields(List<Field> fields){
		
		mongoTemplate.remove(fields);
		
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：删除指定的字段
	 *参数说明：@param field
	 *返回值说明：
	 *更新日期：1:56:32 PM
	 *作者: GUO-QP
	 */
	public void deleteField(Field field){
		mongoTemplate.remove(
				new Query(
				    Criteria.where("dataId").is(field.getDataId())		
				),Field.class);
		
	}

}
