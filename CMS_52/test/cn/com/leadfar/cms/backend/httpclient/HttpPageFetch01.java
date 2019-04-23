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
			URL url = new URL(urlString); // ������һ����ַ
			InputStream is = url.openStream(); // �����ҳ������

			// ��InputStreamת��ΪReader����ʹ�û����ȡ�����Ч�ʣ�ͬʱ���԰��ж�ȡ����
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
			URL url = new URL(urlString); // ������һ����ַ
			// ���ȴ���HTTP����ָ������ĵ�ַ�Ͷ˿�
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					"192.168.1.1", 808));
			/**
			 * ���ȴ�һ�����Ӷ��� ����ͨ�����������������������֮ǰ������һЩ��������Ϣ ���磺�����������
			 */
			URLConnection conn = url.openConnection(proxy);
			InputStream is = conn.getInputStream(); // �����ҳ������
			// ��InputStreamת��ΪReader����ʹ�û����ȡ�����Ч�ʣ�ͬʱ���԰���
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
			URL url = new URL(urlString); // ������һ����ַ
			// �����Ƿ��Զ������ض���ȱʡ���ֵΪtrue
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// ����HTTP METHOD
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			System.out.println("��������Ӧ����Ϊ��" + code);
			InputStream is = conn.getInputStream();
			// ��InputStreamת��ΪReader����ʹ�û����ȡ�����Ч�ʣ�ͬʱ���԰��ж�ȡ����
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
