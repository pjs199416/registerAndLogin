package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;

@WebServlet("/login.action")
public class UserServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户名
		String username = request.getParameter("username");
		//获取密码
		String password = request.getParameter("password");
		String rename = request.getParameter("rename");
		
		//把用户名保存到cookie中
		Cookie cookie = new Cookie("username", username);
		Cookie cookie2 = new Cookie("password", password);
		//判断是否记住用户名
		if ("on".equals(rename)) { 
			//记住,设置有效期时间
			cookie.setMaxAge(7*24*60*60);
			cookie2.setMaxAge(7*24*60*60);
		}else {
			//不记住
			cookie.setMaxAge(0);
			cookie2.setMaxAge(0);
		}
		
		//设置cookie携带的路径
		cookie.setPath(request.getContextPath());
		//将cookie添加到response
		response.addCookie(cookie);
		response.addCookie(cookie2);
		
		
		
		User user = null;
		try {
			UserDao userDao = new UserDao();
			user = userDao.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if (user != null) {
			//登录成功
			writer.write("登录成功");
		}else {
			writer.write("登录失败");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
