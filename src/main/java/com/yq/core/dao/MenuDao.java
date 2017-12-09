/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.yq.core.common.TreeObj;


@MapperScan
public interface MenuDao {
	
	/**
	 * 
	 *版本：
	 *功能描述：获取根菜单
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:12:35 PM
	 *作者: GUO-QP
	 */
	public List<TreeObj> getTopMenus();
	
	/**
	 * 
	 *版本：
	 *功能描述：获取下级子菜单
	 *参数说明：@param id
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:13:03 PM
	 *作者: GUO-QP
	 */
	public List<TreeObj> getSubMenus(int id);
	
	/**
	 * 
	 *版本：
	 *功能描述：获取所有非顶级菜单
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:13:23 PM
	 *作者: GUO-QP
	 */
	public List<TreeObj> getNotTopSubMenus();
	
	/**
	 * 
	 *版本：
	 *功能描述：获取所有菜单项
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:13:45 PM
	 *作者: GUO-QP
	 */
	public List<TreeObj> getAllMenus();
	
	/**
	 * 
	 *版本：
	 *功能描述：更新菜单的排序位置
	 *参数说明：@param id
	 *参数说明：@param pid
	 *参数说明：@param position
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:14:00 PM
	 *作者: GUO-QP
	 */
	public int updateMenu(int id ,int pid,int position);
	
	/**
	 * 
	 *版本：
	 *功能描述：批量更新子菜单的排序
	 *参数说明：@param id
	 *参数说明：@param position
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：3:14:41 PM
	 *作者: GUO-QP
	 */
	public int updateMenuPositions(int id,int position);
	
	/**
	 * 
	 *版本：
	 *功能描述：添加一个栏目
	 *参数说明：@param name
	 *参数说明：@param pid
	 *返回值说明：
	 *更新日期：4:05:02 PM
	 *作者: GUO-QP
	 */
	public void insertMenu(String name,int pid);
	
	/**
	 * 
	 *版本：
	 *功能描述：删除一个栏目
	 *参数说明：@param id
	 *返回值说明：
	 *更新日期：4:05:47 PM
	 *作者: GUO-QP
	 */
	public void deleteMenu(int id);
	
	/**
	 * 
	 *版本：
	 *功能描述：获取父元素下的子元素
	 *参数说明：@param id
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：4:07:00 PM
	 *作者: GUO-QP
	 */
	public int getChildrenNumByPid(int id);
	
}
