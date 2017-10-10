/**
 * --------------------------------------
  *功能描述
  *创建一个监听器，用来给整个应用设置静态资源的路径
  *方便以后进行静态资源的分离操作，也省去了路径经常
  *变动的麻烦
  *---------------------------------------
  *当前版本
  *
  *v1.0
  *---------------------------------------
  *上一个版本
  *创建日期
  *---------------------------------------
  *修改日期
  *
  *文件的描述
  *---------------------------------------
*/
package com.yq.core.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AssertUrlListener implements ServletContextListener{
	
	public static String ASSERT_URL;
	public static String CSS_URL;
	public static String IMAGE_URL;
	public static String JAVASCRIPT_URL;
	public static String ICON_URL;
	
	static{
		Properties properties = new Properties();
		InputStream is = AssertUrlListener.class.getClassLoader().getResourceAsStream("assert.properties");
		try {
			properties.load(is);
			ASSERT_URL = properties.getProperty("ASSERT_URL");
			CSS_URL = properties.getProperty("CSS_URL");
			IMAGE_URL = properties.getProperty("IMAGE_URL");
			JAVASCRIPT_URL = properties.getProperty("JAVASCRIPT_URL");
			ICON_URL = properties.getProperty("ICON_URL");
		} catch (IOException e) {
			System.out.println("sorry,system init failed");
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext appContext = contextEvent.getServletContext();
		appContext.setAttribute("ASSERT_URL", ASSERT_URL);
		appContext.setAttribute("CSS_URL", CSS_URL);
		appContext.setAttribute("IMAGE_URL", IMAGE_URL);
		appContext.setAttribute("JAVASCRIPT_URL", JAVASCRIPT_URL);
		appContext.setAttribute("ICON_URL", ICON_URL);
		System.out.println("static resourse all set");
	}

}
