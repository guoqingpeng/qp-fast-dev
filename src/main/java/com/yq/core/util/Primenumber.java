/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.util;

import java.util.ArrayList;
import java.util.List;

public class Primenumber {
	
	/**
	 * 
	 *版本：
	 *功能描述：当前数是不是素数
	 *参数说明：@param n
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：4:58:19 PM
	 *作者: GUO-QP
	 * @throws PrimeNotFormatException 
	 * @throws Exception 
	 */
	public static boolean isPrimeumber(Integer n) throws PrimeNotFormatException {
		
		if (n<=1) {
				throw new PrimeNotFormatException("格式不对必须大于1");
		}
		
		List<Integer> allnum = new ArrayList<Integer>();
		for (Integer i = 2; i < n; i++) {
			Integer cat = n%i;
			if (cat==0) {
				allnum.add(n/i);
				break;
			}
		}
		return allnum.size()>0 ? false:true;
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：返回当前数字的下一个素数
	 *参数说明：@param n
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：4:58:43 PM
	 *作者: GUO-QP
	 * @throws PrimeNotFormatException 
	 * @throws Exception 
	 */
	public static Integer getNextPrimenumber(Integer n) throws PrimeNotFormatException{
		if (isPrimeumber(n+1)) {
			return n+1;
		}else {
			return getNextPrimenumber(n+1);
		}
	}
	
	/**
	 * 
	 *版本：
	 *功能描述：返回当前数的签一个数字
	 *参数说明：@param n
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：5:00:12 PM
	 *作者: GUO-QP
	 * @throws PrimeNotFormatException 
	 * @throws Exception 
	 */
	public static Integer getPrePrimenumber(Integer n) throws PrimeNotFormatException{
		if (isPrimeumber(n-1)) {
			if (n==2) {
				return 2;
			}else {
				return n-1;
			}
		}else {
			return getPrePrimenumber(n-1);
		}
	}
	
}
