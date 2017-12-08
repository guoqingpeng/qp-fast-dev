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
import com.yq.core.dao.DataDictionaryDao;

@Controller
public class DataDictionaryController extends BaseController{
	
	@Autowired
	private DataDictionaryDao dataDictionaryDao;
	
	/**
	 * 
	 *版本：
	 *功能描述：跳转到设置页面
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：10:25:32 AM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="dataDictionarySet")
	public ModelAndView  dataDictionarySet(){
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("dataDictionary/dataDictionarys");
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
	@RequestMapping(value="dataDictionaryData")
	public  String dataDictionaryItems(){
		List<TreeObj> alldataDictionarys = dataDictionaryDao.getAllDataDictionarys();
		String jsondataDictionarys =JSONArray.toJSONString(alldataDictionarys);
		return jsondataDictionarys;
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
	@RequestMapping(value="dataDictionaryFromPid")
	public  String getdataDictionarysByPid(int pid){
		List<TreeObj> alldataDictionarys = dataDictionaryDao.getSubDataDictionarys(pid);
		String jsondataDictionarys =JSONArray.toJSONString(alldataDictionarys);
		return jsondataDictionarys;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：跳转到菜单添加页面
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：1:54:36 PM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="toDataDictionaryAddPage")
	public String todataDictionaryAddPage(){
		return "dataDictionary/dataDictionaryAddPage";
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
	@RequestMapping(value="dataDictionaryAdd")
	public String dataDictionaryAdd(String name,int pid){
		int i = dataDictionaryDao.insertDataDictionary(name, pid);
		System.out.println("-----------------"+i);
		HashMap<String , String> aMap= new HashMap<String, String>();
		aMap.put("msg", "ok");
		String string = JSON.toJSONString(aMap);
		return  string;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *参数说明：@param id
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:57:56 PM
	 *作者: GUO-QP
	 */
	@ResponseBody
	@RequestMapping(value="dataDictionaryDelete")
	public String deletedataDictionary(int id){
		String msg = "ok";
		int childNum = dataDictionaryDao.getChildrenNumByPid(id);
		if (childNum > 0) {
			msg = "类型下还有子元素，不能删除";
		}else {
			dataDictionaryDao.deleteDataDictionary(id);
		}
		return msg;
		
	}
}
