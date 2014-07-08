<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎光临网上商城</title>
<link rel=stylesheet type=text/css href="./lib/ware.css">
</head>
<SCRIPT language=javascript>
    function CheckForm()
    {
        if(document.loginForm.userName.value=="")
        {
             alert("请输入用户名!!!");
             document.loginForm.userName.focus();
             return false;
        }
        if(document.loginForm.userPassword1.value=="")
        {
             alert("请输入密码!!!");
             document.loginForm.userPassword1.focus();
             return false;
        }
        document.loginForm.submit();
    }
    </SCRIPT>
<body bgcolor="#F4FFFE">
<div align="center">
<br>
<H1>欢迎光临网上商城</H1>
<form name="loginForm" method="post" action="login.do" target="_self" onsubmit="CheckForm()">
<font color="#ff0000">${user.errorMessage }</font>
<table width="90%">
<tr>
<td width="50%" height="30" align="right">用户名：</td>
<td width="50%" height="30" align="left">&nbsp;<input type="text" name="userName"></td>
</tr>
<tr>
<td width="50%" height="30" align="right">密码：</td>
<td width="50%" height="30" align="left">&nbsp;<input type="password" name="userPassword1"></td>
</tr>
<tr>
<td width="100%" height="40" align="center" colspan="2">
<input type="submit" name="sub" value="登录" >&nbsp;&nbsp;
<input type="button" name="btn" value="用户注册" onClick="javascript:window.location='user_reg_form.jsp'">&nbsp;&nbsp;

</td>
</tr>
</table>
</form>

</div>
</body>
</html>
