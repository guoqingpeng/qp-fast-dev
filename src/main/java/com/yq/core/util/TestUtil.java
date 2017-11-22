/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestUtil {

	/**
	 *版本：
	 *功能描述：
	 *参数说明：@param args
	 *返回值说明：
	 *更新日期：1:08:47 PM
	 *作者: GUO-QP
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MongoClient mongoClient =new MongoClient("192.168.6.190",27017);
		MongoDatabase database =  mongoClient.getDatabase("gqptest");
		MongoCollection<Document> dCollection = database.getCollection("table");
		dCollection.insertOne(new Document("name","guoqigpeng"));
	}

}
