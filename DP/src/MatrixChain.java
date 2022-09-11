/*

Problems based on MCM :
1. Scramble string | LC-87 (https://leetcode.com/problems/scramble-string/)
Soln - Same concept as MCM, but with different computation for each partition at index k.

2. Word Break | LC-139 (https://leetcode.com/problems/word-break/)
Soln - Initial attempt was similar to MCM, but can be optimised further to have a 1-D DP
solutions. Please also look at the BFS approach to solve this problem, give in the leetcode
solutions.

 */

// O(n^3)
public class MatrixChain {
	private char matrix = 'A';
	public void getMatrixChainDataBU(int[] p) {
		int[][] table = new int[p.length][p.length];
		int[][] split = new int[p.length][p.length];

		for(int i = 0 ; i < p.length ; i++) {
			table[i][i] = 0;
		}

		for(int len = 2 ; len < p.length ; len++) {
			for(int i = 1 ; i < p.length-len + 1 ; i++) {
				int j = i + len - 1;

				table[i][j] = Integer.MAX_VALUE;

				for(int k = i ; k < j ; k++) {
					int sum = table[i][k] + table[k+1][j] + p[i-1]*p[k]*p[j];
					if(sum < table[i][j]) {
						table[i][j] = sum;
						split[i][j] = k;
					}
				}
			}
		}

		System.out.println(table[1][p.length-1]);

		printParenthesis(1, p.length-1, split);
	}

	private void printParenthesis(int i, int j, int[][] split) {
		if(i == j) {
			System.out.print(matrix++);
			return;
		}
		System.out.print("(");
		printParenthesis(i, split[i][j], split);
		printParenthesis(split[i][j] + 1, j, split);
		System.out.print(")");

	}

	public void getMatrixChainDataTD(int[] p) {
		int[][] table = new int[p.length][p.length];
		int[][] split = new int[p.length][p.length];

		for(int i = 1 ; i < p.length ; i++) {
			for(int j = 1 ; j < p.length ; j++)
				table[i][j] = Integer.MAX_VALUE;
		}
		System.out.println(matrixChain(split, table, p, 1, p.length-1));

		printParenthesis(1, p.length-1, split);
	}

	private int matrixChain(int[][] split, int[][] table, int[] p, int i, int j) {
		if(i == j) {
			table[i][j] = 0;
		} else if(table[i][j] < Integer.MAX_VALUE) {
			return table[i][j];
		}
		for(int k = i ; k < j ; k++) {
			int sum = matrixChain(split, table, p, i, k) + matrixChain(split, table, p, k+1, j) + p[i-1]*p[k]*p[j];
			if(sum < table[i][j]) {
				table[i][j] = sum;
				split[i][j] = k;
			}
		}
		return table[i][j];
	}
}
