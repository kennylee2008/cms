package cn.com.leadfar.cms.backend.httpclient;

import junit.framework.TestCase;

public class ReplaceCharTest extends TestCase {
	public void testReplaceChar() throws Exception{
		//测试利用正则表达式，替换所有非法的字符
		//非法字符：/\:*?<>|
		String s = "这是包含/\\非法:字*符?的<文>本|";
		System.out.println(s);
		/**
		 * /
		 * \\\\
		 * \\:
		 * \\*
		 * \\?
		 * \\|
		 * \\<
		 * >
		 * 
		 */
		s = s.replaceAll("/|\\\\|\\:|\\*|\\?|\\||\\<|>", "_");
		System.out.println(s);
	}
}
