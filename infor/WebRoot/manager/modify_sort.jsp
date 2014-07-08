<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type=text/css href="../lib/ware.css">
<title>欢迎光临网上商城</title>
</head>
<body bgcolor="#F4FFFE">
<p align="center">
<form name="form1" action="modifySortDo.do" method="post">
修改商品类别名称：<input type="text" name="sortname" value="${pf.sortname }" size="30">&nbsp;&nbsp;
<input type="hidden" name="sortid" value="${pf.sortid }"/>
<input type="submit" name="sub" value="确定">
</form>

</body>
</html>
