package group2.servlet;

import group2.manager.*;
import group2.model.ProductForm;
import group2.model.UserForm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

public class Myservlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=null;
		System.out.println(request.getRequestURI());
		String uri=request.getRequestURI();
		System.out.println("������uri��ַ="+uri);
		uri=uri.substring(uri.indexOf("/", 1),uri.indexOf(".", 1));
		System.out.println("uri��ַ="+uri);
		
		String userName=request.getParameter("userName");
		String userPassword1 = request.getParameter("userPassword1");
		String userPassword2=request.getParameter("userPassword2");
		String realName = request.getParameter("realName");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		UserForm user=new UserForm();
		user.setUserName(userName);
		user.setUserPassword1(userPassword1);
		user.setUserPassword2(userPassword2);
		user.setRealName(realName);
		user.setTelephone(telephone);
		user.setEmail(email);	
		
		if("/regist".equals(uri)){
			if(userPassword1!=null){
				if(userPassword1.equals(userPassword2)){
					user=UserManager.getInstance().addUser(user);
					path=user.getPath();
					request.setAttribute("r_user", user);
				}
				else{
					request.setAttribute("errorpasswd", "������������벻ͬ");
					request.setAttribute("r_user", user);
					path="user_reg_form.jsp";
				}		
			}
			System.out.println("����ע��֮�����ת��ַmyservlet/regist.do"+path);
		}
		else if("/login".equals(uri)){
			user=UserManager.getInstance().loginConf(user);
			path=user.getPath();
			System.out.println("���Ե�¼֮���user.userName=="+user.getUserName());
			request.getSession().setAttribute("user", user);
			System.out.println("���Ե�¼֮�����ת��ַmyservlet/login.do=="+path);
		}
		else if("/modifyinfor".equals(uri)){
			
			if(userPassword1!=null){
				if(userPassword1.equals(userPassword2)){
					user.setUserPassword1((String)request.getParameter("oldPassword"));
					System.out.println("�û���="+user.getUserName());
					System.out.println("����="+user.getUserPassword1());
					user=UserManager.getInstance().modfiyUser(user);
					path=user.getPath();
					request.setAttribute("m_user", user);
				}
				else{
					path="info_management.jsp";
					request.setAttribute("errorpasswd", "������������벻ͬ");
					
				}		
			}
			System.out.println("�����޸��û���Ϣ֮�����ת��ַmyservlet/login.do=="+path);
		}
		else if("/queryProduct".equals(uri)){
			int show;
			
			if((String)request.getParameter("showPage")==null){
				show=1;
			}else{
				show=Integer.parseInt((String)request.getParameter("showPage"));
			}
			String s=String.valueOf(show);
			System.out.println("myservlet.\"/queryProduct\"��ʾshowpage="+request.getParameter("showPage"));
			List list=ProductManager.getInstance().queryProduct(5,show);
			request.setAttribute("list", list);
			request.setAttribute("sp",s);
			path="manager/product_manager.jsp";
		}
		else if("/querySort".equals(uri)){
			List list=ProductManager.getInstance().querySort();
			request.setAttribute("list", list);
			path="manager/add_pro.jsp";
		}
		else if("/addProduct".equals(uri)){
			//path="querySort.do";
			ProductForm pf=new ProductForm();
			try
			{
				SmartUpload su = new SmartUpload();
				
				
				su.initialize(getServletConfig(),request,response);
				su.setMaxFileSize(1000000*8);
				su.setAllowedFilesList("jpg,gif,bmp,JPG,GIF,BMP,png,PNG");
				su.upload();
				String pname=su.getRequest().getParameter("pname");
				String company=su.getRequest().getParameter("company");
				String address=su.getRequest().getParameter("address");
				String picurl=su.getRequest().getParameter("picurl");
				String introduce=su.getRequest().getParameter("introduce");
				float price=9.0f;		
				long sortid=1l;
				
				try {
					price = Float.parseFloat(su.getRequest().getParameter("price"));
					sortid = Long.parseLong(su.getRequest().getParameter("sortid"));
				} catch (Exception e) {
					request.setAttribute("error", "��Ϣ���ʧ�ܣ�������������Ƿ���ȷ");
					System.out.println("����ת���쳣");
					e.printStackTrace();
					e.printStackTrace();
				}			
				

				//ProductForm pf=new ProductForm();
				
				
				pf.setPname(pname);
				pf.setCompany(company);
				pf.setAddress(address);
				pf.setPrice(price);
				pf.setSortid(sortid);
				pf.setIntroduce(introduce);
				if(picurl==null){
					picurl="00000000000000.gif";
					pf.setPicurl(picurl);
				}
				
				com.jspsmart.upload.File file = su.getFiles().getFile(0);
				if (!file.isMissing())
				{
					java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
					String sNowTime = dateFormatter.format(new java.util.Date());
					picurl = sNowTime+"."+ file.getFileExt();
					pf.setPicurl(picurl);
					request.setAttribute("error", "��Ϣ���ʧ�ܣ�������������Ƿ���ȷ");
					file.saveAs("./pv/"+picurl); 
					System.out.println("�����Ƿ��ϴ��ɹ�");
					//path=ProductManager.getInstance().addProduct(pf);
					System.out.println("myservlet.addProduct()���������Ʒ��Ϣ�����ת��ַ"+path);

				}
				
			}
			catch(Exception e)
			{
				
				e.printStackTrace();
			}
			path=ProductManager.getInstance().addProduct(pf);
			
		}
		else if("/pdetail".equals(uri)){
			String pid=(String)request.getParameter("pid");
			ProductForm pf=ProductManager.getInstance().pDetail(pid);
			request.setAttribute("pf", pf);
			path="ware_detail.jsp";
		}
		else if("/modifyProduct".equals(uri)){
			String pid=(String)request.getParameter("pid");
			ProductForm pf=ProductManager.getInstance().pDetail(pid);
			request.setAttribute("pf", pf);
			/**
			 * ��ʼ����querySort.do
			 */
			List list=ProductManager.getInstance().querySort();
			request.setAttribute("list", list);
			
			
			/**
			 * ��ɵ���querySort.do
			 */
			path="manager/modify_pro.jsp";
		}
		else if("/modfyProductDo".equals(uri)){
			System.out.println("Myservlet.modfyProductDo����");
			try
			{
				SmartUpload su = new SmartUpload();
				
				
				su.initialize(getServletConfig(),request,response);
				su.setMaxFileSize(1000000*8);
				su.setAllowedFilesList("jpg,gif,bmp,JPG,GIF,BMP,png,PNG");
				su.upload();
				String pname=su.getRequest().getParameter("pname");
				String company=su.getRequest().getParameter("company");
				String address=su.getRequest().getParameter("address");
				String picurl=su.getRequest().getParameter("picurl");
				String introduce=su.getRequest().getParameter("introduce");
				String pid=su.getRequest().getParameter("pid");
				System.out.println("Myservlet.modfyProductDo������ȡ�õ�pid="+pid);
				float price=9.0f;		
				long sortid=1l;
				try {
					price = Float.parseFloat(su.getRequest().getParameter("price"));
					sortid = Long.parseLong(su.getRequest().getParameter("sortid"));
				} catch (Exception e) {
					request.setAttribute("error", "��Ϣ���ʧ�ܣ�������������Ƿ���ȷ");
					System.out.println("����ת���쳣");
					e.printStackTrace();
					e.printStackTrace();
				}			
				

				ProductForm pf=new ProductForm();
				
				pf.setPid(pid);
				pf.setPname(pname);
				pf.setCompany(company);
				pf.setAddress(address);
				pf.setPrice(price);
				pf.setSortid(sortid);
				pf.setIntroduce(introduce);
				if(picurl==null){
					picurl="00000000000000.gif";		
				}
				
				com.jspsmart.upload.File file = su.getFiles().getFile(0);
				if (!file.isMissing())
				{
					java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
					String sNowTime = dateFormatter.format(new java.util.Date());
					picurl = sNowTime+"."+ file.getFileExt();
					pf.setPicurl(picurl);
					request.setAttribute("error", "��Ϣ���ʧ�ܣ�������������Ƿ���ȷ");
					file.saveAs("./pv/"+picurl); 
					System.out.println("�����Ƿ��ϴ��ɹ�");
					path=ProductManager.getInstance().modifyProduct(pf);
					System.out.println("myservlet.modifProductDo()���������Ʒ��Ϣ�����ת��ַ"+path);

				}
				
			}
			catch(Exception e)
			{
				
				e.printStackTrace();
			}
			
			
		}
		else if("/delProduct".equals(uri)){
			System.out.println("���Ҫɾ����pid");
			String [] pid=request.getParameterValues("DeleteID");
			for(int i=0;i<pid.length;i++){
				System.out.print(pid[i]+" ");
			}
			path=ProductManager.getInstance().delProduct(pid);
			
		}
		else if("/mquerySort".equals(uri)){
			List list=ProductManager.getInstance().querySort();
			request.setAttribute("list", list);
			path="manager/sort_management.jsp";
		}
		else if("/addSort".equals(uri)){
			String sortname=request.getParameter("sortname");
			ProductManager.getInstance().addSort(sortname);
			path="mquerySort.do";
		}
		else if("/modifysort".equals(uri)){
			String sortid=request.getParameter("sortid");
			ProductForm pf=ProductManager.getInstance().modifySort(sortid);
			request.setAttribute("pf", pf);
			path="manager/modify_sort.jsp";
			
		}
		else if("/modifySortDo".equals(uri)){
			String sortname=request.getParameter("sortname");
			String sortid=request.getParameter("sortid");
			ProductManager.getInstance().modifySortDo(sortname,sortid);
			path="mquerySort.do";
	
		}
		else if("/delSort".equals(uri)){
			System.out.println("���Ҫɾ����sortid");
			String [] pid=request.getParameterValues("DeleteID");
			for(int i=0;i<pid.length;i++){
				System.out.print(pid[i]+" ");
			}
			ProductManager.getInstance().delSort(pid);
			path="mquerySort.do";
			
		}
		else if("/userQueryProduct".equals(uri)){
			int show;
			
			if((String)request.getParameter("showPage")==null){
				show=1;
			}else{
				show=Integer.parseInt((String)request.getParameter("showPage"));
			}
			String s=String.valueOf(show);
			System.out.println("myservlet.\"/queryProduct\"��ʾshowpage="+request.getParameter("showPage"));
			List list=ProductManager.getInstance().queryProduct(5,show);
			request.setAttribute("list", list);
			request.setAttribute("sp",s);
			path="user_product_list.jsp";
			
		}
		else if("/addGoods".equals(uri)){
			System.out.println("-----������Ʒ����ߺ���ƷID-----");
			UserForm uf=(UserForm)request.getSession().getAttribute("user");
			Long userid=uf.getUserID();
			String wareid=request.getParameter("addID");
			System.out.println(userid+"-----������Ʒ����ߺ���ƷID-----"+wareid);
			ProductManager.getInstance().addGood(wareid, userid);
			path="queryGoods.do?userid="+userid;
			
		}
		else if("/queryGoods".equals(uri)){
			System.out.println("-----������Ʒ����ߺ���ƷID-----");
			UserForm uf=(UserForm)request.getSession().getAttribute("user");
			Long l=uf.getUserID();
			System.out.println(l);
			String userid=request.getParameter("userid");
			userid=l.toString();
			List list=ProductManager.getInstance().queryGood(userid);
			request.setAttribute("listgood", list);
			path="goods_management.jsp";
			
		}
		else if("/delGood".equals(uri)){
			String gid=request.getParameter("gid");
			ProductManager.getInstance().delGoods(gid);
			path="queryGoods.do";
		}
		else if("/admingood".equals(uri)){
			List list=ProductManager.getInstance().adminGood();
			request.setAttribute("alist", list);
			path="manager/goods_management.jsp";
		}
		else if("/admindelGood".equals(uri)){
			String gid=request.getParameter("gid");
			ProductManager.getInstance().delGoods(gid);
			path="admingood.do";
		}
		else if("/ddddDo".equals(uri)){
			
		}
		else if("/ddddDo".equals(uri)){
			
		}
		else if("/ddddDo".equals(uri)){
			
		}
		else if("/ddddDo".equals(uri)){
			
		}
		else if("/ddddDo".equals(uri)){
			
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
