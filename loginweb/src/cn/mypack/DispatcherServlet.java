package cn.mypack;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.mypack.User;
/**
 * <p>Title: DispatcherServlet.java</p>
 * <p>Description: 控制器（controller）</p>
 * <p>Copyright: Copyright (c) 2011-2015 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-5-18 下午2:41:46
 */
public class DispatcherServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse resp){
		String successPath = "hello.jsp";                //登陆成功页面的路径
		String failedPath = "login.jsp";                 //登陆失败页面的路径
		User user = new User();                          //存储用户信息的bean
		List<String> info = new ArrayList<String>();     //存储输出信息
		
		user.setUsername(req.getParameter("username"));
		user.setUserpass(req.getParameter("userpass"));
		if(user.getUsername().equals("")){
			info.add("用户名不能为空！\r\n");
		}
		if(user.getUserpass().equals("")){
			info.add("用户密码不能为空！\r\n");
		}
		if(user.isValidate()){
			try {
				req.setAttribute("user", user);
				req.getRequestDispatcher(successPath).forward(req,resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				info.add("账号或密码错误，请重新输入！");
				req.setAttribute("info", info);
				req.getRequestDispatcher(failedPath).forward(req,resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp){
		this.doGet(req, resp);
	}

}
