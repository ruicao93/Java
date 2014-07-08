<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>欢迎光临网上商城</title>
		<link rel=stylesheet type=text/css href="../lib/ware.css">

	</head>
	
	<body bgcolor="#F4FFFE">
	<div align="center">
		<font color="#ff0000">${error }</font>
		<form name="form1" method="post" action="addProduct.do" target="_self" enctype="multipart/form-data" >
		<table width="80%" border="0" cellpadding="0" cellspacing="1">
			<tr>
			<td width="50%" height="20" align="right">商品名称：</td>
			<td width="50%" height="20" align="left">&nbsp;<input type="text" name="pname" size="30"></td>
			</tr>
			<tr>
			<td width="50%" height="20" align="right">商品类别：</td>
			<td width="50%" height="20" align="left">&nbsp;
			<select name="sortid">
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="7">无数据</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="u" >
						<option value="${u.sortid }">${u.sortname }</option>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</select>
			</td>
			</tr>
			<tr>
			<td width="50%" height="20" align="right">生产厂家：</td>
			<td width="50%" height="20" align="left">&nbsp;<input type="text" name="company" size="30"></td>
			</tr>
			<tr>
			<td width="50%" height="20" align="right">产地：</td>
			<td width="50%" height="20" align="left">&nbsp;<input type="text" name="address" size="30"></td>
			</tr>
			<tr>
			<td width="50%" height="20" align="right">价格：</td>
			<td width="50%" height="20" align="left">&nbsp;<input type="text" name="price" size="10">（RMB）</td>
			</tr>
			<tr>
			<td width="50%" height="20" align="right">商品简介：</td>
			<td width="50%" height="20" align="left">&nbsp;<textarea name="introduce" rows="6" cols="30"></textarea></td>
			</tr>
			<tr>
			<td width="50%" height="20" align="right">上传图片：</td>
			<td width="50%" height="20" align="left">&nbsp;<input type="file" name="picurl" size="30"></td>
			</tr>
		</table>
		<p>
		<input type="submit" name="sub" value="提交">&nbsp;&nbsp;
		<input type="reset" name="res" value="重填">
		</p>
		</form>
		
	</div>
	</body>
</html>
