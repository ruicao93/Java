
public class Test {

	public static void main(String args[]){
		String str1 = new String("込込込込");
		String str2 = new String("込込込込");
		String str3 = "込込込込";
		String str4 = "込込込込";
		System.out.println(str1.hashCode()+"*****"+str2.hashCode());
		System.out.println(str3.hashCode()+"*****"+str4.hashCode());
	}
}
