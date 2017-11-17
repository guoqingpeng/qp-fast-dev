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
import org.springframework.stereotype.Repository;

@Repository
public class TestMongo {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * 
	 *版本：
	 *功能描述：测试
	 *参数说明：
	 *返回值说明：
	 *更新日期：12:36:23 PM
	 *作者: GUO-QP
	 */
	public void mongoinfo(){
		System.out.println("mogodb-------"+mongoTemplate.getDb());;
		//mongoTemplate.createCollection("myqld");
		//mongoTemplate.insert("{'a':'a'}", "myqld");
	}

}
