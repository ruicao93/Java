
public class Main {
	public static void main(String args[]) throws Exception{
		SignServer server = new SignServer();
		String str = server.getWeekAndNum();//("1");
		System.out.println(str);
	}
}
