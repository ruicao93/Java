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
 * <p>Description: 用于处理用户座位预约请求</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014年4月9日 下午4:56:35
 */
public class SeatProcessor {
	//用户信息
	private LoginInfo user = new LoginInfo();
	private String openID;
	private boolean isBind = false;
	//预约信息
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
	 * 处理过程标识，标识当前处在处理的第几步
	 * 0:第0次请求，应进行绑定账号操作
	 * 1：第1次请求，说明菜单
	 * 2：第2次请求，进行阅览室选择操作，向用户发出阅览室列表
	 * 3：第3次请求，进行座位选择操作，向用户发出座位列表
	 * 4：第4次请求，接收用户选择的座位号，判断并预约或报错
	 */
	int processState;
	int detailState[] = {0,0,0,0,0};
	
	/*
	 * 构造函数
	 */
	public SeatProcessor(String openID){
		init(openID);
	}
	
	/*
	 * 处理用户请求的核心调度方法，调用该方法开始处理
	 * @params:String message：用户发来的请求信息
	 * @return：无
	 */
	public void process(String message){
		/*使用说明：
		 * 【0】、座位预约
		 * 【1】、绑定账号
		 */
		//若为“m”，返回使用说明
		if(message.trim().equals("m")){
			//返回使用说明
			String respMess = "使用说明：\r\n【0】座位预约\r\n【1】账号绑定";
			//如果未绑定账号，提示绑定账号，格式为“账号@密码”
			if(false == isBind){
				
			}
		}
		
		/****************************
		 * 在主菜单下（processState == 1）
		 * ***************************
		 */
		if(processState == 1){
			//若收到“1”，进行账号绑定操作
			if(message.trim().equals("1")){
				if(isBind){
					//若已绑定账号，输出提示信息，“已经绑定账号”
				}else{
					//切换至账号绑定操作状态
					processState = 0;
					detailState[0] = 0;
					//发出提示：绑定账号，格式为“账号@密码”
				}
			}
			//若收到“0”，进行座位预约操作
			if(message.trim().equals("0")){
				processState = 2;
				if(detailState[2] == 0){
					//登陆座位服务器
					try {
						login();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//输出阅览室列表
					showSeatRoomList();
					return;
				}	
			}
			//其他，
		}
		/******************************
		 * 在阅览室列表菜单下(processState==2)
		 * ****************************
		 */
		if(processState == 2){
			int num = Integer.valueOf(message);
			//若选择的阅览室号码符合要求
			if(num>=0 && num<roomList.length){
				//返回该阅览室可选择的座位号
				
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
				//若号码不在自习室列表内，返回错误信息
			
			}
		}
		
		
	}
	
	/*
	 * 初始化方法：从数据库中获取用户信息，并将用户信息填入LoginInfo
	 * @params:String openID
	 * @return:无
	 */
	public void init(String openID){
		this.openID = openID;
		processState = 1;
		//获得数据库中的用户信息，txt_LoginID,txt_Password
		String userInfo[] = getUserInfo(openID);
		//检查数据库中是否有该用户数据
		//若没有用户数据
		if(null == userInfo){
			isBind = false;
		}else{
			//若有用户数据，记录用户数据人user
			isBind = true;
			user.setTxt_LoginID(userInfo[0]);
			user.setTxt_Password(userInfo[1]);
			//进行第一次连接，获取Cookie等值
			try {
				firstConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 从数据库获得用户信息：txt_LoginID,txt_Password
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
	 * 绑定用户账号
	 * @params:openID,txt_LoginID,txt_Password
	 * @return:无
	 */
	public void bindUser(String openID,String txt_LoginID,String txt_Password){
		//操作标识位为detailState[0]
		//若为新操作，返回信息：“请输入账号密码，格式为：账号@密码”
		if(detailState[0] == 0){
			//验证账号密码是否正确
			
		}
		
	}
	
	/*
	 * 获得阅览室列表
	 * @params:无
	 * @return:Map<String,String> list:阅览室列表<阅览室名，阅览室编号>
	 */
	public Map<String,String> getSeatRoomList() throws Exception{
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
	 * 显示阅览室列表
	 * @param:无
	 * @return:无
	 */
	public void showSeatRoomList(){
		try {
			getSeatRoomList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String respMess = "阅览室列表：\r\n";
		int i = 0;
		String tmp="";
		for(String key : list.keySet()){
			respMess +="【"+i+++"】"+key+"\r\n";
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
	 * 获得阅览室座位列表
	 * @params:String roomNo:阅览室编号
	 * @return:String:用“；”隔开
	 */
	public String getSeatList(String roomNo){
		String seatList = null;
		return seatList;
	}
	
	
	
	/*
	 * 预约座位
	 * @params:String seatShortNo
	 * @return:无
	 */
	public void getSeat(String seatShortNo){
		
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
		connection.connect();
		//获得Cookie
		String cookieVal = connection.getHeaderField("Set-Cookie");
		user.setCookie(cookieVal);
		System.out.println(cookieVal);
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
		System.out.println(__VIEWSTATE);
		System.out.println(__EVENTVALIDATION);
	}
	/*
	 * 模拟post登陆
	 * @param:无
	 * @return :无
	 */
	public void login() throws Exception{
		String surl = "http://yuyue.juneberry.cn/Login.aspx";
		HttpURLConnection loginConnection = URLUtil.getConnection(surl);
		loginConnection.setRequestMethod("POST");
		//设置参数
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
	/*
	 * 返回阅览室座位列表
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
