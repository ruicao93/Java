<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
<SCRIPT language=javascript>
    function CheckForm()
    {
        if(document.regform.userName.value=="")
        {
             alert("请输入用户名!!!");
             document.regform.userName.focus();
             return false;
        }
        if(document.regform.userPassword1.value=="")
        {
             alert("请输入密码!!!");
             document.regform.userPassword1.focus();
             return false;
        }
        document.regform.submit();
    }
    </SCRIPT>
<title>欢迎光临网上商城</title>
<link rel=stylesheet type=text/css href="./lib/ware.css">
</head>
<body bgcolor="#F4FFFE">
<div align="center">
<h1>网上商城用户注册</h1>
<hr width="90%">
<form name="regform" method="post" action="regist.do" >
<table width=80%>
<tr>
<td align=right width="40%">*请输入用户名：</td>
<td align=left><input name="userName" size="15" type="text" value="${r_user.userName }"><font color="#ff0000">${r_user.errorMessage }</font></td>
</tr>
<tr>
<td align=right>*请输入密码：</td>
<td align=left><input type="password" name="userPassword1" size=20> </td>
</tr>
<tr>
<td align=right>*请确认密码：</td>
<td align=left><input type="password" name="userPassword2" size=20><font color="#ff0000">${errorpasswd} </font></td>
</tr>
<tr>
<td align=right>*请输入真实姓名：</td>
<td align=left><input name="realName" size="20" value="${r_user.realName}"></td>
</tr>
<tr>
<td align=right>*请输入电话号码：</td>
<td align=left><input type="text" name="telephone" size="20" value="${r_user.telephone}"></td>
</tr>
<tr>
<td align=right>*请输入您的Email：</td>
<td align=left><input name="email" size="20" value="${r_user.email}"></td>
</tr>

</table>
<hr width="90%">
<input type="submit" value="提交表格" name="sub"> 
&nbsp;&nbsp;<input type="reset" value="重新填写"> 

</form>
</div>
</body>
</html>
