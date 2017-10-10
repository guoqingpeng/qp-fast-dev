/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yq.core.dao.MenuDao;

@Controller
public class MenuController {
	
	@Autowired
	MenuDao menuDao;
	/**
	 *版本：
	 *功能描述：
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：9:17:42 AM
	 *作者: GUO-QP
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="menulist")
	public ModelAndView  menulist(){
		ModelAndView modelAndView = new ModelAndView();
		try {
			HashMap menuHashMap = menuDao.getMenu(1);
			System.out.println(menuHashMap.get("user_login"));
			modelAndView.addObject("menu", menuHashMap);
			modelAndView.setViewName("menu/menus");
		} catch (Exception e) {
			System.out.println("取数异常");
		}
		return modelAndView;
	}

}
