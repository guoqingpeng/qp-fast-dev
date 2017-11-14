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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yq.core.common.TreeObj;
import com.yq.core.dao.MenuDao;

@Controller
public class MenuController extends BaseController{
	@Autowired
	private MenuDao menuDao;
	
	/**
	 * 
	 *版本：
	 *功能描述：跳转到栏目设置页面
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：10:25:32 AM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="menuSet")
	public ModelAndView  menuSet(){
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("menu/menus");
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
	public  String menuItems(){
		List<TreeObj> allMenus = menuDao.getAllMenus();
		String jsonMenus =JSONArray.toJSONString(allMenus);
		return jsonMenus;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：根据pid获取子菜单
	 *参数说明：@param pid
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：5:07:55 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="menuFromPid")
	public  String getMenusByPid(int pid){
		List<TreeObj> allMenus = menuDao.getSubMenus(pid);
		String jsonMenus =JSONArray.toJSONString(allMenus);
		return jsonMenus;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：添加一个栏目
	 *参数说明：@param name
	 *参数说明：@param pid
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：5:35:45 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="menuAdd")
	public String menuAdd(String name,int pid){
		menuDao.insertMenu(name, pid);
		HashMap<String , String> aMap= new HashMap<String, String>();
		aMap.put("msg", "ok");
		String string = JSON.toJSONString(aMap);
		System.out.println(string);
		return  string;
	}
}
