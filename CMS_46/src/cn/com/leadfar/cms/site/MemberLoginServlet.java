package cn.com.leadfar.cms.site;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.view.BaseServlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class MemberLoginServlet extends BaseServlet {
	
	private MemberDao memberDao;
	
	private int width;
	private int height;
	private int number; // 显示多少个字符
	private String codes; // 有哪些字符可供选择

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		width = Integer.parseInt(config.getInitParameter("width"));
		height = Integer.parseInt(config.getInitParameter("height"));
		number = Integer.parseInt(config.getInitParameter("number"));
		codes = config.getInitParameter("codes");
	}

	//生成验证码
	public void checkcode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("image/jpeg");

		// 创建一张图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		// 创建白色背景
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 画黑边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

		Random random = new Random();

		// 每个字符占据的宽度
		int x = (width - 1) / number;
		int y = height - 4;

		StringBuffer sb = new StringBuffer();

		// 随机生成字符
		for (int i = 0; i < number; i++) {
			String code = String.valueOf(codes.charAt(random.nextInt(codes
					.length())));
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));

			Font font = new Font("Arial", Font.PLAIN,
					random(height / 2, height));
			g.setFont(font);

			g.drawString(code, i * x + 1, y);

			sb.append(code);
		}

		// 将验证码串放到HTTP SESSION中
		request.getSession().setAttribute("codes", sb.toString());

		// 随机生成一些点
		for (int i = 0; i < 50; i++) {
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}

		OutputStream out = response.getOutputStream();
		// 将图片转换为JPEG类型
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);

		out.flush();
		out.close();

	}

	// 用于执行登录认证
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode"); // 用户输入的验证码

		// 系统判断验证码是否正确
		// 刚刚生成的验证码串
		String sessionCodes = (String) request.getSession().getAttribute(
				"codes");

		// if(!sessionCodes.equalsIgnoreCase(checkcode)){
		// //重定向[forward]到登录页面
		// request.setAttribute("error", "验证码错误");
		// request.getRequestDispatcher("/backend/login.jsp").forward(request,
		// response);
		// return;
		// }

		// 系统判断用户名是否存在，判断密码是否正确
		Member member = memberDao.findMemberByNickname(nickname);
		
		if (member == null) {
			// forward到long.jsp，并且提示“用户名不存在”
			request.setAttribute("error", "用户【" + nickname + "】不存在");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request,
					response);
			return;
		}

		if (!member.getPassword().equals(password)) {
			// forward到long.jsp，并且提示“用户密码不正确”
			request.setAttribute("error", "用户【" + nickname + "】的密码不正确，请重试");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request,
					response);
			return;
		}

		// 需要把登录用户的信息存入HTTP SESSION
		request.getSession().setAttribute("LOGIN_MEMBER", member);

		String ref = request.getHeader("referer");
		// 判断都通过了，转向刚才发出登录请求的界面
		response.sendRedirect(ref);

	}
	
	//会员退出网站！
	public void quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 清空Http Session中的所有数据，销毁HTTP SESSION对象
		// 结束会话
		request.getSession().invalidate();
		
		// 转向引用页面
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}	

	/**
	 * 产生一个从min到max之间的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	private int random(int min, int max) {
		int m = new Random().nextInt(999999) % (max - min);
		return m + min;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


}
