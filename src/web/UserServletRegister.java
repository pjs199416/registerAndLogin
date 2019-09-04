package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;


@WebServlet("/register.action")
public class UserServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决中文乱码
		request.setCharacterEncoding("utf-8");
		//获取所有数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		UserDao userDao = new UserDao();
		boolean falg = userDao.register(user);
		
		//判断是否注册成功
		if (falg) {
			//注册成功,跳转到登录页面
			response.sendRedirect(request.getContextPath()+"/login.html");
			System.out.println("注册成功");
		}else {
			System.out.println("注册失败");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
