package cn.creater.action;

import cn.creater.util.BaseUntil;
import cn.creater.util.TimeIP;

public class Test {
	
	public static void main(String arg[]) {
		String userid = TimeIP.recordDate() + "221" + BaseUntil.getFixLenthString(3);
		System.out.println(userid);
	}
}
