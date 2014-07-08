<%@ page contentType="text/html; charset=UTF-8" language="java"%>
	<%@ page import="group2.model.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎光临网上商城</title>
	<link rel=stylesheet type=text/css href="./lib/ware.css">
	</head>

	<body bgcolor="#F4FFFE">
	<div align="center">
	<table width="90%" border="0" cellpadding="0" cellspacing="1">
		<tr>
		<td width="30%" height="20" align="right" rowspan="5"> <img src="./pv/${pf.picurl }" width="110" height="140"></td>
		<td width="20%" height="20" align="left">&nbsp;&nbsp;&nbsp;&nbsp;【商品名称】&nbsp;</td>
		<td width="50%" height="20" align="left">&nbsp;<font color="#0000FF">${pf.pname }</font></td>
		</tr>
		<tr>
		<td width="20%" height="20" align="left">&nbsp;&nbsp;&nbsp;&nbsp;【商品类别】&nbsp;</td>
		<td width="50%" height="20" align="left">&nbsp;<font color="#0000FF">${pf.sortname }</font></td>
		</tr>
		<tr>
		<td width="20%" height="20" align="left">&nbsp;&nbsp;&nbsp;&nbsp;【产地】&nbsp;</td>
		<td width="50%" height="20" align="left">&nbsp;<font color="#0000FF">${pf.address }</font></td>
		</tr>
		<tr>
		<td width="20%" height="20" align="left">&nbsp;&nbsp;&nbsp;&nbsp;【生产厂家】&nbsp;</td>
		<td width="45%" height="20" align="left">&nbsp;<font color="#0000FF">${pf.company }</font></td>
		</tr>
		<tr>
		<td width="20%" height="20" align="left">&nbsp;&nbsp;&nbsp;&nbsp;【价格】&nbsp;</td>
		<td width="50%" height="20" align="left">&nbsp;<font color="#0000FF">${pf.price }</font>（RMB）</td>
		</tr>
		</table>
		<table width="60%" border="0" cellpadding="0" cellspacing="1">
		<tr>
		<td width="100%" align="left" height="40" valign="middle">【商品简介】</td>
		</tr>
		<tr>
		<td width="100%" align="left">
		<font color="#0000FF">
			
			<%
			//替换商品简介中的回车和换行符号，以便在页面上能正常显示
			ProductForm f =(ProductForm)request.getAttribute("pf");
			String sIntro=f.getIntroduce();
			sIntro = sIntro.replaceAll("\r\n","<br>");
			out.println(sIntro);
			%>
		</font>
		</td>
		</tr>
		
	</table>
	
	</body>
</html>
