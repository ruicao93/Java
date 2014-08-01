import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;




import org.jsoup.Jsoup; 
import org.jsoup.nodes.*; 
import org.jsoup.select.Elements;


/**
 * <p>Title: SeatProcessor.java</p>
 * <p>Description: ���ڴ����û���λԤԼ����</p>
 * <p>Copyright: Copyright (c) 2011-2012 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014��4��9�� ����4:56:35
 */
public class SeatProcessor {
	//�û���Ϣ
	private LoginInfo user = new LoginInfo();
	private String openID;
	private boolean isBind = false;
	//ԤԼ��Ϣ
	private String date;
	private String roomNo;
	private String seatShortNo;
	private String seatNo;
	private Map<String,String> list = null;
	private String roomList[] = null;
	private String seatList = null;
	private String txtBookDate = "";
	private String hidBookDate = "";
	private String hidRrId = "";
	private String selReadingRoom = "";
	/*
	 * ������̱�ʶ����ʶ��ǰ���ڴ���ĵڼ���
	 * 0:��0������Ӧ���а��˺Ų���
	 * 1����1������˵���˵�
	 * 2����2�����󣬽���������ѡ����������û������������б�
	 * 3����3�����󣬽�����λѡ����������û�������λ�б�
	 * 4����4�����󣬽����û�ѡ�����λ�ţ��жϲ�ԤԼ�򱨴�
	 */
	int processState;
	int detailState[] = {0,0,0,0,0};
	
	/*
	 * ���캯��
	 */
	public SeatProcessor(String openID){
		init(openID);
	}
	
