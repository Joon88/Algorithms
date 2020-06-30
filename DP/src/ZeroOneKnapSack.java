// O(nw). n - > number of elts, and w -> knapsack capacity
public class ZeroOneKnapSack {
	public int[][] memo; 
	public int knapsack(int i, int w, int[] weight, int[] profit) {
		System.out.println(i + "++" + w);
		if(i == 0 || w == 0) {
			return 0;
		} else if(memo[i][w] > 0) {
			System.out.println("---");
			return memo[i][w];
		} else if(weight[i] > w) {
			memo[i][w] = knapsack(i-1, w, weight, profit);
		} else {
			memo[i][w] = Math.max(knapsack(i-1, w, weight, profit), profit[i] + knapsack(i-1, w - weight[i], weight, profit));
		}
		return memo[i][w];
	}
	
	public boolean subsetSum(int n, int sum, int[] num) {
		boolean isSubset = false;
		if(sum == 0) {
			return true;
		} else if(n == 0) {
			return false;
		} else if(memo[n][sum] != -1) {
			if(memo[n][sum] == 0)
				return false;
			return true;
		} else if(num[n-1] > sum) {
			isSubset = subsetSum(n-1, sum, num);
			if(isSubset)
				memo[n][sum] = 1;
			else
				memo[n][sum] = 0;
		} else {
			isSubset = subsetSum(n-1, sum, num) || subsetSum(n-1, sum - num[n-1], num);
			if(isSubset)
				memo[n][sum] = 1;
			else
				memo[n][sum] = 0;
		}
		return isSubset;
	}
}
