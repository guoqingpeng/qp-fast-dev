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
import com.yq.core.common.TreeObj;
import com.yq.core.dao.MenuDao;

@Controller
public class TreeController {
	
	@Autowired
	private MenuDao menuDao;
	
	/**
	 * 
	 *版本：
	 *功能描述：加载树结构的页面
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:36:54 PM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="treeData")
	public ModelAndView treeMain(){
		ModelAndView modelAndView = new ModelAndView();
		List<TreeObj> allMenus = menuDao.getAllMenus();
		String  menus = JSONArray.toJSONString(allMenus);
		modelAndView.addObject("treeData",menus);
		modelAndView.setViewName("common/tree");
		System.out.println("加载了Ztree数据了"+menus);
		return modelAndView;
	}
}
