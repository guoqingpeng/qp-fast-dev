/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.controller;

import java.io.UnsupportedEncodingException;
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
	
	@RequestMapping(value="menuSet")
	public ModelAndView  menuSet(){
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("menu/menus");
		return modelAndView;
	}
	
	
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
	public ModelAndView  menulist(int pid){
		ModelAndView modelAndView = new ModelAndView();
			//menuFromPid
			if (pid != 0) {
				modelAndView.addObject("menuData", "menuFromPid.do?pid="+pid);
				modelAndView.setViewName("menu/menuframe2");
			}else {
				modelAndView.addObject("menuData", "menuData.do");
				modelAndView.setViewName("menu/menuframe");
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
	 * @throws UnsupportedEncodingException 
	 * @throws JsonProcessingException 
	 */
	@ResponseBody
	@RequestMapping(value="menuData")
	public  String menuItems(){
		List<TreeObj> allMenus = menuDao.getAllMenus();
		String jsonMenus =JSONArray.toJSONString(allMenus);
		System.out.println(jsonMenus);
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
	 * @throws JsonProcessingException 
	 */
	@ResponseBody
	@RequestMapping(value="menuFromPid")
	public  String getMenusByPid(int pid){
			List<TreeObj> allMenus = menuDao.getSubMenus(pid);
			String jsonMenus =JSONArray.toJSONString(allMenus);
			System.out.println((JSON) JSON.toJSON(allMenus));
			return jsonMenus;
	}
}
