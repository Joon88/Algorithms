
public class GreedyMain {
	public static void main(String args[]) {
		int[] profit  = {25, 24, 15};
		int[] weights = {18, 15, 10};
		int capacity = 30;
		
		FractionalKnapsack fn = new FractionalKnapsack();
		System.out.println(fn.maxProfit(profit, weights, capacity));
		
		HuffmanCode hCode = new HuffmanCode();
		System.out.println(hCode.getHuffmanCode("Suha"));
		
		
		int[] profit1 = {200, 180, 190, 300, 120, 100};
		int[] deadline = {5, 3, 3, 2, 4, 2};
		JobSequencingWithDeadlines js = new JobSequencingWithDeadlines();
		System.out.println(js.getMaxProfit(profit1, deadline));
		
		Integer[] a = {23, 78, 100};
		Integer[] b = {1, 6, 45, 65, 79};
		Integer[] c = {90, 91, 92, 100, 150, 160, 170};
		OptimalMerge om = new OptimalMerge();
		System.out.println(om.optimallyMerge(a, b, c));
		
		System.out.println("------------");
		
		JobSeqDisjointSet js1 = new JobSeqDisjointSet();
		System.out.println(js1.getMaxProfit(profit1, deadline));
	}
}
