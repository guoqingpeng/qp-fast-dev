/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.util;

import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class MongoTableUtil {
	
	//系统中所有mogo表的最大的数据id
	private  static HashMap<String, Integer> currentDataIdMap = new HashMap<String, Integer>();
	
	/**
	 * 
	 *版本：
	 *功能描述：返创建下一个连接的数据id
	 *注意这个地方的并发性，所以这里制作成一个同步的方法
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：4:43:40 PM
	 *作者: GUO-QP
	 */
	public  static synchronized int getNextTableId(String collectionName){
		
		Integer currentId = currentDataIdMap.get(collectionName);
		/*判断currentTableId是不是null
		*如果是-1说明系统是初次启动，或者重新启动，
		*这个时候我们需要把mongo中table表的最大的id读出来，并且保存到全局维护的变量，并返回
		*以后就从这个地方取当前值加1返回
		*/
		if (currentId == null) {
			 currentId = findMaxIdInTable(collectionName) + 1;
		 }else {
			 currentId = currentId + 1;
		}
		currentDataIdMap.put(collectionName, currentId);
		return currentId;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：返回对应表中最大的数据id
	 *参数说明：@param collectionName
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：11:40:36 AM
	 *作者: GUO-QP
	 */
	public static Integer findMaxIdInTable(String collectionName) {
		int maxId = 0;
		try {
			Mongo client = new Mongo("192.168.6.190:27017");
			
			DB database = client.getDB("gqptest");
			DBCollection collection = database.getCollection(collectionName);

			BasicDBObject query = new BasicDBObject();
			
			BasicDBObject projection = new BasicDBObject();
			projection.put("dataId", 1.0);

			BasicDBObject sort = new BasicDBObject();
			sort.put("dataId", -1.0);

			int limit = 1;
			
			DBCursor cursor = collection.find(query, projection).sort(sort).limit(limit);
			try {
				while (cursor.hasNext()) {
					BasicDBObject document = (BasicDBObject) cursor.next();
					maxId  = (Integer) document.get("dataId");
				}
			} finally {
				cursor.close();
			}
		} catch (MongoException e) {
		}
		return maxId;
	}
}
