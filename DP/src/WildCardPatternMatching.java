import java.util.Arrays;

public class WildCardPatternMatching {
	// O(m*n), m - length of search string, and n - length of the pattern
	public void checkMatch(String str, String pattern) {
		int m = str.length();
		int n = pattern.length();

		boolean[][] dp = new boolean[m+1][n+1];
		dp[0][0] = true;

		for(int i = 1 ; i <= m ; i++) {
			dp[i][0] = false;
		}
		for(int j = 1 ; j <= n ; j++ ) {
			dp[0][j] = pattern.charAt(j-1) == '*' ? dp[0][j-1] : false;
			//dp[0][j] = false;
		}
		// if(pattern.charAt(0) == '*') {
		// 	for(int i = 0 ; i <= m ; i++)
		// 		dp[i][1] = true;
		// }

		for(int i = 1 ; i <= m ; i++) {
			for(int j = 1 ; j <= n ; j++) {
				if(str.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?') {
					dp[i][j] = dp[i-1][j-1];
				} else if(pattern.charAt(j-1) == '*') {
					dp[i][j] = dp[i][j-1] || dp[i-1][j];
				} else {
					dp[i][j] = false;
				}
			}
		}

		for(boolean[] arr : dp) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("The result is : " + dp[m][n]);
	}
}
