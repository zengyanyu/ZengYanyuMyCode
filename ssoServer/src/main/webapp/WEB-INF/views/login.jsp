<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
</head>
${errorMessage}
<body>
	<form action="/ssoServer/login" method="post">
		<input type="hidden" name="redirectUrl" value="${redirectUrl}">
		<br>
		<input type="text" name="username" placeholder="账号" value="zhangsan">
		<br>
		<input type="password" name="password" placeholder="密码" value="666">
		<br>
		<input type="submit" value="登录">
	</form>
</body>
</html>