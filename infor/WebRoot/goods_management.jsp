<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎光临网上商城</title>
	<link rel=stylesheet type=text/css href="./lib/ware.css">
	
	</head>
	<body bgcolor="#F4FFFE">
	

	<div align="center">
	<table width="90%" cellpadding="0" cellspacing="0" border="1">
		<tr bgcolor="#0066FF">
		<td width="100%" height="30" align="center" colspan="6"><font color="#FFFFFF"> 的购物车</font></td>
		</tr>
		<tr>
		<td width="25%" height="25" align="center"><font color="#0000FF">商品名称</font></td>
		<td width="15%" height="25" align="center"><font color="#0000FF">商品产地</font></td>
		<td width="20%" height="25" align="center"><font color="#0000FF">生产厂家</font></td>
		<td width="10%" height="25" align="center"><font color="#0000FF">价格</font></td>
		<td width="15%" height="25" align="center"><font color="#0000FF">购买数量</font></td>
		<td width="15%" height="25" align="center"><font color="#0000FF">从购物车中清除</font></td>
		</tr>
	<c:choose>
			<c:when test="${empty listgood}">
				<tr>
					<td colspan="7">没有符合条件的数据!</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${listgood}" var="u" >
					<tr>
					<td width="25%" height="25" align="left">&nbsp;<a href="pdetail.do?pid=${u.pid }" target="_self"><font color="#0000FF">${u.pname }</font></a></td>
					<td width="15%" height="25" align="left">&nbsp;${u.address }</td>
					<td width="20%" height="25" align="left">&nbsp;${u.company }</td>
					<td width="10%" height="25" align="left">&nbsp;${u.price }</td>
					<td width="15%" height="25" align="center">${u.picurl }</td>
					<td width="15%" height="25" align="center"><a href="delGood.do?gid=${u.pid }" target="_self"><font color="#0000FF">删除</font></a></td>
					</tr>
	
				</c:forEach>
			</c:otherwise>
	</c:choose>
	
		
		<tr>
		<td height="30" align="center" colspan="6">总计：<font color="#0000FF">${u.sumMoney }</font>&nbsp;（RMB）</td>
		</tr>
	</table>
	</div>
	</body>
</html>
