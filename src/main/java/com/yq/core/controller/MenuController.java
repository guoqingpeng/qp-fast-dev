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
import com.yq.core.common.TreeObj;
import com.yq.core.dao.MenuDao;

@Controller
public class MenuController {
	
	@Autowired
	private MenuDao menuDao;
	
	/**
	 *版本：
	 *功能描述：
	 *获取所有的菜单的列表数据
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：9:17:42 AM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="menulist")
	public ModelAndView  menulist(){
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.setViewName("menu/menus");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.setViewName("404");
		}
		return modelAndView;
	}
	/**
	 * 
	 *版本：
	 *功能描述：ajax方式返回所有的数据
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：11:58:03 AM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="menuData")
	public String menuItems(){
		List<TreeObj> allMenus = menuDao.getAllMenus();
		String jsonMenus = JSONArray.toJSONString(allMenus);
		return jsonMenus;
	}
	
}
