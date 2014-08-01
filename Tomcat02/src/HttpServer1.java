/**
 * <p>Title: HttpServer1.java</p>
 * <p>Description: xxxx平台软件</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-3-26 下午3:06:22
 */
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class HttpServer1 {
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	private boolean shutdown = false;
	
	public static void main(String args[]){
		HttpServer1 server = new HttpServer1();
		System.out.println("Start。。。。。。");
		server.await();
	}
	public void await()	{
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!shutdown){
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				Request request = new Request(input);
				Response response = new Response(output);
				request.parse();
				response.setRequest(request);
				if((request.getUri()!=null)&&(request.getUri().startsWith("/servlet"))){
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request, response);
				}
				else{
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.prcocess(request, response);
				}
				socket.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			
		}
		
	}
	
}
