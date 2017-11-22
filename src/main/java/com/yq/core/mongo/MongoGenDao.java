/**
 *功能描述
 *当前版本
 *上一个版本
 *创建日期
 *修改日期
 *文件的描述
 */
package com.yq.core.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public abstract class MongoGenDao<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *参数说明：@param t
	 *返回值说明：
	 *更新日期：3:02:09 PM
	 *作者: GUO-QP
	 */
	public void save(T t) {
		Query query = new Query();
		Criteria criteria = Criteria.where("id").is("a");		
		this.mongoTemplate.save(t);
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *参数说明：@param mongoTemplate
	 *返回值说明：
	 *更新日期：3:02:16 PM
	 *作者: GUO-QP
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
