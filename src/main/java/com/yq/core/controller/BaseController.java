/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
	
	/**
	 *控制器器异常统一错误页面
	 *版本：
	 *功能描述：
	 *参数说明：@param e
	 *参数说明：@param request
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：5:33:32 PM
	 *作者: GUO-QP
	 */
	@ExceptionHandler(Exception.class)
	public String handleExcption(Exception e,HttpServletRequest request){
		e.printStackTrace();
		return "404";
	}
}