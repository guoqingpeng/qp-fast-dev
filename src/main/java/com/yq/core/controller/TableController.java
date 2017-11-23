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

import com.alibaba.fastjson.JSONArray;
import com.yq.core.dao.TableDao;
import com.yq.core.entity.Table;
import com.yq.core.mongo.TableMongoDao;
import com.yq.core.util.MongoTableUtil;

@Controller
public class TableController {
	
	@Autowired
	TableMongoDao tableMongoDao;
	
	@Autowired
	TableDao tableDao;
	/**
	 * 
	 *版本：
	 *功能描述：
	 *简单的跳转到表设置列表页面
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：11:34:03 AM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="tableSet")
	public ModelAndView toList(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("table/tables");
		return modelAndView;		
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *获取所有的列表数据
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：11:35:27 AM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="tableData")
	public String getAllListData(){
		List<Table> allTable = tableMongoDao.getAllTable();
		return JSONArray.toJSONString(allTable);
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：添加一个对象
	 *参数说明：@param table
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:14:03 PM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="tableAdd")
	public String tableAdd(Table table){
		
		int dataId = MongoTableUtil.getNextTableId("table");
		table.setDataId(dataId);
		//注册表
		tableMongoDao.save(table);
		//成功之后创建一个基本表包含一些基本字段
		tableDao.createTabe(table);
		//注册默认的字段
		return "table/tables";
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：删除一个对象
	 *参数说明：@param id
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：4:20:52 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="tableDelete")
	public String tableDelete(int id,String tableName){
		
		
		//在mongo中记录
		tableMongoDao.delete(id);
		//删除字段的记录
		
		//从数据库中删除表
		tableDao.destoryTable(tableName);
		
		//从数据库中删除字段
		
		return "ok";
	}
}
