package cn.com.leadfar.cms.backend.dao;

public class KeyWordTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "kk jj,ll";
		//按照逗号或空格，隔开关键字列表
		String[] ss = s.split(",| ");
		for(String s1:ss){
			System.out.println(s1);
		}
	}

}
