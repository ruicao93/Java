import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class ThirdThread implements Callable<Integer>{

	private int i;
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		for( ;i<100 ;i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
		return i;
	}
	
	public static void main(String args[]){
		ThirdThread rt = new ThirdThread();
		FutureTask<Integer> task = new FutureTask<Integer>(rt);
		for(int i=0; i<100; i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i == 20){
				new Thread(task).start();
			}
		}
		try {
			System.out.println("线程返回值："+task.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
