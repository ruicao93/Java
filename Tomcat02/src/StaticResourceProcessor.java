/**
 * <p>Title: StaticResourceProcessor.java</p>
 * <p>Description: xxxxƽ̨���</p>
 * <p>Copyright: Copyright (c) 2011-2012 ���˾�</p>
 * <p>Company: ���˾�</p>
 * @author CR
 * @version 1.0 ����ʱ�䣺2014-3-26 ����3:10:25
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
