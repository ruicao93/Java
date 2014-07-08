package group2.DB;

import java.sql.*;

public class ConnectDB
{ 
	//���ݿ��û���
	String userName="root";
	//���ݿ�����
	String userPassword="123456";
	//���ݿ��URL�������������ݿ���ʹ�õı����ʽ
	String url="jdbc:mysql://localhost:3036/onlinetrading";
	//����һ�����Ӷ���
	Connection dbConn;   
	//������Ϣ��
	String errMes;
    	
   	public ConnectDB()
   	{
   		//��ʼ������
		errMes="";
   		dbConn=null;
   	}
   	
	//�������ݿ�
   	public Connection getConn()
   	{   	
	   	try
	   	{ 
			//�������õ����
			Class.forName("com.mysql.jdbc.Driver");
			//������ݿ�����Ӷ���
			dbConn= DriverManager.getConnection(url,userName,userPassword);
	   	}
	   	catch(Exception e)
	   	{	   		
	   		dbConn = null;
	   		errMes=e.toString();	   		
	   	}
	   	return dbConn;
   }
   
   //��ȡ������Ϣ
   public String getErrMes()
   {
   		return errMes;	
   }
}