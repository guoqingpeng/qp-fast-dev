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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.yq.core.entity.Field;
import com.yq.core.mongo.FieldMongoDao;

@Controller
public class FieldController {
	
	@Autowired
	FieldMongoDao fieldMongoDao;
	
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
	@RequestMapping(value="fields")
	public ModelAndView getAllFieldsByTable(int tableId){
		
		ModelAndView modelAndView = new ModelAndView();
		List<Field> fieldsList = fieldMongoDao.findFieldsOfTable(tableId);
		modelAndView.setViewName("field/fields");
		modelAndView.addObject("data", JSONArray.toJSONString(fieldsList));
		return modelAndView;
	}

}
