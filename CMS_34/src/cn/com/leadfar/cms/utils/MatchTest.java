package cn.com.leadfar.cms.utils;

public class MatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pattern = ".*Servlet|.*\\.jsp";
		String page = "/backend/login.jsp";
		System.out.println(page.matches(pattern));
	}

}
