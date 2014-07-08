
public class DrawThread extends Thread{
	private Account account;
	private double drawAmount;
	
	public DrawThread(String name,Account account,double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	
	public void run(){
		synchronized(account){
			if(drawAmount <= account.getBalance()){
				System.out.println(getName()+"È¡Ç®³É¹¦£¡ÍÂ³ö³®Æ±£º"+" "+drawAmount);
				account.setBalance(account.getBalance()-drawAmount);
				System.out.println("\tÓà¶îÎª£º"+account.getBalance());
			}
			else{
				System.out.println(getName()+"È¡Ç®Ê§°Ü£¡Óà¶î²»×ã£¡");
			}
			
		}
		
	}

}
