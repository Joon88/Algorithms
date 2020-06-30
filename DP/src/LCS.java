import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// O(mn) , where m and n are lengths of the two sequences
public class LCS {
	private StringBuilder s = new StringBuilder();
	private Map<Integer, StringBuilder> m = new HashMap<>();
	public void findLSC(char[] c1, char[] c2) {
		char[] ch1 = new char[c1.length+1];
		char[] ch2 = new char[c2.length+1];
		for(int i = 0 ; i < c1.length ; i++) {
			ch1[i+1] = c1[i];
		}
		for(int i = 0 ; i < c2.length ; i++) {
			ch2[i+1] = c2[i];
		}
		
		int[][] memo = new int[ch1.length][ch2.length];
		for(int[] a : memo) {
			Arrays.fill(a, -1);
		}
		
		System.out.println("LCS length :" + lcs(ch1, ch2, ch1.length-1, ch2.length-1, memo));
		
		for(int[] a : memo) {
			System.out.println(Arrays.toString(a));
		}
		int x = 1;
		printLcs(ch1, ch2, ch1.length-1, ch2.length-1, memo, x);
		System.out.println(s.reverse().toString());
		System.out.println("Number of LCSs : " + m.keySet().size());
		//for(StringBuilder sb : m.values()) {
		//	System.out.println(sb.reverse().toString());
		//}
		
		Set<String> s = new HashSet<>();
		for(StringBuilder s1 : m.values())
			s.add(s1.reverse().toString());
		System.out.println(s);
	}
	
	private int lcs(char[] ch1, char[] ch2, int i, int j, int[][] memo){
		if(i < 0 || j < 0)
			return -1;
		if(i == 0 || j == 0) {
			memo[i][j] = 0;
		}else if(memo[i][j] > -1) {
			return memo[i][j];
		}else if(ch1[i] == ch2[j]) {
			memo[i][j] = 1 + lcs(ch1, ch2, i-1, j-1, memo);
		}else if(ch1[i] != ch2[j])
			memo[i][j] =  Math.max(lcs(ch1, ch2, i-1, j, memo), lcs(ch1, ch2, i, j-1, memo));
		return memo[i][j];
	}
	
	private void printLcs(char[] ch1, char[] ch2, int i, int j, int[][] memo, int x) {
		if(i < 1 || j < 1) {
			return;
		}
		if(ch1[i] == ch2[j]) {
			if(m.containsKey(x)) {
				m.get(x).append(ch1[i]);
			}else {
				m.put(x, new StringBuilder().append(ch1[i]));
			}
			printLcs(ch1, ch2, i-1, j-1, memo, x);
		} else if(memo[i-1][j] > memo[i][j-1]){
			printLcs(ch1, ch2, i-1, j, memo, x);
		} else if(memo[i-1][j] < memo[i][j-1]){
			printLcs(ch1, ch2, i, j-1, memo, x);
		} else {
			printLcs(ch1, ch2, i, j-1, memo, x);
			printLcs(ch1, ch2, i-1, j, memo, x+1);
		}
	}
}
