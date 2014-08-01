package connector.http;
/**
 * 请求行
 * @author 睿
 *
 */
public class HttpRequestLine {

	public static final int INITIAL_METHOD_SIZE = 8; //默认method长度
	public static final int INITIAL_URI_SIZE = 64; //默认URI长度
	public static final int INITIAL_PROTOCOL_SIZE = 8;
	public static final int MAX_METHOD_SIZE = 1024;
	public static final int MAX_URI_SIZE = 32768;
	public static final int MAX_PROTOCOL_SIZE = 1024;
	
	/**
	 * 缺省初始化方法
	 */
	public HttpRequestLine(){
		this(new char[INITIAL_METHOD_SIZE],0,
				new char[INITIAL_PROTOCOL_SIZE],0,
				new char[INITIAL_URI_SIZE],0);
	}
	/**
	 * 指定参数的初始化方法
	 */
	public HttpRequestLine(char[] method,int methodEnd,
			char[] uri,int uriEnd,
			char[] protocol,int protocolEnd){
		this.method = method;
		this.methodEnd = methodEnd;
		this.uri = uri;
		this.uriEnd = uriEnd;
		this.protocol = protocol;
		this.protocolEnd = protocolEnd;
	}
	
	public char[] method;
	public int methodEnd;
	public char[] uri;
	public int uriEnd;
	public char[] protocol;
	public int protocolEnd;
	/**
	 * 重置method、uri、protocol指针
	 */
	public void recycle(){
		
	}
}
