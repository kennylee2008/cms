package cn.com.leadfar.cms.backend.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpPageFetch02 extends TestCase {
	public void testFetch01() {
		try {
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet("http://localhost:8080/cms");
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get);
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				// ��InputStreamת��ΪReader����ʹ�û����ȡ�����Ч�ʣ�ͬʱ���԰��ж�ȡ����
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "UTF-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFetch02() {
		try {
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet("http://localhost:8080/cms");
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get);
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// �����Ӧ���ַ���������Ϣ
				// ����ȡHTTP HEAD�ģ�Content-Type:text/html;charset=UTF-8�е��ַ�����Ϣ
				String charset = EntityUtils.getContentCharSet(entity);
				System.out.println("��Ӧ���ַ����ǣ�" + charset);
				InputStream is = entity.getContent();
				// ʹ����Ӧ�еı�����������Ӧ������
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {

					System.out.println(line);
				}
				is.close();
			}
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFetch03() {
		try {
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();

			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					new HttpHost("192.168.1.1", 808));

			// ����HTTP GET���������������
			HttpGet get = new HttpGet("http://www.baidu.com/");
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get);
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// �����Ӧ���ַ���������Ϣ
				// ����ȡHTTP HEAD�ģ�Content-Type:text/html;charset=UTF-8�е��ַ�����Ϣ
				String charset = EntityUtils.getContentCharSet(entity);
				System.out.println("��Ӧ���ַ����ǣ�" + charset);
				InputStream is = entity.getContent();
				// ʹ����Ӧ�еı�����������Ӧ������
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {

					System.out.println(line);
				}
				is.close();
			}
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFetch04() {
		try {
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			HttpContext context = new BasicHttpContext();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet(
					"http://localhost:8080/cms/backend/main.jsp");
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get, context);
			// ����ض���֮���������ַ��Ϣ
			HttpHost targetHost = (HttpHost) context
					.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			System.out.println(targetHost); // http://localhost:8080
			// ���ʵ�ʵ���������URI�����ض���֮���"/cms/backend/login.jsp"��
			HttpUriRequest actualRequest = (HttpUriRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			System.out.println(actualRequest.getURI());
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// �����Ӧ���ַ���������Ϣ
				// ����ȡHTTP HEAD�ģ�Content-Type:text/html;charset=UTF-8�е��ַ�����Ϣ
				String charset = EntityUtils.getContentCharSet(entity);
				System.out.println("��Ӧ���ַ����ǣ�" + charset);
				InputStream is = entity.getContent();
				// ʹ����Ӧ�еı�����������Ӧ������
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFetch05() {
		try {
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			HttpContext context = new BasicHttpContext();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet(
					"http://localhost:8080/cms/backend/login.jsp");
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get, context);
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			String charset = null;
			if (entity != null) {
				// �����Ӧ���ַ���������Ϣ
				// ����ȡHTTP HEAD�ģ�Content-Type:text/html;charset=UTF-8�е��ַ�����Ϣ
				charset = EntityUtils.getContentCharSet(entity);
				System.out.println("��Ӧ���ַ����ǣ�" + charset);
				InputStream is = entity.getContent();
				// ʹ����Ӧ�еı�����������Ӧ������
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// ************* ִ�е�¼���� ********************//
			HttpPost post = new HttpPost(
					"http://localhost:8080/cms/backend/LoginServlet");
			// ���POST����
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", "admin"));
			nvps.add(new BasicNameValuePair("password", "admin"));
			post.setEntity(new UrlEncodedFormEntity(nvps, charset));
			response = httpclient.execute(post);
			entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				// ʹ����Ӧ�еı�����������Ӧ������
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// ******************* �������²�ѯ ********************//
			get = new HttpGet(
					"http://localhost:8080/cms/backend/ArticleServlet");
			response = httpclient.execute(get);
			entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				// ʹ����Ӧ�еı�����������Ӧ������
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
