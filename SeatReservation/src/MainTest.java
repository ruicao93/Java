import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * <p>Title: MainTest.java</p>
 * <p>Description: xxxx平台软件</p>
 * <p>Copyright: Copyright (c) 2011-2015 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-6-2 下午1:34:42
 */
public class MainTest {
	public static void main(String args[]) throws IOException{
		String userID = "0000";
		int num;
		//UserInfo user = new UserInfo();   // 建立用户信息类
		Map<String,String> roomList;
		//user.setTxt_LoginID("0000202930");
		//user.setTxt_Password("0000202930");
		
		FileOutputStream outputStream ;
		PrintStream print;
		outputStream = new FileOutputStream("d:/test.txt");
		print = new PrintStream(outputStream);
		for(num = 200000; num<207000; num++){
			UserInfo user = new UserInfo();   // 建立用户信息类
			user.setTxt_LoginID(userID+Integer.toString(num));
			user.setTxt_Password(userID+Integer.toString(num));
			PageProcessor pageProcessor = new PageProcessor();   //建立页面处理类
			pageProcessor.setUser(user);           //设置用户
			//尝试登陆
			if(!pageProcessor.login()){
				//若登陆失败
				//System.out.println("0;"+pageProcessor.getLastError());
				
				//return;
			}else{
				//System.out.println("登陆成功");
				print.println(userID+Integer.toString(num));
			}
		}
		print.close();
		outputStream.close();
		//获取阅览室列表 和下一个参数2
		//获取座位列表 和下一个参数
		//获得预约界面 和下一个参数3
//		try {
//			System.out.println("viwstate3:"+user.get__VIEWSTATE3());
//			System.out.println("viewstate3"+user.get__EVENTVALIDATION3());
//			pageProcessor.getReservationPage("301001","001","2014/6/7");
//			System.out.println("viwstate3:"+user.get__VIEWSTATE3());
//			System.out.println("viewstate3"+user.get__EVENTVALIDATION3());
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//获取座位
//		try {
//			if(!pageProcessor.getMySeat("301001","001","2014/6/7")){
//				//若失败
//				System.out.println("0;"+pageProcessor.getLastError());
//				return;
//			}else{
//				System.out.println(pageProcessor.getLastError());
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("1;"+pageProcessor.getLastError());
//			e.printStackTrace();
//		}
	}
}
