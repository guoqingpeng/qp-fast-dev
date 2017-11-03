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
import com.yq.core.common.TreeDataUtil;
import com.yq.core.common.TreeObj;
import com.yq.core.dao.MenuDao;

@Controller
public class MainPageController {
	
	@Autowired
	private MenuDao menuDao;
	
	/**
	 *版本：
	 *功能描述：
	 *跳转到首页生成所有菜单
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：9:17:42 AM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="mainPage")
	public ModelAndView toMainPage(){
		ModelAndView modelAndView = new ModelAndView();
		try {
			//获取顶级菜单，父级菜单为0,递归获取子菜单
			List<TreeObj> topMenusList = menuDao.getTopMenus();
			//非顶级菜单
			List<TreeObj> notTopMenusList = menuDao.getNotTopSubMenus();
			topMenusList = TreeDataUtil.makeTreeData(topMenusList, notTopMenusList);
			modelAndView.addObject("menu",JSONArray.toJSONString(topMenusList));
			modelAndView.setViewName("main");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.setViewName("404");
		}
		return modelAndView;
	}

}
