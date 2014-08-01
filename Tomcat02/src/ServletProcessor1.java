/**
 * <p>Title: ServletProcessor1.java</p>
 * <p>Description: xxxx平台软件</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-3-26 下午3:12:19
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.io.File;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletProcessor1 {

	public void process(Request request,Response response){
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/")+1);
		URLClassLoader loader = null;
		
		try {
			//Creat a URLClass Loader
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String repository = (new URL("file", null, classPath.getCanonicalPath()+File.separator))
					.toString();
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Servlet servlet = null;
		try {
			servlet = (Servlet)myClass.newInstance();
			servlet.service((ServletRequest)request, (ServletResponse)response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
