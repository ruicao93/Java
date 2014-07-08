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
 * <p>Description: ��������controller��</p>
 * <p>Copyright: Copyright (c) 2011-2015 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014-5-18 ����2:41:46
 */
public class DispatcherServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse resp){
		String successPath = "hello.jsp";                //��½�ɹ�ҳ���·��
		String failedPath = "login.jsp";                 //��½ʧ��ҳ���·��
		User user = new User();                          //�洢�û���Ϣ��bean
		List<String> info = new ArrayList<String>();     //�洢�����Ϣ
		
		user.setUsername(req.getParameter("username"));
		user.setUserpass(req.getParameter("userpass"));
		if(user.getUsername().equals("")){
			info.add("�û�������Ϊ�գ�\r\n");
		}
		if(user.getUserpass().equals("")){
			info.add("�û����벻��Ϊ�գ�\r\n");
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
				info.add("�˺Ż�����������������룡");
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
