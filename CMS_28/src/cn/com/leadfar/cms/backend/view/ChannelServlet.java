package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public class ChannelServlet extends BaseServlet {

	private ChannelDao channelDao;

	// 在这个方法中执行查询工作
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int offset = 0;
		int pagesize = 5;

		// 希望从request中获得offset参数
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}

		// 如果从request传递过来了pagesize参数，那么就需要更新http session中的pagesize的值
		if (request.getParameter("pagesize") != null) {
			request.getSession().setAttribute("pagesize",
					Integer.parseInt(request.getParameter("pagesize")));
		}

		// 希望从http session中获得pagesize的值，如果没有，则设置缺省值为5
		Integer ps = (Integer) request.getSession().getAttribute("pagesize");
		if (ps == null) {
			request.getSession().setAttribute("pagesize", pagesize);
		} else {
			pagesize = ps;
		}

		PagerVO pv = channelDao.findChannels(offset, pagesize);

		request.setAttribute("pv", pv);

		//forward到channel_list.jsp
		request.getRequestDispatcher("/backend/channel/channel_list.jsp").forward(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从request中获取参数
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Channel c = new Channel();
		c.setName(name);
		c.setDescription(description);
		channelDao.addChannel(c);
		
		//forward到成功页面
		request.getRequestDispatcher("/backend/channel/add_channel_success.jsp").forward(request, response);
	}
	
	//打开更新界面
	public void updateInput(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		//接收从界面传递过来的ID
		String id = request.getParameter("id");
		Channel c = channelDao.findChannelById(Integer.parseInt(id));
		request.setAttribute("channel", c);
		//forward到更新页面
		request.getRequestDispatcher("/backend/channel/update_channel.jsp").forward(request, response);

	}

	//执行更新操作
	public void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//首先，从界面接收频道的基本信息（包括：ID、名称、描述）
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Channel c = new Channel();
		c.setId(Integer.parseInt(id));
		c.setName(name);
		c.setDescription(description);
		channelDao.updateChannel(c);
		
		//forward到更新成功的页面
		request.getRequestDispatcher("/backend/channel/update_channel_success.jsp").forward(request, response);

	}	
	
	//执行删除操作
	public void del(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//从界面接收ID参数
		String[] ids = request.getParameterValues("id");
		
		if(ids == null){
			//提示错误(forward到错误页面)
			request.setAttribute("error", "无法删除频道，ID不允许为空");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}

		channelDao.delChannels(ids);
		
		//转向列表页面
		response.sendRedirect(request.getContextPath()+"/backend/ChannelServlet");
		//request.getRequestDispatcher("/backend/ChannelServlet").forward(request, response);
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
}