	/*
	 * �����û�����ĺ��ĵ��ȷ��������ø÷�����ʼ����
	 * @params:String message���û�������������Ϣ
	 * @return����
	 */
	public void process(String message){
		/*ʹ��˵����
		 * ��0������λԤԼ
		 * ��1�������˺�
		 */
		//��Ϊ��m��������ʹ��˵��
		if(message.trim().equals("m")){
			//����ʹ��˵��
			String respMess = "ʹ��˵����\r\n��0����λԤԼ\r\n��1���˺Ű�";
			//���δ���˺ţ���ʾ���˺ţ���ʽΪ���˺�@���롱
			if(false == isBind){
				
			}
		}
		
		/****************************
		 * �����˵��£�processState == 1��
		 * ***************************
		 */
		if(processState == 1){
			//���յ���1���������˺Ű󶨲���
			if(message.trim().equals("1")){
				if(isBind){
					//���Ѱ��˺ţ������ʾ��Ϣ�����Ѿ����˺š�
				}else{
					//�л����˺Ű󶨲���״̬
					processState = 0;
					detailState[0] = 0;
					//������ʾ�����˺ţ���ʽΪ���˺�@���롱
				}
			}
			//���յ���0����������λԤԼ����
			if(message.trim().equals("0")){
				processState = 2;
				if(detailState[2] == 0){
					//��½��λ������
					try {
						login();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//����������б�
					showSeatRoomList();
					return;
				}	
			}
			//������
		}
		/******************************
		 * ���������б�˵���(processState==2)
		 * ****************************
		 */
		if(processState == 2){
			int num = Integer.valueOf(message);
			//��ѡ��������Һ������Ҫ��
			if(num>=0 && num<roomList.length){
				//���ظ������ҿ�ѡ�����λ��
				
				selReadingRoom = roomList[num];
				try {
					getSeatList(num);
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				//�����벻����ϰ���б��ڣ����ش�����Ϣ
			
			}
		}
		
		
	}
	
	/*
	 * ��ʼ�������������ݿ��л�ȡ�û���Ϣ�������û���Ϣ����LoginInfo
	 * @params:String openID
	 * @return:��
	 */
	public void init(String openID){
		this.openID = openID;
		processState = 1;
		//������ݿ��е��û���Ϣ��txt_LoginID,txt_Password
		String userInfo[] = getUserInfo(openID);
		//������ݿ����Ƿ��и��û�����
		//��û���û�����
		if(null == userInfo){
			isBind = false;
		}else{
			//�����û����ݣ���¼�û�������user
			isBind = true;
			user.setTxt_LoginID(userInfo[0]);
			user.setTxt_Password(userInfo[1]);
			//���е�һ�����ӣ���ȡCookie��ֵ
			try {
				firstConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * �����ݿ����û���Ϣ��txt_LoginID,txt_Password
	 * @params:String openID
	 * @return:String[0]:txt_LoginID,String[1]:txt_Password
	 */
	public String[] getUserInfo(String openID){
		String[] userInfo = new String[2];
		userInfo[0] = "0000202818";
		userInfo[1] = "0000202818";
		return userInfo;
	}
	
	/*
	 * ���û��˺�
	 * @params:openID,txt_LoginID,txt_Password
	 * @return:��
	 */
	public void bindUser(String openID,String txt_LoginID,String txt_Password){
		//������ʶλΪdetailState[0]
		//��Ϊ�²�����������Ϣ�����������˺����룬��ʽΪ���˺�@���롱
		if(detailState[0] == 0){
			//��֤�˺������Ƿ���ȷ
			
		}
		
	}
	
	/*
	 * ����������б�
	 * @params:��
	 * @return:Map<String,String> list:�������б�<���������������ұ��>
	 */
	public Map<String,String> getSeatRoomList() throws Exception{
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
					}
				content = doc.getElementById("txtBookDate");
				txtBookDate = content.val();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}
	}
	
	/*
	 * ��ʾ�������б�
	 * @param:��
	 * @return:��
	 */
	public void showSeatRoomList(){
		try {
			getSeatRoomList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String respMess = "�������б�\r\n";
		int i = 0;
		String tmp="";
		for(String key : list.keySet()){
			respMess +="��"+i+++"��"+key+"\r\n";
			if(tmp.equals("")){
				tmp = list.get(key);
			}else{
				tmp +=";"+list.get(key); 
			}
		}
		roomList = tmp.split(";");
		System.out.println(respMess);
	}
	
	/*
	 * �����������λ�б�
	 * @params:String roomNo:�����ұ��
	 * @return:String:�á���������
	 */
	public String getSeatList(String roomNo){
		String seatList = null;
		return seatList;
	}
	
	
	
	/*
	 * ԤԼ��λ
	 * @params:String seatShortNo
	 * @return:��
	 */
	public void getSeat(String seatShortNo){
		
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
		connection.connect();
		//���Cookie
		String cookieVal = connection.getHeaderField("Set-Cookie");
		user.setCookie(cookieVal);
		System.out.println(cookieVal);
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
		System.out.println(__VIEWSTATE);
		System.out.println(__EVENTVALIDATION);
	}
	/*
	 * ģ��post��½
	 * @param:��
	 * @return :��
	 */
	public void login() throws Exception{
		String surl = "http://yuyue.juneberry.cn/Login.aspx";
		HttpURLConnection loginConnection = URLUtil.getConnection(surl);
		loginConnection.setRequestMethod("POST");
		//���ò���
		loginConnection.setRequestProperty("Cache-Control", "max-age=0");
		loginConnection.setRequestProperty("Connection", "keep-alive");
		loginConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		loginConnection.setRequestProperty("Cookie", user.getCookie());
		loginConnection.setRequestProperty("Referer", "http://yuyue.juneberry.cn/Login.aspx");
		loginConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.102 Safari/537.36");
		
		System.out.println(user.get__VIEWSTATE());
		System.out.println(user.get__EVENTVALIDATION());
		System.out.println(user.getCookie());
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
		InputStream input= loginConnection.getInputStream();
		input.close();
		loginConnection.disconnect();
		System.out.println(postStr);
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
	/*
	 * ������������λ�б�
	 */
	public String getSeatList(int num) throws Exception{
		String url = "http://yuyue.juneberry.cn/BookSeat/BookSeatListForm.aspx";
		//String hidBookDate = "";
		//String hidRrId = "";
		String subCmd = "query";
		String Cookie[] = user.getCookie().split("=");
		Map<String,String> postMap = new HashMap<String, String>();
		postMap.put("__VIEWSTATE", user.get__VIEWSTATE2());
		postMap.put("__EVENTVALIDATION", user.get__EVENTVALIDATION2());
		postMap.put("hidBookDate",hidBookDate);
		postMap.put("hidRrId", hidRrId);
		postMap.put("subCmd", "query");
		postMap.put("txtBookDate",txtBookDate);
		postMap.put("selReadingRoom",selReadingRoom);
//		postMap.put("__VIEWSTATE",URLEncoder.encode(user.get__VIEWSTATE2()));
//		postMap.put("__EVENTVALIDATION",URLEncoder.encode(user.get__EVENTVALIDATION2()));
//		postMap.put("hidBookDate",URLEncoder.encode(hidBookDate));
//		postMap.put("hidRrId", URLEncoder.encode(hidRrId));
//		postMap.put("subCmd", URLEncoder.encode("query"));
//		postMap.put("txtBookDate",URLEncoder.encode(txtBookDate));
//		postMap.put("selReadingRoom",URLEncoder.encode(selReadingRoom));
		String postStr = String.format("__VIEWSTATE=%s&__EVENTVALIDATION=%s&hidBookDate=%s&"
				+ "hidRrId=%s&subCmd=%s"
				+ "&txtBookDate=%s&selReadingRoom=%s", URLEncoder.encode(user.get__VIEWSTATE2()),
				URLEncoder.encode(user.get__EVENTVALIDATION2()),URLEncoder.encode(hidBookDate),
				URLEncoder.encode(hidRrId),
				URLEncoder.encode("query"),URLEncoder.encode("2014/04/23"),
				URLEncoder.encode("301001"));
		Document doc = Jsoup.connect("http://www.baidu.com").get();
//				.data(postMap)
//				.header("Content-Type", "application/x-www-form-urlencoded")
//				.cookie(Cookie[0], Cookie[1])
//				.post();
		Element content = doc.getElementById("DataListBookSeat");
//		Elements links = content.getElementsByTag("td");
//		for (Element link : links) {
//			System.out.println(link.text()+link.val());
//			//list.put(link.text(), link.val());
//			}
		System.out.println(doc.toString());
		
		return null;
	}
}
