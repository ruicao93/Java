<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel=stylesheet type=text/css href="../lib/ware.css">
		<title>欢迎光临网上商城</title>


	</head>
	<script type="text/javascript">
	function deleteSort() {
		var flag = false;
		for (var i = 0; i < document.getElementsByName("DeleteID").length; i++) {
			if (document.getElementsByName("DeleteID")[i].checked) {
				flag = true;
			}		
		}
		if (!flag) {
			alert("请选择需要删除的商品！");
			return;
		}
		if (window.confirm("确认删除吗？")) {
			with (document.getElementById("delForm")) {
				method = "post";
				action = "delSort.do";
				submit();
			}
		}
	}
	function addSort() {
		
			with (document.getElementById("delForm")) {
				method = "post";
				action = "manager/add_sort.jsp";
				submit();
			}
	}
	function checkAll() {
		for (var i = 0; i < document.getElementsByName("DeleteID").length; i++) {
			document.getElementsByName("DeleteID")[i].checked = document.getElementById("delAll").checked;
		}
	}

</script>

	<body bgcolor="#F4FFFE">
	<div align="center">
	<form name="delForm" action="delete_sort.jsp" method="post">
	<table width="60%" border="0" cellpadding="0" cellspacing="1">
		<tr bgcolor="#0066FF">
		<td width="20%" height="20" align="center"><font color="#FFFFFF"><input type="checkbox" name="delAll" onClick="checkAll()"/>选中</font></td>
		<td width="50%" height="20" align="center"><font color="#FFFFFF">商品类别</font></td>
		<td width="30%" height="20" align="center"><font color="#FFFFFF">修改</font></td>
	
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="7">没有符合条件的数据!</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="u" >
					<tr>
						<td width="20%" height="20" align="center"><input type="checkbox" name="DeleteID" value="${u.sortid}"></td>
						<td width="50%" height="20" align="left">&nbsp;${u.sortname }</td>
						<td width="30%" height="20" align="left">&nbsp;<a href="modifysort.do?sortid=${u.sortid }" target="_self"><font color="#0000FF">修改</font></a></td>
						
					</tr>
				</c:forEach>
			</c:otherwise>
	</c:choose>
	
	</table>
	<p>
	<input name="btnDelete" class="button1" type="button"
								id="btnDelete" value="删除类别" onClick="deleteSort()">&nbsp;&nbsp;&nbsp;&nbsp;
	<input name="btnDelete" class="button1" type="button"
								id="btnDelete" value="添加类别" onClick="addSort()"></a>

	</form>
	</div>
</body>
</html>
