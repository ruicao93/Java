package cn.mypack;
/**
 * <p>Title: User.java</p>
 * <p>Description: �洢�û���Ϣ��JAVA bean</p>
 * <p>Copyright: Copyright (c) 2011-2015 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014-5-18 ����2:12:49
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
	
	public boolean isValidate(){      //����û��������Ƿ���ȷ
		if(username.equals("caorui")&&userpass.equals("paradise")){
			return true;
		}
		return false;
	}
}
