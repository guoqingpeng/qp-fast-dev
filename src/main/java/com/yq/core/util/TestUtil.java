/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.util;


public class TestUtil {

	/**
	 *版本：
	 *功能描述：
	 *参数说明：@param args
	 *返回值说明：
	 *更新日期：1:08:47 PM
	 *作者: GUO-QP
	 * @throws Exception 
	 */
	public static void main(String[] args){
		
		try {
			System.out.println(Primenumber.getPrePrimenumber(1));
		} catch (PrimeNotFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
