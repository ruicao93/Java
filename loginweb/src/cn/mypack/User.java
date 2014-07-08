package cn.mypack;
/**
 * <p>Title: User.java</p>
 * <p>Description: 存储用户信息的JAVA bean</p>
 * <p>Copyright: Copyright (c) 2011-2015 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-5-18 下午2:12:49
 */
public class User {
	private String username;
	private String userpass;
	public String getUsername(){
		return username;
	}
	
	public String getUserpass(){
		return userpass;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setUserpass(String userpass){
		this.userpass = userpass;
	}
	
	public boolean isValidate(){      //检测用户名密码是否正确
		if(username.equals("caorui")&&userpass.equals("paradise")){
			return true;
		}
		return false;
	}
}
