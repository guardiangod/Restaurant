package com.ryan.app.common;

public class Utils {

	private Utils() {
	}
	
	public static Integer parseInt(String num) throws CommonException {
		Integer iNum = 0;
		try {
			iNum = Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			throw new CommonException(nfe.getMessage());
		}

		return iNum;
	}

	public static boolean isNull(String str) {
		boolean isNull = false;
		if (str == null || "".equals(str))
			isNull = true;

		return isNull;
	}
}
