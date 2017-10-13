/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="table")
public class TableController {
	
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
	@RequestMapping(value="toList")
	public ModelAndView toList(){
		
		ModelAndView modelAndView = new ModelAndView();
		return null;		
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
	public Object getAllListData(){
		
		
		return null;
	}
}
