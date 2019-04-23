package cn.com.leadfar.cms.backend.view;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import cn.com.leadfar.cms.backend.model.Attachment;

public class AttachmentFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//判断请求的URI，是否包含"upload_image"
		//如果包含"upload_image"，则从d:/temp/upload目录中读取相应的文件，并且把文件的数据流写入response
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String requestURI = req.getRequestURI();
		
		//因为路径中可能包含有经过编码的信息，所以需要将它转换为正确的字符串
		requestURI = URLDecoder.decode(requestURI, request.getCharacterEncoding());
		
		//indexOf返回值：
		//-1表示不包含这个字符串
		//某个值，就表示这个字符串起始字符的索引
		//requestURI的值，可能是：/cms/backend/upload_image/logo.gif
		int index = requestURI.indexOf("/upload_image/");
		if(index != -1){ 
			String imageName = requestURI.substring(index+"/upload_image/".length());
			byte[] image = FileUtils.readFileToByteArray(new File(Attachment.ATTACHMENT_DIR+imageName));
			response.setContentType("image/jpeg");
			response.getOutputStream().write(image);
			return;
		}
		
		index = requestURI.indexOf("/upload_file/");
		if(index != -1){
			String fileName = requestURI.substring(index+"/upload_file/".length());
			byte[] file = FileUtils.readFileToByteArray(new File(Attachment.ATTACHMENT_DIR+fileName));
			response.setContentType("application/x-msdownload");
			response.getOutputStream().write(file);
			return;
		}
		
		//让其他的请求通行！
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
