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
		
		//希望从request中获得offset参数
		try{
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		}catch(Exception ignore){}
		
		//如果从request传递过来了pagesize参数，那么就需要更新http session中的pagesize的值
		if(request.getParameter("pagesize") != null){
			request.getSession().setAttribute("pagesize",
					Integer.parseInt(request.getParameter("pagesize"))
				);
		}
		
		//希望从http session中获得pagesize的值，如果没有，则设置缺省值为5
		Integer ps = (Integer)request.getSession().getAttribute("pagesize");
		if(ps == null){
			request.getSession().setAttribute("pagesize", pagesize);
		}else{
			pagesize = ps;
		}
		
		//从界面中获取title参数
		String title = request.getParameter("title");
		
		ArticleDao articleDao = BeanFactory.getArticleDao();
		PagerVO pv = articleDao.findArticles(title, offset, pagesize);
		
		request.setAttribute("pv", pv);
		
		//forward到article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}
}