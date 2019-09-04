<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
</head>
<body>
	<%
		//1.记住用户名,获取request中携带的cookie
		Cookie[] cookies = request.getCookies();
	    //2.如果没有找到,则用户名就为空
	    String username = "";
	    String password = "";
	    //3.判断
	    if(cookies != null){
	    	//遍历
	    	for(Cookie cookie : cookies){
	    		if("username".equals(cookie.getName())){
	    			username = cookie.getValue();
	    			
	    		}
	    		if("password".equals(cookie.getName())){
	    			password = cookie.getValue();
	    		}
	    	}
	    }
		
	    //声明一个str
	    String str = "checked='checked'";
	    if("".equals(username) && "".equals(password)){
	    	str = "";
	    }
	%>
	<form action="login.action" method="get">
		用户名:<input type="text" name="username" value="<%=username %>"><br>
		密码:<input type="password" name="password" value="<%=password %>"><br>
		验证码:<input type="text" name="vcode">
		<img style="width: 100px;height: 30px;" src="${pageContext.request.contextPath}/vcode.action" onclick="fn1(this)"><br>
		<input type="checkbox" name="rename" value="on" <%=str %>>记住用户名<br>
		<input type="submit" value="登录" style="width:80px;height:50px;background:red;">
	</form>
	<script type="text/javascript">
		function fn1(obj) {
			obj.src = "${pageContext.request.contextPath}/vcode.action?time="+new Date;
		}
	</script>
</body>
</html>