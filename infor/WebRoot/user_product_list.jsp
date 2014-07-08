<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function deleteProduct() {
		var flag = false;
		for (var i = 0; i < document.getElementsByName("addID").length; i++) {
			if (document.getElementsByName("addID")[i].checked) {
				flag = true;
			}		
		}
		if (!flag) {
			alert("请选择需要添加的商品！");
			return;
		}
		if (window.confirm("确认添加到购物车？")) {
			with (document.getElementById("addGoodform")) {
				method = "post";
				action = "addGoods.do";
				submit();
			}
		}
	}
	function addProduct() {
		
			with (document.getElementById("addGoodform")) {
				method = "post";
				action = "querySort.do";
				submit();
			}
	}
	function checkAll() {
		for (var i = 0; i < document.getElementsByName("addID").length; i++) {
			document.getElementsByName("addID")[i].checked = document.getElementById("addAll").checked;
		}
	}
	function modifyUser() {
		var count = 0;
		var j = 0;
		for (var i = 0; i < document.getElementsByName("addID").length; i++) {
			if (document.getElementsByName("addID")[i].checked) {
				j = i;
				count++;
			}
		}
		if (count == 0) {
			alert("请选择需要添加的商品！");
			return;
		}
		if (count > 1) {
			alert("一次只能添加一个商品！");
			return;
		}
		if (window.confirm("确认添加到购物车？")) {
			with (document.getElementById("addGoodform")) {
				method = "post";
				action = "addGoods.do";
				submit();
			}
		}
		
	}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel=stylesheet type=text/css href="../lib/ware.css">
<title>欢迎光临网上商城</title>

</head>
<%!
	int showPage=1;
	int pagedown=1;
	int pageup=1;
	Integer in=1;
 %>
 <%  //request.setAttribute("pageCount",pageCount);
 	 if(request.getAttribute("sp")!=null){
 	 	showPage=Integer.parseInt((String)request.getAttribute("sp"));
 	 	pagedown=showPage;
 	 	pageup=showPage;
 	 }
  %>
<body bgcolor="#F4FFFE">
<div align="center">
${user.userName}
<form name="addGoodform" action="deleteProduct.do" method="post" target="_self">
<table width="95%" border="0" cellpadding="0" cellspacing="1">
<tr bgcolor="#0066FF">
<td width="5%" height="20" align="center"><font color="#FFFFFF"><input type="checkbox" name="addAll" onClick="checkAll()">全选</font></td>
<td width="10%" height="20" align="center"><font color="#FFFFFF">商品类别</font></td>
<td width="15%" height="20" align="center"><font color="#FFFFFF">商品名称</font></td>
<td width="15%" height="20" align="center"><font color="#FFFFFF">产地</font></td>
<td width="15%" height="20" align="center"><font color="#FFFFFF">添加时间</font></td>
<td width="15%" height="20" align="center"><font color="#FFFFFF">生产厂家</font></td>
<td width="10%" height="20" align="center"><font color="#FFFFFF">查看</font></td>
	<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="7">没有符合条件的数据!</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="u" >
					<tr>
						<td width="5%" height="20" align="center"><input type="checkbox" name="addID" value="${u.pid}"></td>
						<td width="10%" height="20" align="left">&nbsp;${u.sortname }</td>
						<td width="15%" height="20" align="left">&nbsp;<a href="pdetail.do?pid=${u.pid }" target="_self"><font color="#0000FF">${u.pname }</font></a></td>
						<td width="15%" height="20" align="left">&nbsp;${u.address }</td>
						<td width="15%" height="20" align="left">&nbsp;${u.createtime}</td>
						<td width="15%" height="20" align="left">&nbsp;${u.company }</td>
						<c:set value="${u.pageCount}" var="pc" scope="request" ></c:set>
						<c:set value="${u.ipage}" var="ip" scope="request" ></c:set>
						<td width="10%" height="20" align="center"><font color="#FFFFFF"><a href="pdetail.do?pid=${u.pid }" target="_self"><font color="#0000FF">查看详细</font></a></font></td>
					</tr>
				</c:forEach>
			</c:otherwise>
	</c:choose>
	
	<%
	
	if(request.getAttribute("pc")!=null){
		
			//String str=(String)request.getAttribute("pc");
			in=(Integer)request.getAttribute("pc");
	}
	if(pageup==1){
		pageup=in.intValue()+1;
	
	}
	if(pagedown==in.intValue()){
		pagedown=0;
	
	}

%>

</table>
<p><font color="#0000FF"><input name="btnDelete" class="button1" type="button"
								id="btnDelete" value="添加到购物车" onClick="modifyUser()"></font>&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#0000FF"></p>
</form>

<p>
<form action="ware_management.jsp" method="post" target="_self">	
	第<font color="#FF0000"><c:out value="${ip}"></c:out></font>页/共<font color=red><c:out value="${pc}"></c:out></font>页&nbsp;
	<a href="userQueryProduct.do?showPage=1" target="_self"><font color="#0000FF">[首页]</font></a>&nbsp;			
	<a href="userQueryProduct.do?showPage=<%=--pageup %>" target="_self"><font color="#0000FF">[上一页]</font></a>&nbsp;
	<a href="userQueryProduct.do?showPage=<%=++pagedown %>" target="_self"><font color="#0000FF">[下一页]</font></a>&nbsp;
	<a href="userQueryProduct.do?showPage=<%=in.intValue() %>" target="_self"><font color="#0000FF">[尾页]</font></a>&nbsp;
</form>

</div>

</body>
</html>
