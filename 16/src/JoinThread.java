
public class JoinThread extends Thread{

	private int i;
	public JoinThread(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	public void run(){
		for( ; i<100; i++){
			System.out.println(getName()+" "+i);
		}
	}
	
	public static void main(String args[]) throws Exception{
		JoinThread jt1 = new JoinThread("新线程");
		JoinThread jt2;
		jt1.start();
		for(int i=0; i<100; i++){
			if(i == 20){
				jt2 = new JoinThread("被join的线程");
				jt2.start();
				jt2.join();
			}
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
}
