package cn.com.leadfar.cms.utils;

public class PropertiesBeanFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory factory = new PropertiesBeanFactory();
		factory.getArticleDao();
		factory.getChannelDao();
	}

}
