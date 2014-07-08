<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type=text/css href="../lib/ware.css">
<title>欢迎光临网上商城</title>


</head>

<body bgcolor="#F4FFFE">
<div align="center">
<form name="form1" action="delete_goods.jsp" method="post" target="_self">
<table width="95%" border="0" cellpadding="0" cellspacing="1">
	<tr bgcolor="#0066FF">
	
	<td width="5%" height="20" align="center"><font color="#FFFFFF">编号</font></td>
	<td width="15%" height="20" align="center"><font color="#FFFFFF">商品名称</font></td>
	<td width="15%" height="20" align="center"><font color="#FFFFFF">订购人</font></td>
	<td width="15%" height="20" align="center"><font color="#FFFFFF">订购数量</font></td>
	<td width="20%" height="20" align="center"><font color="#FFFFFF">订购时间</font></td>
	<td width="15%" height="20" align="center"><font color="#FFFFFF">操作</font></td>
	</tr>

	<c:choose>
			<c:when test="${empty alist}">
				<tr>
					<td colspan="7">没有符合条件的数据!</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${alist}" var="u" >
					<tr>
					<td width="5%" height="25" align="left">&nbsp;${u.pid }</td>
					<td width="15%" height="25" align="left">&nbsp;<a href="pdetail.do?pid=${u.sortname }" target="_self"><font color="#0000FF">${u.pname }</font></a></td>
					<td width="15%" height="25" align="left">&nbsp;${u.company }</td>
					<td width="15%" height="25" align="left">&nbsp;${u.picurl }</td>
					<td width="20%" height="25" align="center">${u.createtime }</td>
					<td width="15%" height="25" align="center"><a href="admindelGood.do?gid=${u.pid }" target="_self"><font color="#0000FF">删除</font></a></td>
					
					</tr>
	
				</c:forEach>
			</c:otherwise>
	</c:choose>


</table>
</form>
<p>

</p>

</div>
</body>
</html>
