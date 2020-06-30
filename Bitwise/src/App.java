import java.util.Random;

public class App {
	
	public static void main(String args[]) {
		int i = 0xFFFF;
		System.out.println(i);
		countOnes(-5);
		rightPropagate(26);
		findModulo(22, 4);
		checkIfPowerOfTwo(65535);
		
		System.out.println(Integer.toBinaryString(-5));
		
		Random r = new Random();
		System.out.println(r.nextInt(1));
	}
	
	public static void countOnes(int n) {
		short ctr = 0;
		while(n != 0) {
			ctr += (n & 1);
			n >>>= 1;
		}
		
		System.out.println(ctr);
	}
	
	public static void rightPropagate(int n) {
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(n-1));
		int y = n & (n-1);
		int z = n & ~(n-1);
		System.out.println(z + "---" + Integer.toBinaryString(z));
		System.out.println(y + "---" + Integer.toBinaryString(y));
		System.out.println(n | (n-1));
	}
	
	public static void findModulo(int x, int y) {
		int a = (int)Math.pow(2, y)-1;
		System.out.println(a);
		int z = x & a;
		System.out.println("Modulo " + z);
	}
	
	public static void checkIfPowerOfTwo(int x) {
		if(x == (x & ~(x-1)))
			System.out.println(true);
		else
			System.out.println(false);
	}

}
