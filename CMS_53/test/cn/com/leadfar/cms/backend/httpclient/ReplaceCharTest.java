package cn.com.leadfar.cms.backend.httpclient;

import junit.framework.TestCase;

public class ReplaceCharTest extends TestCase {
	public void testReplaceChar() throws Exception{
		//��������������ʽ���滻���зǷ����ַ�
		//�Ƿ��ַ���/\:*?<>|
		String s = "���ǰ���/\\�Ƿ�:��*��?��<��>��|";
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
