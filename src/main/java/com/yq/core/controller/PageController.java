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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController extends BaseController{
	
	/**
	 * 
	 *版本：
	 *功能描述：跳转到页面生成页面
	 *参数说明：@param tableId
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：2:51:03 PM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="pageSet")
	public ModelAndView toPageSet(int tableId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tableId", tableId);
		modelAndView.setViewName("page/pageSet");
		return modelAndView;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：保存一个页面添加
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:52:34 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="pageAdd")
	public String pageAdd(){
		
		System.out.println("sfdsfds");
		return "数据保存成功";
	}

}
