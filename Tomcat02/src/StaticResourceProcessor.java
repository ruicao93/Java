/**
 * <p>Title: StaticResourceProcessor.java</p>
 * <p>Description: xxxx平台软件</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-3-26 下午3:10:25
 */
import java.io.IOException;

public class StaticResourceProcessor {

	public void prcocess(Request request,Response response){
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
}
