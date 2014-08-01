import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * <p>Title: MainTest.java</p>
 * <p>Description: xxxx平台软件</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014年4月9日 下午7:54:27
 */
public class MainTest {
	public static void main(String args[]){
		SeatProcessor processor = new SeatProcessor("111");
		try {
			//processor.firstConnection();
			processor.process("0");
			//processor.process("1");
			//processor.getSeatList(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
