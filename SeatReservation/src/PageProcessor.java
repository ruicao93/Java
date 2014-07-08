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
 * <p>Description:���ڴ���ԤԼ�����е���ҳ</p>
 * <p>Copyright: Copyright (c) 2011-2015 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014-6-2 ����12:04:32
 */
public class PageProcessor {
	private UserInfo user;   //�û���Ϣ
	private String urlMainPage = "http://yuyue.juneberry.cn/Login.aspx";   //��ҳ��url
	private String urlSeatListPage ="http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx"; // ��λ�б�url
	private String error;   //�洢����
	private Map<String,String> list = null;
	/*
	 * @description:��ʼ���û���Ϣ
	 * @param:UserInfo
	 * @return :��
	 */
	public void setUser(UserInfo user){
		this.user = user;
	}
	
	/*
	 * @description:��õ�½�����ҳ��
	 * @param:��
	 * @return :��ҳ���ַ���
	 */
	public String getMainPage() throws Exception{
		String strMainPage = null;
		firstConnection();   //��һ������ ��ȡcookie�Ȳ���
		HttpURLConnection loginConnection = URLUtil.getConnection(urlMainPage);  //�����ҳ������
		loginConnection.setRequestMethod("POST");
		//���ò���
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
	 * @description:����������б�ҳ��
	 * @param:��
	 * @return :�������б�ҳ���ַ���
	 */
	public String getRoomListPage(){
		StringBuffer strRoomListPage = new StringBuffer();
		return strRoomListPage.toString();
	}
	/*
	 * @description:�����λ�б�ҳ��
	 * @param:��
	 * @return :��λ�б�ҳ���ַ���
	 */
	public String getSeatListPage(String roomNum){
		StringBuffer strSeatListPage = new StringBuffer();
		return strSeatListPage.toString();
	}
	/*
	 * @description:����������б�ҳ��
	 * @param:��
	 * @return :�������б�ҳ���ַ���
	 */
	public String getReservationPage(String roomNum,String seatNum,String date) throws Exception{
		StringBuffer strReservationPage = new StringBuffer();
		String url = String.format("http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx" +
				"?seatNo=%s&seatShortNo=%s&roomNo=%s&date=%s",roomNum+seatNum,seatNum,roomNum,URLEncoder.encode(date));
		System.out.println(url);
		//HttpURLConnection connection = resumeConnection("http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx?seatNo=301001001&seatShortNo=001&roomNo=301001&date=2014%2F6%2F3");
		//connection.setRequestMethod("GET"); //���õ�¼ģʽΪGET���ַ�����д��
		//connection.connect();
		//HttpURLConnection connection  = URLUtil.getConnection("http://yuyue.juneberry.cn/MainFunctionPage.aspx");
		HttpURLConnection connection  = URLUtil.getConnection(url);
		connection.setRequestMethod("GET"); //���õ�¼ģʽΪPOST���ַ�����д��
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
	 * @description:����������б�
	 * @param:��
	 * @return :�������б�
	 */
	public String getRoomListData(){
		StringBuffer strRoomListPage = null;
		return strRoomListPage.toString();
	}
	/*
	 * @description:�����λ�б�ҳ��
	 * @param:��
	 * @return :��λ�б�
	 */
	public String getSeatListData(String roomNum){
		StringBuffer strSeatListPage = null;
		return strSeatListPage.toString();
	}
	/*
	 * @description:ԤԼ��λ
	 * @param:��
	 * @return :�Ƿ�ɹ�boolean
	 */
	public boolean getMySeat(String roomNum,String seatNum,String date) throws Exception{
		boolean isSuccess = false;
		//String url ;  //"http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx?seatNo=301001001&seatShortNo=001&roomNo=301001&date=2014%2f6%2f3"
		String url = String.format("http://yuyue.juneberry.cn/BookSeat/BookSeatMessage.aspx" +
				"?seatNo=%s&seatShortNo=%s&roomNo=%s&date=%s",roomNum+seatNum,seatNum,roomNum,URLEncoder.encode(date));
		System.out.println(url);
		HttpURLConnection connection = URLUtil.getConnection(url);;
		connection.setRequestMethod("POST");
		//���ò���
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
		//String resultPage = getString(input);    //��ȡ���صĽ��
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
		//�ж��Ƿ�ԤԼ�ɹ�
		try {
			int index1 = resultPage.indexOf("\"MessageTip\">")
						+"\"MessageTip\">".length();
			int index2 = resultPage.indexOf("<",index1);
			error = resultPage.substring(index1,index2);
			if(error.equals("��λԤԼ�ɹ�������8:00��8:30��ͼ���ˢ��ȷ��")){
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
	 * @description:��½������
	 * @param:��
	 * @return :�Ƿ�ɹ���½
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
	 * ��һ��������λ�����������Cookie��ֵ
	 * @param:��
	 * @return:��
	 */
	public void firstConnection() throws Exception{
		String surl = "http://yuyue.juneberry.cn/Login.aspx";
		HttpURLConnection connection = URLUtil.getConnection(surl);
		connection.setRequestMethod("GET"); //���õ�¼ģʽΪGET���ַ�����д��
		connection.setRequestProperty("Cache-Control", "max-age=0");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.connect();
		//���Cookie
		String cookieVal = connection.getHeaderField("Set-Cookie");
		user.setCookie(cookieVal.substring(0,cookieVal.indexOf(";")));
		//user.setCookie(cookieVal);
		
		//System.out.println(cookieVal);
		//���hidden��Ԫ��
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
	//��InputStream����ȡҳ��String
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
	//��ȡ���Ĵ���
	public String getLastError(){
		return error;
	}
	//��ȡ�������б�
	public Map<String,String> getRoomList() throws Exception{
		if(list != null){
			return list;
		}else{
			//�������б��ַ
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
	//��ȡ��λ�б��������ܻ��ԤԼʱ�����ز���
	public void getSeatList(String roomNum){
		try {
			HttpURLConnection connection = resumeConnection(urlSeatListPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * �Ժ�ĵ�½��ֻ�踽��Cookie����
	 */
	public HttpURLConnection resumeConnection(String url) throws Exception{
		//�´ε�½
				//String url = "http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx";
				HttpURLConnection connection  = URLUtil.getConnection(url);
				connection.setRequestMethod("GET"); //���õ�¼ģʽΪPOST���ַ�����д��
				connection.setRequestProperty("Cookie", user.getCookie());
				connection.connect();
				return connection;
	}
}
