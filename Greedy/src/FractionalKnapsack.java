import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
	
	public float maxProfit(int[] profit, int[] weights, int capacity) {
		
		KnapSackItem[] items = new KnapSackItem[profit.length];
		float maxProfit = 0;
		
		for(int i = 0 ; i < profit.length ; i++) {
			items[i] = new KnapSackItem(profit[i], weights[i]);
		}
		
		Arrays.sort(items, new Comparator<KnapSackItem>() {

			@Override
			public int compare(KnapSackItem o1, KnapSackItem o2) {
				return -1 * o1.profitByWeight.compareTo(o2.profitByWeight);
			}
			
		});
		
		System.out.println(Arrays.toString(items));
		
		for(int i = 0 ; i < items.length ; i++) {
			if(items[i].weight < capacity) {
				maxProfit += items[i].profit;
				capacity -= items[i].weight;
			} else if(capacity > 0) {
				maxProfit += ((float)capacity/items[i].weight)*items[i].profit;
				capacity = 0;
			}
		}
		
		return maxProfit;
		
	}
	
	private class KnapSackItem{
		private int profit;
		private int weight;
		private Float profitByWeight;
		
		public KnapSackItem(int profit, int weight) {
			this.profit = profit;
			this.weight = weight;
			profitByWeight = Float.valueOf((float)profit/weight);
		}
		
		public String toString() {
			return this.profit + "-" + this.weight + "-" + profitByWeight;
		}
	}
}
