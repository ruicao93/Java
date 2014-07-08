import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sun.org.mozilla.javascript.internal.regexp.SubString;

/**
 * <p>Title: PageProcessor.java</p>
 * <p>Description:用于处理预约过程中的网页</p>
 * <p>Copyright: Copyright (c) 2011-2015 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-6-2 下午12:04:32
 */
public class PageProcessor {
	private UserInfo user;   //用户信息
	private String urlMainPage = "http://yuyue.juneberry.cn/Login.aspx";   //主页面url
	private String urlSeatListPage ="http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx"; // 座位列表url
	private String error;   //存储错误
	private Map<String,String> list = null;
	/*
	 * @description:初始化用户信息
	 * @param:UserInfo
	 * @return :无
	 */
	public void setUser(UserInfo user){
		this.user = user;
	}
	
	/*
	 * @description:获得登陆后的主页面
	 * @param:无
	 * @return :主页面字符串
	 */
	public String getMainPage() throws Exception{
		String strMainPage = null;
		firstConnection();   //第一次连接 获取cookie等参数
		HttpURLConnection loginConnection = URLUtil.getConnection(urlMainPage);  //获得主页面连接
		loginConnection.setRequestMethod("POST");
		//设置参数
		loginConnection.setRequestProperty("Cache-Control", "max-age=0");
		loginConnection.setRequestProperty("Connection", "keep-alive");
		loginConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		loginConnection.setRequestProperty("Cookie", user.getCookie());
		loginConnection.setRequestProperty("Referer", "http://yuyue.juneberry.cn/Login.aspx");
		loginConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.102 Safari/537.36");
		
		//System.out.println(user.get__VIEWSTATE());
		//System.out.println(user.get__EVENTVALIDATION());
		//System.out.println(user.getCookie());
		String postStr = String.format("__VIEWSTATE=%s&__EVENTVALIDATION=%s&subCmd=%s"
				+ "&txt_LoginID=%s&txt_Password=%s&selSchool=%s", URLEncoder.encode(user.get__VIEWSTATE()),
				URLEncoder.encode(user.get__EVENTVALIDATION()),URLEncoder.encode("Login"),URLEncoder.encode(user.getTxt_LoginID()),
				URLEncoder.encode(user.getTxt_Password()),URLEncoder.encode(user.getSelSchool()));
		loginConnection.setRequestProperty("Content-Length",String.valueOf(postStr.length()));	
		OutputStreamWriter out2 = new OutputStreamWriter(loginConnection.getOutputStream(), "UTF-8");
		out2.write(postStr);
		out2.flush();
		if(out2 != null){
			out2.close();
		}
		//loginConnection.connect();
		//String cookieVal = loginConnection.getHeaderField("Set-Cookie");
		//user.setCookie(cookieVal.substring(0,cookieVal.indexOf(";")));
		System.out.println(user.getCookie());
		InputStream input= loginConnection.getInputStream();
		strMainPage = getString(input);
		//System.out.println(strMainPage);
		input.close();
		loginConnection.disconnect();
		//System.out.println(postStr);
		return strMainPage;
	}
	/*
	 * @description:获得阅览室列表页面
	 * @param:无
	 * @return :阅览室列表页面字符串
	 */
	public String getRoomListPage(){
		StringBuffer strRoomListPage = new StringBuffer();
		return strRoomListPage.toString();
	}
	/*
	 * @description:获得座位列表页面
	 * @param:无
	 * @return :座位列表页面字符串
	 */
	public String getSeatListPage(String roomNum){
		StringBuffer strSeatListPage = new StringBuffer();
		return strSeatListPage.toString();
	}
	/*
	 * @description:获得阅览室列表页面
	 * @param:无
	 * @return :阅览室列表页面字符串
	 */
	public String getReservationPage(String roomNum,String seatNum,String date) throws Exception{
		StringBuffer strReservationPage = new StringBuffer();
		String url = String.format("http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx" +
				"?seatNo=%s&seatShortNo=%s&roomNo=%s&date=%s",roomNum+seatNum,seatNum,roomNum,URLEncoder.encode(date));
		System.out.println(url);
		//HttpURLConnection connection = resumeConnection("http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx?seatNo=301001001&seatShortNo=001&roomNo=301001&date=2014%2F6%2F3");
		//connection.setRequestMethod("GET"); //设置登录模式为GET（字符串大写）
		//connection.connect();
		//HttpURLConnection connection  = URLUtil.getConnection("http://yuyue.juneberry.cn/MainFunctionPage.aspx");
		HttpURLConnection connection  = URLUtil.getConnection(url);
		connection.setRequestMethod("GET"); //设置登录模式为POST（字符串大写）
		//connection.addRequestProperty("Cookie", user.getCookie());
		connection.setRequestProperty("Host", "Host: yuyue.juneberry.cn");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.setRequestProperty("Cache-Control", "max-age=0");
		connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.102 Safari/537.36");
		connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
		connection.setRequestProperty("Referer", "http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx");
		//connection.setRequestProperty("cookie",user.getCookie());
		
		
		System.out.println(user.getCookie());
		//connection.connect();
		
		InputStream input = connection.getInputStream();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		String tmp="";
		StringBuffer html= new StringBuffer();	
		while((tmp =buffer.readLine())!=null){
			html.append(tmp).append("\n");
		};
		buffer.close();
		
		String htmlStr = html.toString();
		System.out.println(htmlStr);
		int index1 = htmlStr.indexOf("id=\"__VIEWSTATE\" value=\"")+"id=\"__VIEWSTATE\" value=\"".length();
		String __VIEWSTATE = htmlStr.substring(index1, htmlStr.indexOf("\"", index1));
		index1 = htmlStr.indexOf("id=\"__EVENTVALIDATION\" value=\"")+"id=\"__EVENTVALIDATION\" value=\"".length();
		String __EVENTVALIDATION = htmlStr.substring(index1, htmlStr.indexOf("\"", index1));
		user.set__VIEWSTATE3(__VIEWSTATE);
		user.set__EVENTVALIDATION3(__EVENTVALIDATION);
		input.close();
		connection.disconnect();
		return strReservationPage.toString();
	}
	/*
	 * @description:获得阅览室列表
	 * @param:无
	 * @return :阅览室列表
	 */
	public String getRoomListData(){
		StringBuffer strRoomListPage = null;
		return strRoomListPage.toString();
	}
	/*
	 * @description:获得座位列表页面
	 * @param:无
	 * @return :座位列表
	 */
	public String getSeatListData(String roomNum){
		StringBuffer strSeatListPage = null;
		return strSeatListPage.toString();
	}
	/*
	 * @description:预约座位
	 * @param:无
	 * @return :是否成功boolean
	 */
	public boolean getMySeat(String roomNum,String seatNum,String date) throws Exception{
		boolean isSuccess = false;
		//String url ;  //"http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx?seatNo=301001001&seatShortNo=001&roomNo=301001&date=2014%2f6%2f3"
		String url = String.format("http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx" +
				"?seatNo=%s&seatShortNo=%s&roomNo=%s&date=%s",roomNum+seatNum,seatNum,roomNum,URLEncoder.encode(date));
		System.out.println(url);
		HttpURLConnection connection = URLUtil.getConnection(url);;
		connection.setRequestMethod("POST");
		//设置参数
		connection.setRequestProperty("Cache-Control", "max-age=0");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Cookie", user.getCookie());
		connection.setRequestProperty("Referer", "http://yuyue.juneberry.cn/Login.aspx");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.102 Safari/537.36");
		
		//System.out.println(user.get__VIEWSTATE());
		//System.out.println(user.get__EVENTVALIDATION());
		//System.out.println(user.getCookie());
		String postStr = String.format("__VIEWSTATE=%s&__EVENTVALIDATION=%s&subCmd=%s",
				URLEncoder.encode(user.get__VIEWSTATE3()),
				URLEncoder.encode(user.get__EVENTVALIDATION3()),URLEncoder.encode("query"));
		connection.setRequestProperty("Content-Length",String.valueOf(postStr.length()));	
		OutputStreamWriter out2 = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		out2.write(postStr);
		out2.flush();
		if(out2 != null){
			out2.close();
		}
		//loginConnection.connect();
		
		InputStream input= connection.getInputStream();
		//String resultPage = getString(input);    //获取返回的结果
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		String tmp="";
		StringBuffer html= new StringBuffer();	
		while((tmp =buffer.readLine())!=null){
			html.append(tmp).append("\n");
		};
		buffer.close();
		input.close();
		String resultPage = html.toString();
		input.close();
		connection.disconnect();
		//判断是否预约成功
		try {
			int index1 = resultPage.indexOf("\"MessageTip\">")
						+"\"MessageTip\">".length();
			int index2 = resultPage.indexOf("<",index1);
			error = resultPage.substring(index1,index2);
			if(error.equals("座位预约成功，请在8:00至8:30到图书馆刷卡确认")){
				return true;
			}else{
				//System.out.printf("%d---%d",index1,index2);
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(postStr);
		return isSuccess;
	}
	/*
	 * @description:登陆服务器
	 * @param:无
	 * @return :是否成功登陆
	 */
	public boolean login(){
		try {
			String strMainPage = getMainPage();
			//System.out.println(strMainPage);
			int index1 = strMainPage.indexOf("<span id=\"spanWarmInfo\" name=\"spanWarmInfo\" style=\"color: Red\">")
						+"<span id=\"spanWarmInfo\" name=\"spanWarmInfo\" style=\"color: Red\">".length();
			int index2 = strMainPage.indexOf("<",index1);
			if(index2 == index1){
				return true;
			}else{
				error = strMainPage.substring(index1,index2);
				//System.out.printf("%d---%d",index1,index2);
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	/*
	 * 第一次连接座位服务器，获得Cookie等值
	 * @param:无
	 * @return:无
	 */
	public void firstConnection() throws Exception{
		String surl = "http://yuyue.juneberry.cn/Login.aspx";
		HttpURLConnection connection = URLUtil.getConnection(surl);
		connection.setRequestMethod("GET"); //设置登录模式为GET（字符串大写）
		connection.setRequestProperty("Cache-Control", "max-age=0");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.connect();
		//获得Cookie
		String cookieVal = connection.getHeaderField("Set-Cookie");
		user.setCookie(cookieVal.substring(0,cookieVal.indexOf(";")));
		//user.setCookie(cookieVal);
		
		//System.out.println(cookieVal);
		//获得hidden的元素
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
		int index1 = htmlStr.indexOf("id=\"__VIEWSTATE\" value=\"")+"id=\"__VIEWSTATE\" value=\"".length();
		String __VIEWSTATE = htmlStr.substring(index1, htmlStr.indexOf("\"", index1));
		index1 = htmlStr.indexOf("id=\"__EVENTVALIDATION\" value=\"")+"id=\"__EVENTVALIDATION\" value=\"".length();
		String __EVENTVALIDATION = htmlStr.substring(index1, htmlStr.indexOf("\"", index1));
		user.set__VIEWSTATE(__VIEWSTATE);
		user.set__EVENTVALIDATION(__EVENTVALIDATION);
		//System.out.println(__VIEWSTATE);
		//System.out.println(__EVENTVALIDATION);
	}
	//从InputStream流获取页面String
	public String getString(InputStream input) throws Exception{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		String tmp="";
		StringBuffer html= new StringBuffer();	
		while((tmp =buffer.readLine())!=null){
			html.append(tmp).append("\n");
		};
		buffer.close();
		return html.toString();
	}
	//获取最后的错误
	public String getLastError(){
		return error;
	}
	//获取阅览室列表
	public Map<String,String> getRoomList() throws Exception{
		if(list != null){
			return list;
		}else{
			//阅览室列表地址
			String url = "http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx";
			list = new HashMap<String, String>();
			try {
				HttpURLConnection connection = resumeConnection(url);
				String Cookie[] = user.getCookie().split("=");
				Document doc = Jsoup.connect(url).cookie(Cookie[0], Cookie[1]).get();
				Element content = doc.getElementById("selReadingRoom");
				Elements links = content.getElementsByTag("option");
				for (Element link : links) {
					//System.out.println(link.text()+link.val());
					list.put(link.text(), link.val());
					System.out.println(link.text()+" "+link.val());
					}
				content = doc.getElementById("txtBookDate");
				user.txtBookDate = content.val();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}
	}
	//获取座位列表，这样才能获得预约时的隐藏参数
	public void getSeatList(String roomNum){
		try {
			HttpURLConnection connection = resumeConnection(urlSeatListPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 以后的登陆，只需附加Cookie即可
	 */
	public HttpURLConnection resumeConnection(String url) throws Exception{
		//下次登陆
				//String url = "http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx";
				HttpURLConnection connection  = URLUtil.getConnection(url);
				connection.setRequestMethod("GET"); //设置登录模式为POST（字符串大写）
				connection.setRequestProperty("Cookie", user.getCookie());
				connection.connect();
				return connection;
	}
}
