package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.BeanFactory;

public class SearchArticlesServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int offset = 0;
		int pagesize = 5;
		
		//ϣ����request�л��offset����
		try{
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		}catch(Exception ignore){}
		
		//�����request���ݹ�����pagesize��������ô����Ҫ����http session�е�pagesize��ֵ
		if(request.getParameter("pagesize") != null){
			request.getSession().setAttribute("pagesize",
					Integer.parseInt(request.getParameter("pagesize"))
				);
		}
		
		//ϣ����http session�л��pagesize��ֵ�����û�У�������ȱʡֵΪ5
		Integer ps = (Integer)request.getSession().getAttribute("pagesize");
		if(ps == null){
			request.getSession().setAttribute("pagesize", pagesize);
		}else{
			pagesize = ps;
		}
		
		//�ӽ����л�ȡtitle����
		String title = request.getParameter("title");
		
		ArticleDao articleDao = BeanFactory.getArticleDao();
		PagerVO pv = articleDao.findArticles(title, offset, pagesize);
		
		request.setAttribute("pv", pv);
		
		//forward��article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}
}