<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>欢迎光临网上商城</title>
		<link rel=stylesheet type=text/css href="./lib/ware.css">
	</head>
	<body bgcolor="#F4FFFE">
		<h1 align="center"><font color="#0000FF">欢迎光临网上商城</font></h1>
		<p align="center">
		<a href="userQueryProduct.do" target="mainFrame"><font color="#0000FF">浏览商品信息</font></a>&nbsp;&nbsp;
		
		<a href="queryGoods.do" target="mainFrame"><font color="#0000FF">购物车管理</font></a>&nbsp;&nbsp;
		<a href="info_management.jsp" target="mainFrame"><font color="#0000FF">资料管理</font></a>&nbsp;&nbsp;
		欢迎您：<font color="#FF0000">${user.userName}</font>&nbsp;&nbsp;
		<a href="./logout.jsp" target="_parent"><font color="#0000FF">退出</font></a>
		</p>
	</body>
</html>
