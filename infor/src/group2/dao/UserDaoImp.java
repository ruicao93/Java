package group2.dao;

import group2.DB.ConnectDB;
import group2.model.UserForm;

import java.sql.*;


//�����̳���ConnectDB��
public class UserDaoImp extends ConnectDB implements UserDao
{
	//���ݿ����Ӷ���
	private Connection dbConn;
	private PreparedStatement stmt;
	private ResultSet rs;	
    //error ���� ������Ϣ
    private String errMes;
	
	//��ʼ������
	public UserDaoImp()
	{
		dbConn = super.getConn();
		stmt = null;
		rs = null;	
		this.errMes = super.getErrMes();		
	}
	
	 //ִ��sql ִ����䣬��Ҫ��ִ�в����ɾ����SQL���
	public UserForm addUser(UserForm user){
		
		String sql="insert into users (UserName,UserPassword,CreateTime,RealName,Telephone,Email)values(?,?,now(),?,?,?)";
		String path=null;
		try
		{
			stmt=dbConn.prepareStatement("select username from users where username=?");
			stmt.setString(1,user.getUserName());
			rs=stmt.executeQuery();
			if(!rs.next()){
	   			stmt=dbConn.prepareStatement(sql);
	   			stmt.setString(1,user.getUserName());
	   			stmt.setString(2,user.getUserPassword1());
	   			stmt.setString(3,user.getRealName());
	   			stmt.setString(4,user.getTelephone());
	   			stmt.setString(5,user.getEmail());
				stmt.executeUpdate();
				
				path="user_reg_success.jsp";
				System.out.println("�����û�ע��������Ƿ����ɹ���UserDaoImp.addUser(UserForm user)"); 
				path="user_reg_success.jsp";
				user.setPath(path);
			}
			else{
				
				path="user_reg_form.jsp";
				user.setErrorMessage("���û��Ѿ�����");
				user.setPath(path);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.errMes = this.errMes + "<br>" +e.toString();			
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	public UserForm loginConf(UserForm user){
		//UserForm uf=new UserForm();
		try {
			stmt=dbConn.prepareStatement("select userid,username,role,telephone,email,realname from users where username=? and userpassword=?");
			stmt.setString(1,user.getUserName());
			stmt.setString(2, user.getUserPassword1());
			rs=stmt.executeQuery();
			System.out.println("�����û���¼�Ƿ�ɹ���UserDaoImp.loginConf(UserForm user)"); 
			if(rs.next()){
				Long userid=rs.getLong(1);
				String username=user.getUserName();
				String role=rs.getString(3);
				String realname=rs.getString(6);
				String email=rs.getString(5);
				String telephone=rs.getString(4);
				user.setUserName(username);
				user.setUserID(userid);
				user.setRole(role);
				user.setRealName(realname);
				user.setEmail(email);
				user.setTelephone(telephone);
				if("0".equals(role)){
					user.setPath("main.jsp");
				}else{
					user.setPath("./manager/main.jsp");
				}
			}
			else{
				user.setErrorMessage("������û���������!!");
				user.setPath("index.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	
	public UserForm modfiyUser(UserForm user){
		
		String sql="update users set UserPassword=?,RealName=?,Telephone=?,Email=? where username=?";
		try {
			stmt=dbConn.prepareStatement("select userid,username,role from users where username=? and userpassword=?");
			stmt.setString(1,user.getUserName());
			stmt.setString(2, user.getUserPassword1());
			rs=stmt.executeQuery();
			System.out.println("������֤�����Ƿ�ɹ���UserDaoImp.modfiyUser(UserForm user)"); 
			if(rs.next()){
				Long userid=rs.getLong(1);
				String username=user.getUserName();
				String role=rs.getString(3);
				user.setUserName(username);
				user.setUserID(userid);
				user.setRole(role);
				stmt=dbConn.prepareStatement(sql);
				stmt.setString(1,user.getUserPassword2());
				stmt.setString(2,user.getRealName());
				stmt.setString(3, user.getTelephone());
				stmt.setString(4, user.getEmail());
				stmt.setString(5, user.getUserName());
				stmt.executeUpdate();
				user.setPath("modify_infor.jsp");
			}
			else{
				user.setErrorMessage("����У�����!!");
				user.setPath("info_management.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	//ִ��sql��ѯ���    
    public ResultSet exeQuery(String strSql)
	{		
		try
		{
   			stmt=dbConn.prepareStatement(strSql);
			rs =stmt.executeQuery();			            
		}
		catch(Exception e)
		{	
			System.out.println(e);
            this.errMes = this.errMes + "<br>" +e.toString();	
			rs = null;
		}	
		return rs;
	}
	
    //ȡ�ô�����Ϣ
    public String getErrMes()
    {
        return errMes;
    }		
}