/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yq.core.common.TreeObj;
import com.yq.core.dao.DataDictionaryDao;
import com.yq.core.dao.FieldDao;
import com.yq.core.entity.Field;
import com.yq.core.mongo.FieldMongoDao;
import com.yq.core.mongo.TableMongoDao;
import com.yq.core.util.MongoTableUtil;



@Controller
public class FieldController {
	
	@Autowired
	FieldMongoDao fieldMongoDao;
	
	@Autowired
	TableMongoDao tableMongoDao;
	
	@Autowired
	FieldDao fieldDao;
	
	@Autowired
	private DataDictionaryDao dataDictionaryDao;
	/**
	 * 
	 *版本：
	 *功能描述：跳转到字段设置列表页面
	 *参数说明：@param tableId
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:00:41 PM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="fields")
	public ModelAndView toFieldListPage(int tableId){
		ModelAndView modelAndView = new ModelAndView();
		List<TreeObj> dics = dataDictionaryDao.getTopDataDictionarys();
		modelAndView.addObject("cachedDictionary", JSONArray.toJSONString(dics));
		modelAndView.addObject("tableId", tableId);
		modelAndView.setViewName("field/fields");
		return modelAndView;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：获取当前对象下的所有的字段
	 *参数说明：@param tableId
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：4:53:17 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="fieldData")
	public String  getAllFieldsByTable(int tableId){
		
		return JSONArray.toJSONString(fieldMongoDao.findFieldsOfTable(tableId));
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：字段修改的变更
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：6:05:54 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="updateFields")
	public String updateFields(String fields){
		JSONObject jsonObject = JSON.parseObject(fields);
		List<Field> addfieldList = JSON.parseArray(jsonObject.getString("fieldAddList"), Field.class);
		//List<Field> fieldUpdateList = JSON.parseArray(jsonObject.getString("fieldUpdateList"), Field.class);
		List<Field> fieldDeleteList = JSON.parseArray(jsonObject.getString("fieldDeleteList"), Field.class);
		int tableId = jsonObject.getIntValue("tableId");
		String tableName = tableMongoDao.getTableNameById(tableId);
		
		for (Field field : addfieldList) {
			field.setDataId(MongoTableUtil.getNextTableId("field"));
			field.setBelongTable(tableId);
			field.setTableName(tableName);
			fieldDao.addField(field);
		}
		
		fieldMongoDao.batchInsertFields(addfieldList);
		
		
		for (Field field : fieldDeleteList) {
			field.setTableName(tableName);
			fieldDao.dropField(field);
			fieldMongoDao.deleteField(field);
		}
		
		return "ok";
	}
}