
public class SecondThread implements Runnable{
	private int i;
	public void run(){
		for( ; i<100 ;i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
	
	public static void main(String args[]){
		SecondThread target = new SecondThread();
		for(int i=0; i<100; i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i == 20){
				new Thread(target).start();;
				new Thread(target).start();;
			}
		}
	}

}
