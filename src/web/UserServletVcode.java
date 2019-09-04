package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dsna.util.images.ValidateCode;


@WebServlet("/vcode.action")
public class UserServletVcode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取一张图片
		ValidateCode validateCode = new ValidateCode(130, 50, 4, 20);
		//获取验证码字符串
		String code = validateCode.getCode();
		//存储到session作用域
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		ServletOutputStream outputStream = response.getOutputStream();
		validateCode.write(outputStream);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
