package com.zhangjing.util;

import com.zhangjing.entity.Remark;
import com.zhangjing.entity.User;

import java.util.List;

public class DateReplaceUtil {
	/**
	 * 修改时间格式
	 * 去除时间结尾 .0 的bug
	 * @param list
	 */
	public static void dateUtil(List<User> list) {
		for (int i=0;i<list.size();i++) {
			list.get(i).setRegistertime(
					list.get(i).getRegistertime().toString().replace(".0",""));
		}
	}

	public static void remarkDateUtil(List<Remark> list) {
		for (int i=0;i<list.size();i++) {
			list.get(i).setTime(
					list.get(i).getTime().toString().replace(".0",""));
		}
	}
}
