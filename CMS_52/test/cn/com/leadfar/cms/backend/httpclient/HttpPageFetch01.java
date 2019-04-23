package cn.com.leadfar.cms.backend.httpclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;

public class HttpPageFetch01 extends TestCase {
	public void testFetch01() {
		try {
			String urlString = "http://localhost:8080/cms/";
			URL url = new URL(urlString); // 代表了一个网址
			InputStream is = url.openStream(); // 获得网页的内容

			// 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFetch02() {
		try {
			String urlString = "http://www.ibm.com/developerworks/cn/java/j-javaroundtable/index.html";
			URL url = new URL(urlString); // 代表了一个网址
			// 首先创建HTTP代理，指定代理的地址和端口
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					"192.168.1.1", 808));
			/**
			 * 首先打开一个连接对象 可以通过这个对象，在真正发起请求之前，设置一些其它的信息 比如：代理服务器等
			 */
			URLConnection conn = url.openConnection(proxy);
			InputStream is = conn.getInputStream(); // 获得网页的内容
			// 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFetch03() {
		try {
			String urlString = "http://localhost:8080/cms/backend/main.jsp";
			URL url = new URL(urlString); // 代表了一个网址
			// 设置是否自动进行重定向，缺省这个值为true
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置HTTP METHOD
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			System.out.println("服务器响应代码为：" + code);
			InputStream is = conn.getInputStream();
			// 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
