
public class DrawTest {
	public static void main(String args[]){
		Account account = new Account("1234567", 500);
		new DrawThread("��", account, 300).start();
		new DrawThread("��", account, 300).start();
		
	}
}
