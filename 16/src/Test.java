
public class Test {

	public static void main(String args[]){
		String str1 = new String("��������");
		String str2 = new String("��������");
		String str3 = "��������";
		String str4 = "��������";
		System.out.println(str1.hashCode()+"*****"+str2.hashCode());
		System.out.println(str3.hashCode()+"*****"+str4.hashCode());
	}
}
