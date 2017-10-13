/**
 *功能描述
 *当前版本
 *上一个版本
 *创建日期
 *修改日期
 *文件的描述
 */
package com.yq.core.common;

import java.util.ArrayList;
import java.util.List;

public class TreeDataUtil {
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *----------------------------
	 *从一个父元素开始递归设置子元素的子元素
	 *----------------------------
	 *参数说明：@param topTreeObj
	 *参数说明：@param TreeObjs
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：10:11:58 AM
	 *作者: GUO-QP
	 */
	public static List<TreeObj> subTreeObjs(TreeObj topTreeObj, List<TreeObj> TreeObjs) {
		List<TreeObj> subTreeObjList = getSubTreeObjs(topTreeObj.getId(), TreeObjs);
		if (subTreeObjList.size() > 0) {
			topTreeObj.setHasSubTreeobj(true);
			topTreeObj.setSubTreeobjList(subTreeObjList);
			topTreeObj.setSubMenuNum(subTreeObjList.size());
			for (TreeObj treeObj : subTreeObjList) {
				subTreeObjs(treeObj, TreeObjs);
			}
		} else {
			topTreeObj.setHasSubTreeobj(false);
		}
		return subTreeObjList;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *
	 *-------------------------------------------
	 *根据pid从传入的集合中获取某个父元素下所有的子元素
	 *-------------------------------------------
	 *参数说明：@param id
	 *参数说明：@param TreeObjs
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：10:13:03 AM
	 *作者: GUO-QP
	 */
	public static List<TreeObj> getSubTreeObjs(int id, List<TreeObj> TreeObjs) {
		List<TreeObj> subTreeObjList = new ArrayList<TreeObj>();
		for (TreeObj treeObj : TreeObjs) {
			if (treeObj.getPid() == id) {
				subTreeObjList.add(treeObj);
			}
		}
		return subTreeObjList;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *----------------------------------
	 *构造整体树结构
	 *----------------------------------
	 *参数说明：@param topTreeObjsList
	 *参数说明：@param notTopTreeObjsList
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：10:18:56 AM
	 *作者: GUO-QP
	 */
	public static List<TreeObj> makeTreeData(List<TreeObj> topTreeObjsList,List<TreeObj> notTopTreeObjsList){
		for (TreeObj treeObj : topTreeObjsList) {
			subTreeObjs(treeObj,notTopTreeObjsList);
		}
		return topTreeObjsList;
	}
}
