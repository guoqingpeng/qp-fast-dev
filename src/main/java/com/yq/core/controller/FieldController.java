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

import com.alibaba.fastjson.JSONArray;
import com.yq.core.mongo.FieldMongoDao;

@Controller
public class FieldController {
	
	@Autowired
	FieldMongoDao fieldMongoDao;
	
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
}
