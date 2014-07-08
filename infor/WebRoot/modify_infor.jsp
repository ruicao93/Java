<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title></title></head>
<body>
	<div align="center">
		<c:choose>
			<c:when test="${user.errorMessage==null}">
				<p><font color=blue>修改用户信息成功</font></p>
					<p><a href="info_management.jsp" target="_self"><font color=blue>返回资料管理</font></a></p>
			</c:when>
			<c:otherwise>
				<p><font color=red>修改用户信息失败，请稍后重试</font></p>
				<input type=button name=btn value=返回 onClick='window.history.go(-1)'>
			</c:otherwise>
		</c:choose>
		

	</div>
	</body>
</html>
