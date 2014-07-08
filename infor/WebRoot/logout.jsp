<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<%
	//使session失效
	session.invalidate();
	//跳转到登录界面
	response.sendRedirect("./index.jsp");
%>

