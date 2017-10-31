/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodController {
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：1:20:08 PM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="g1")
	public String g1(){
		System.out.println("哎呀，g1被点击了");
		return "menu/menus";
		
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：2:23:40 PM
	 *作者: GUO-QP
	 */
	@RequestMapping(value="g2")
	public String g2(){
		System.out.println("哎呀，g2被点击了");
		return "g2";
		
	}
	@RequestMapping(value="g3")
	public String g3(){
		System.out.println("哎呀，g3被点击了");
		return "g3";
		
	}

}
