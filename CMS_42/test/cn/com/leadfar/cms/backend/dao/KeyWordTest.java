package cn.com.leadfar.cms.backend.dao;

public class KeyWordTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "kk jj,ll";
		//���ն��Ż�ո񣬸����ؼ����б�
		String[] ss = s.split(",| ");
		for(String s1:ss){
			System.out.println(s1);
		}
	}

}
