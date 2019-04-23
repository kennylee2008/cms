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
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet("http://localhost:8080/cms");
			// 获得服务器响应的的所有信息
			HttpResponse response = httpclient.execute(get);
			// 获得服务器响应回来的消息体（不包括HTTP HEAD）
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				// 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "UTF-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFetch02() {
		try {
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet("http://localhost:8080/cms");
			// 获得服务器响应的的所有信息
			HttpResponse response = httpclient.execute(get);
			// 获得服务器响应回来的消息体（不包括HTTP HEAD）
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 获得响应的字符集编码信息
				// 即获取HTTP HEAD的：Content-Type:text/html;charset=UTF-8中的字符集信息
				String charset = EntityUtils.getContentCharSet(entity);
				System.out.println("响应的字符集是：" + charset);
				InputStream is = entity.getContent();
				// 使用响应中的编码来解释响应的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {

					System.out.println(line);
				}
				is.close();
			}
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFetch03() {
		try {
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();

			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					new HttpHost("192.168.1.1", 808));

			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet("http://www.baidu.com/");
			// 获得服务器响应的的所有信息
			HttpResponse response = httpclient.execute(get);
			// 获得服务器响应回来的消息体（不包括HTTP HEAD）
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 获得响应的字符集编码信息
				// 即获取HTTP HEAD的：Content-Type:text/html;charset=UTF-8中的字符集信息
				String charset = EntityUtils.getContentCharSet(entity);
				System.out.println("响应的字符集是：" + charset);
				InputStream is = entity.getContent();
				// 使用响应中的编码来解释响应的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {

					System.out.println(line);
				}
				is.close();
			}
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFetch04() {
		try {
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();
			HttpContext context = new BasicHttpContext();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet(
					"http://localhost:8080/cms/backend/main.jsp");
			// 获得服务器响应的的所有信息
			HttpResponse response = httpclient.execute(get, context);
			// 获得重定向之后的主机地址信息
			HttpHost targetHost = (HttpHost) context
					.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			System.out.println(targetHost); // http://localhost:8080
			// 获得实际的请求对象的URI（即重定向之后的"/cms/backend/login.jsp"）
			HttpUriRequest actualRequest = (HttpUriRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			System.out.println(actualRequest.getURI());
			// 获得服务器响应回来的消息体（不包括HTTP HEAD）
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 获得响应的字符集编码信息
				// 即获取HTTP HEAD的：Content-Type:text/html;charset=UTF-8中的字符集信息
				String charset = EntityUtils.getContentCharSet(entity);
				System.out.println("响应的字符集是：" + charset);
				InputStream is = entity.getContent();
				// 使用响应中的编码来解释响应的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFetch05() {
		try {
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();
			HttpContext context = new BasicHttpContext();
			// 利用HTTP GET向服务器发起请求，
			HttpGet get = new HttpGet(
					"http://localhost:8080/cms/backend/login.jsp");
			// 获得服务器响应的的所有信息
			HttpResponse response = httpclient.execute(get, context);
			// 获得服务器响应回来的消息体（不包括HTTP HEAD）
			HttpEntity entity = response.getEntity();
			String charset = null;
			if (entity != null) {
				// 获得响应的字符集编码信息
				// 即获取HTTP HEAD的：Content-Type:text/html;charset=UTF-8中的字符集信息
				charset = EntityUtils.getContentCharSet(entity);
				System.out.println("响应的字符集是：" + charset);
				InputStream is = entity.getContent();
				// 使用响应中的编码来解释响应的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// ************* 执行登录请求 ********************//
			HttpPost post = new HttpPost(
					"http://localhost:8080/cms/backend/LoginServlet");
			// 添加POST参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", "admin"));
			nvps.add(new BasicNameValuePair("password", "admin"));
			post.setEntity(new UrlEncodedFormEntity(nvps, charset));
			response = httpclient.execute(post);
			entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				// 使用响应中的编码来解释响应的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// ******************* 请求文章查询 ********************//
			get = new HttpGet(
					"http://localhost:8080/cms/backend/ArticleServlet");
			response = httpclient.execute(get);
			entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				// 使用响应中的编码来解释响应的内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, charset));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
