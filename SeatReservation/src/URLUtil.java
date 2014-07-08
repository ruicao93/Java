/**
 * <p>Title: URLUtil.java</p>
 * <p>Description: 进行网络连接操作</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014年4月9日 下午7:40:05
 */
import java.net.*;
public class URLUtil {
	public static HttpURLConnection getConnection(String surl) throws Exception{
		 
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
		/** 
		* 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 
		* 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做： 
		*/  
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		return connection;
	}
}
