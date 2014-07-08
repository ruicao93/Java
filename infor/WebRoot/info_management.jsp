<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<SCRIPT language=javascript>
    function CheckForm()
    {
        if(document.loginForm.oldPassword.value=="")
        {
             alert("请输入原密码!!!");
             document.loginForm.oldPassword.focus();
             return false;
        }
        if(document.loginForm.userPassword1.value=="")
        {
             alert("请输入新密码!!!");
             document.loginForm.userPassword1.focus();
             return false;
        }
        document.loginForm.submit();
    }
    </SCRIPT>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎光临网上商城</title>
<link rel=stylesheet type=text/css href="./lib/movie.css">

</head>
<body bgcolor="#F4FFFE">
<div align="center"><br>
<h3>修改个人资料信息</h3>
<form name="loginForm" method="post" action="modifyinfor.do" onsubmit="CheckForm()" target="_self">
<input type="hidden" name="userName" value="${user.userName}"/>
<table width="80%" border="0">

<tr>
<td width="50%" align="right" height="25">用户名：</td>
<td width="50%" align="left" height="25">&nbsp;<font color="#0000FF">${user.userName}</font></td>
</tr>
<tr>
<td width="50%" align="right" height="25">请输入旧密码：</td>
<td width="50%" align="left" height="25"><input name="oldPassword" type="password" size="20"><font color="#ff0000">${m_user.errorMessage}</font></td>
</tr>
<tr>
<td width="50%" align="right" height="25">请输入新密码：</td>
<td width="50%" align="left" height="25"><input name="userPassword1" type="password" size="20" ><font color="#ff0000">${errorpasswd}</font></td>
</tr>
<tr>
<td width="50%" align="right" height="25">请确认新密码：</td>
<td width="50%" align="left" height="25"><input name="userPassword2" type="password" size="20" ></td>

</tr>


<tr>
<td width="50%" align="right" height="25">真实姓名：</td>
<td width="50%" align="left" height="25"><input name="realName" type="text" size="20" value="${user.realName }"></td>
</tr>
<tr>
<td width="50%" align="right" height="25">电话：</td>
<td width="50%" align="left" height="25"><input name="telephone" type="text" size="20" value="${user.telephone }"></td>
</tr>
<tr>
<td width="50%" align="right" height="25">Email：</td>
<td width="50%" align="left" height="25"><input name="email" type="text" size="20" value="${user.email }"></td>
</tr>
<tr>
<td width="100%" align="center" height="30" colspan="2">
<input type="submit" name="sub" value="修改">
&nbsp;&nbsp;
<input type="reset" name="res" value="重填">
</td>
</tr>
</table>
</form>
</div>
</body>
</html>
