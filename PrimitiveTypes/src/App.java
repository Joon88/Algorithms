
public class App {

		public static void main(String args[]) {
			
			parityCheck(108);
			parityCheckTwo(108);
			
		}
		
		// O(64/8)
		// long signifies 64 bit number
		public static void parityCheck(long n) {
			int len = (int)Math.pow(2, 8);
			int a[] = new int[len];
			
			//storing parities in an array
			for(int i = 0 ; i < len ; i++) {
				a[i] = findParity(i);
			}
			
			int arr[] = new int[64/8];
			int parity[] = new int[8];
			
			for(int i = 0 ; i < 8 ; i++) {
				arr[i] = (int)(n & 0xFF);
				parity[i] = a[(int)arr[i]];
				n >>>= 8;
			}
			
			int p = 0;
			for(int i = 0 ; i < 8 ; i++) {
				p ^= parity[i];
			}
			
			System.out.println("Parity is : " + p);
			
			
		}
		
		// O(number of 1's)
		// its long data-type coz I want 64 bit number as input
		private static int findParity(int n) {
			int rslt = 0;
			while(n != 0) {
				rslt ^= 1;
				n &= (n-1);
			}
			return rslt;
		}
		
		// O(log n)
		public static void parityCheckTwo(long n) {
		
			for(int i = 32 ; i > 0 ; i /= 2) {
				n ^= (n >>> i);
			}
			
			System.out.println("The parity is : " + (n & 0x1));
		}
}
