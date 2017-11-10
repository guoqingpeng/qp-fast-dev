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

import com.alibaba.fastjson.JSONArray;
import com.yq.core.common.TreeObj;
import com.yq.core.dao.MenuDao;


@Controller
public class TreeController extends BaseController{
	
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
	 * @throws JsonProcessingException 
	 */
	@ResponseBody
	@RequestMapping(value="treeData")
	public String treeMain(){
		List<TreeObj> allMenus = menuDao.getAllMenus();
		String  menus = JSONArray.toJSONString(allMenus);
		return menus;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：拖动栏目时修改pid
	 *参数说明：@param id
	 *参数说明：@param pid
	 *返回值说明：
	 *更新日期：4:30:40 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="changTreeParent")
	public String changTreeParent(int id,int pid){
		try {
			menuDao.updateMenu(id, pid);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
}
