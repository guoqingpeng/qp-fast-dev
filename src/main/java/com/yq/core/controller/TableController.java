/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yq.core.entity.Table;
import com.yq.core.mongo.TableMongoDao;
import com.yq.core.util.MongoTableUtil;

@Controller
public class TableController {
	
	@Autowired
	TableMongoDao tableMongoDao;
	
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
		return null;
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
	@ResponseBody
	@RequestMapping(value="tableAdd")
	public String tableAdd(Table table){
		table.setDataId(MongoTableUtil.getNextTableId("table"));
		tableMongoDao.save(table);
		return "ok";
	}
}
