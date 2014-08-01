import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;

/**
 * <p>Title: main.java</p>
 * <p>Description: xxxxƽ̨���</p>
 * <p>Copyright: Copyright (c) 2011-2012 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014��4��8�� ����11:09:34
 */
public class main {
	public static void main(String args[]) throws IOException{
	    // ���ӵ�ַ��ͨ���Ķ�htmlԴ�����ã���Ϊ��½���ύ��URL��  
		String surl = "http://yuyue.juneberry.cn/Login.aspx";  

		/** 
		* ����Ҫ��URL�µ�URLConnection�Ի��� URLConnection���Ժ����׵Ĵ�URL�õ������磺 // Using 
		* java.net.URL and //java.net.URLConnection 
		*/  
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();  

		/** 
		* Ȼ���������Ϊ���ģʽ��URLConnectionͨ����Ϊ������ʹ�ã���������һ��Webҳ�� 
		* ͨ����URLConnection��Ϊ���������԰����������Webҳ���͡�������������� 
		*/  
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		connection.setRequestMethod("GET"); //���õ�¼ģʽΪPOST���ַ�����д��
		connection.connect();
		/** 
		* ���Ϊ�˵õ�OutputStream�������������Լ����Writer���ҷ���POST��Ϣ�У����磺 ... 
		*/  
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");  
		     //���е�memberName��passwordҲ���Ķ�html�����֪�ģ���Ϊ���ж�Ӧ�Ĳ�������  
		//out.write("memberName=myMemberName&password=myPassword"); // post�Ĺؼ����ڣ�  
		//remember to clean up  
		out.flush();
		out.close();
		//��һ�δ���ҳʱ���Cookie,��ʽΪASP.NET_SessionId=qofd3iwalmf2ndu4kbt2abxp; path=/; HttpOnly
		String cookieVal = connection.getHeaderField("Set-Cookie");
		System.out.println(cookieVal);
		//��ȡCookie,��ʽΪASP.NET_SessionId=qofd3iwalmf2ndu4kbt2abxp
		int index1 = cookieVal.indexOf(";");
		String str = cookieVal.substring(0, index1);
		System.out.println(str);
		InputStream input = connection.getInputStream();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		String tmp="";
		StringBuffer html= new StringBuffer();
		
		while((tmp =buffer.readLine())!=null){
			html.append(tmp).append("\n");
		};
		buffer.close();
		input.close();
		String htmlStr = html.toString();
		//System.out.println(htmlStr);
		//�û���½�Ĳ���
		String loginUrlStr = "http://yuyue.juneberry.cn/Login.aspx";
		String __VIEWSTATE,__EVENTVALIDATION,subCmd,txt_LoginID,txt_Password,selSchool;
		String postStr;
		//��½
		URL loginUrl = new URL(loginUrlStr);
		HttpURLConnection loginConnection = (HttpURLConnection)loginUrl.openConnection();
		loginConnection.setDoOutput(true);
		loginConnection.setDoInput(true);
		loginConnection.setRequestMethod("POST");
		//loginConnection.connect();
		//���ò���
		
		loginConnection.setRequestProperty("Cache-Control", "max-age=0");
		loginConnection.setRequestProperty("Connection", "keep-alive");
		loginConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		loginConnection.setRequestProperty("Cookie", cookieVal);
		loginConnection.setRequestProperty("Referer", "http://yuyue.juneberry.cn/Login.aspx");
		loginConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.102 Safari/537.36");
		
		
		index1 = htmlStr.indexOf("id=\"__VIEWSTATE\" value=\"")+"id=\"__VIEWSTATE\" value=\"".length();
		__VIEWSTATE = htmlStr.substring(index1, htmlStr.indexOf("\"", index1));
		//System.out.println(__VIEWSTATE);
		index1 = htmlStr.indexOf("id=\"__EVENTVALIDATION\" value=\"")+"id=\"__EVENTVALIDATION\" value=\"".length();
		__EVENTVALIDATION = htmlStr.substring(index1, htmlStr.indexOf("\"", index1));
		//System.out.println(__EVENTVALIDATION);
		subCmd="Login";
		txt_LoginID = "0000202818";
		txt_Password = "0000202818";
		selSchool = "63";
		postStr = String.format("__VIEWSTATE=%s&__EVENTVALIDATION=%s&subCmd=%s"
				+ "&txt_LoginID=%s&txt_Password=%s&selSchool=%s", URLEncoder.encode(__VIEWSTATE),
				URLEncoder.encode(__EVENTVALIDATION),URLEncoder.encode(subCmd),URLEncoder.encode(txt_LoginID),
				URLEncoder.encode(txt_Password),URLEncoder.encode(selSchool));
		loginConnection.setRequestProperty("Content-Length",String.valueOf(postStr.length()));
		
		OutputStreamWriter out2 = new OutputStreamWriter(loginConnection.getOutputStream(), "UTF-8");
		out2.write(postStr);
		out2.flush();
		if(out2 != null){
			out2.close();
		}
		InputStream input2 = loginConnection.getInputStream();
		buffer = new BufferedReader(new InputStreamReader(input2,"UTF-8"));
		tmp="";
		html = new StringBuffer();
		while((tmp =buffer.readLine())!=null){
			html.append(tmp).append("\n");
		};
		buffer.close();
		input2.close();
		htmlStr = html.toString();
		//System.out.println(htmlStr);
		//System.out.println(postStr);
		
		//�´ε�½
		String mainPage = "http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx";
		URL mainPageUrl = new URL(mainPage);
		connection = (HttpURLConnection)mainPageUrl.openConnection();
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		connection.setRequestMethod("GET"); //���õ�¼ģʽΪPOST���ַ�����д��
		connection.setRequestProperty("Cookie", cookieVal);
		connection.connect();
		input = connection.getInputStream();
		buffer = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		tmp="";
		html= new StringBuffer();
		
		while((tmp =buffer.readLine())!=null){
			html.append(tmp).append("\n");
		};
		buffer.close();
		input.close();
		htmlStr = html.toString();
		System.out.println(htmlStr);
		System.out.println(postStr);
		System.out.println(cookieVal);
		
	}
	
	/*
	 * ��½���ܣ�ģ���û���½����վ
	 * @params user
	 */

}
