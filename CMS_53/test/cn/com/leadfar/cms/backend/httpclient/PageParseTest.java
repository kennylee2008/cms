package cn.com.leadfar.cms.backend.httpclient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.util.NodeList;

import cn.com.leadfar.cms.utils.HttpUtils;
import cn.com.leadfar.cms.utils.ParseUtils;

import junit.framework.TestCase;

public class PageParseTest extends TestCase {
	
	private String localFile = "d:/temp.html";
	
	public void testFetchPage() throws Exception{
		try {
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();

			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					new HttpHost("192.168.1.1", 808));

			// ����HTTP GET���������������
			HttpGet get = new HttpGet("http://www.ibm.com/developerworks/cn/java/j-javaroundtable/index.html");
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get);
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// �����Ӧ���ַ���������Ϣ
				// ����ȡHTTP HEAD�ģ�Content-Type:text/html;charset=UTF-8�е��ַ�����Ϣ
				String charset = EntityUtils.getContentCharSet(entity);
				InputStream is = entity.getContent();
				IOUtils.copy(is, new FileOutputStream(localFile));
			}
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//��ȡ��ҳ�е�ͼƬ���ӵ�ַ
	public void testParse01() throws Exception{
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		//����һ��HTML������
		Parser parser = new Parser();
		parser.setInputHTML(html);
		
		//��ȡ���е�ͼƬ��ǩ<img>��ǩ
		NodeList imageTags = parser.parse(new NodeClassFilter(ImageTag.class));
		for(int i=0; i<imageTags.size(); i++){
			ImageTag it = (ImageTag)imageTags.elementAt(i);
			String imageUrl = it.getImageURL();
			System.out.println(imageUrl);
		}
	}
	
	//��ȡ����ĳ�������ı�ǩ
	public void testParse02() throws Exception{
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		//����һ��HTML������
		Parser parser = new Parser();
		parser.setInputHTML(html);
		
		//��ȡname="title"��meta��ǩ
		NodeList metaTags = parser.parse(
			new NodeFilter(){
				@Override
				public boolean accept(Node node) {
					if(node instanceof MetaTag){
						MetaTag mt = (MetaTag)node;

						if(mt.getMetaTagName() != null && mt.getMetaTagName().equals("title")){
							return true;
						}
					}
					return false;
				}
			}
		);
		
		for(int i=0; i<metaTags.size(); i++){
			MetaTag mt = (MetaTag)metaTags.elementAt(i);
			System.out.println("���µı����ǣ�"+mt.getMetaContent());
		}
	}
	
	//��ȡ���µļ��͹ؼ���
	public void testParse03() throws Exception{
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		MetaTag t = ParseUtils.parseTag(html, MetaTag.class, "name", "Abstract");
		System.out.println("���µļ���ǣ�"+t.getMetaContent());
		
		t = ParseUtils.parseTag(html, MetaTag.class, "name", "Keywords");
		System.out.println("���µĹؼ����ǣ�"+t.getMetaContent());
	}	
	
	//��ȡ���µ�������Ϣ
	public void testParse04() throws Exception{
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		List<Div> authors = ParseUtils.parseTags(html, Div.class, "class", "author");
		for(Div div:authors){
			System.out.println(
					ParseUtils.parseTag(div.getStringText(), LinkTag.class).getStringText()
			);
		}
	}
	
	//��ȡ���µ�����
	public void testParse05() throws Exception{
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		String content = StringUtils.substringBetween(html, "<!-- MAIN_COLUMN_CONTENT_BEGIN -->", "<!-- CMA");
		System.out.println(content);
	}
	
	//�����������е�ͼƬ���ص�����
	public void testParse06() throws Exception{
		
		HttpClient httpclient = new DefaultHttpClient();
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		String content = StringUtils.substringBetween(html, "<!-- MAIN_COLUMN_CONTENT_BEGIN -->", "<!-- CMA");
		
		//��ȡ���������е�ͼƬ��Ϣ
		Parser parser = new Parser();
		parser.setInputHTML(content);
		
		//��ȡ���е�ͼƬ��ǩ<img>��ǩ
		NodeList imageTags = parser.parse(new NodeClassFilter(ImageTag.class));
		for(int i=0; i<imageTags.size(); i++){
			ImageTag it = (ImageTag)imageTags.elementAt(i);
			String imageUrl = it.getImageURL(); // /a/b/xxx.jpg
			System.out.println(imageUrl);
			String url = "http://www.ibm.com/developerworks/cn/java/j-javaroundtable/"+imageUrl;
			String imageName = FilenameUtils.getName(url);
			byte[] image = HttpUtils.getImage(httpclient, url);
			//�洢�����ص�ĳ��Ŀ¼��
			IOUtils.write(image, new FileOutputStream("d:/temp/"+imageName));
		}
		
		httpclient.getConnectionManager().shutdown();
	}
	
	//���Խ����µ�����ͼƬ�޸�Ϊ�µĵ�ַ
	public void testParse07() throws Exception{
		
		HttpClient httpclient = new DefaultHttpClient();
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		String content = StringUtils.substringBetween(html, "<!-- MAIN_COLUMN_CONTENT_BEGIN -->", "<!-- CMA");
		
		content = ParseUtils.modifyImageUrl(content, "upload_image/���±���/");
		
		System.out.println(content);
		
		httpclient.getConnectionManager().shutdown();
	}
	
	//�����Ƴ�ĳЩ��ǩ
	public void testParse08() throws Exception{
		
		HttpClient httpclient = new DefaultHttpClient();
		
		//���ļ������ݶ���һ���ַ�����
		String html = IOUtils.toString(new FileInputStream(localFile),"UTF-8");
		
		//String content = StringUtils.substringBetween(html, "<!-- MAIN_COLUMN_CONTENT_BEGIN -->", "<!-- CMA");
		
		String content = ParseUtils.reomveTags(html, Div.class, "class", "ibm-alternate-rule");
		
		System.out.println(content);
		
		httpclient.getConnectionManager().shutdown();
	}
}
