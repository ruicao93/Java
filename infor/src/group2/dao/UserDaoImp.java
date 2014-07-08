package group2.dao;

import group2.DB.ConnectDB;
import group2.model.UserForm;

import java.sql.*;


//这个类继承自ConnectDB类
public class UserDaoImp extends ConnectDB implements UserDao
{
	//数据库连接对象
	private Connection dbConn;
	private PreparedStatement stmt;
	private ResultSet rs;	
    //error 描述 错误信息
    private String errMes;
	
	//初始化操作
	public UserDaoImp()
	{
		dbConn = super.getConn();
		stmt = null;
		rs = null;	
		this.errMes = super.getErrMes();		
	}
	
	 //执行sql 执行语句，主要是执行插入和删除的SQL语句
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
				System.out.println("测试用户注册的数据是否插入成功：UserDaoImp.addUser(UserForm user)"); 
				path="user_reg_success.jsp";
				user.setPath(path);
			}
			else{
				
				path="user_reg_form.jsp";
				user.setErrorMessage("该用户已经存在");
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
			System.out.println("测试用户登录是否成功：UserDaoImp.loginConf(UserForm user)"); 
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
				user.setErrorMessage("错误的用户名或密码!!");
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
			System.out.println("测试验证密码是否成功：UserDaoImp.modfiyUser(UserForm user)"); 
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
				user.setErrorMessage("密码校验错误!!");
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
	//执行sql查询语句    
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
	
    //取得错误信息
    public String getErrMes()
    {
        return errMes;
    }		
}