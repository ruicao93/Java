/**
 * <p>Title: URLUtil.java</p>
 * <p>Description: �����������Ӳ���</p>
 * <p>Copyright: Copyright (c) 2011-2012 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014��4��9�� ����7:40:05
 */
import java.net.*;
public class URLUtil {
	public static HttpURLConnection getConnection(String surl) throws Exception{
		 
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
		/** 
		* Ȼ���������Ϊ���ģʽ��URLConnectionͨ����Ϊ������ʹ�ã���������һ��Webҳ�� 
		* ͨ����URLConnection��Ϊ���������԰����������Webҳ���͡�������������� 
		*/  
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		return connection;
	}
}
