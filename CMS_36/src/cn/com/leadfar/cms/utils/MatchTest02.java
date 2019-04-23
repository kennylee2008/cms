package cn.com.leadfar.cms.utils;

public class MatchTest02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pattern = ".*Servlet|.*\\.jsp";
		String page = "/backend/main.jsp";
		System.out.println(page.matches(pattern));
	}

}
