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
 * <p>Description: xxxx平台软件</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014年4月8日 下午11:09:34
 */
public class main {
	public static void main(String args[]) throws IOException{
	    // 连接地址（通过阅读html源代码获得，即为登陆表单提交的URL）  
		String surl = "http://yuyue.juneberry.cn/Login.aspx";  

		/** 
		* 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using 
		* java.net.URL and //java.net.URLConnection 
		*/  
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();  

		/** 
		* 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 
		* 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做： 
		*/  
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		connection.setRequestMethod("GET"); //设置登录模式为POST（字符串大写）
		connection.connect();
		/** 
		* 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ... 
		*/  
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");  
		     //其中的memberName和password也是阅读html代码得知的，即为表单中对应的参数名称  
		//out.write("memberName=myMemberName&password=myPassword"); // post的关键所在！  
		//remember to clean up  
		out.flush();
		out.close();
		//第一次打开主页时获得Cookie,格式为ASP.NET_SessionId=qofd3iwalmf2ndu4kbt2abxp; path=/; HttpOnly
		String cookieVal = connection.getHeaderField("Set-Cookie");
		System.out.println(cookieVal);
		//提取Cookie,格式为ASP.NET_SessionId=qofd3iwalmf2ndu4kbt2abxp
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
		//用户登陆的参数
		String loginUrlStr = "http://yuyue.juneberry.cn/Login.aspx";
		String __VIEWSTATE,__EVENTVALIDATION,subCmd,txt_LoginID,txt_Password,selSchool;
		String postStr;
		//登陆
		URL loginUrl = new URL(loginUrlStr);
		HttpURLConnection loginConnection = (HttpURLConnection)loginUrl.openConnection();
		loginConnection.setDoOutput(true);
		loginConnection.setDoInput(true);
		loginConnection.setRequestMethod("POST");
		//loginConnection.connect();
		//设置参数
		
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
		
		//下次登陆
		String mainPage = "http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx";
		URL mainPageUrl = new URL(mainPage);
		connection = (HttpURLConnection)mainPageUrl.openConnection();
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		connection.setRequestMethod("GET"); //设置登录模式为POST（字符串大写）
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
	 * 登陆功能，模拟用户登陆到网站
	 * @params user
	 */

}
