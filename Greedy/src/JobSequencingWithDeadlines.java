import java.util.Arrays;
import java.util.Comparator;

// Each jobs takes 1 time unit to run
public class JobSequencingWithDeadlines {
	private class Job {
		private int profit;
		private int deadline;
		
		public String toString() {
			return "Profit : " + profit + " & Deadline : " + deadline;
		}
	}
	
	public int getMaxProfit(int[] profit, int[] deadline) {
		Job[] jobs = new Job[profit.length];
		
		for(int i = 0 ; i < profit.length ; i++) {
			jobs[i] = new Job();
			jobs[i].profit = profit[i];
			jobs[i].deadline = deadline[i];
		}
		
		Arrays.sort(jobs, new Comparator<Job>() {

			@Override
			public int compare(Job o1, Job o2) {
				return Integer.valueOf(o2.profit).compareTo(o1.profit);
			}
			
		});
		
		int profitGantt[] = new int[findMax(deadline)];
		
		for(int i = 0 ; i < jobs.length ; i++) {
			int indx = jobs[i].deadline-1;
			if(profitGantt[indx] == 0) {
				profitGantt[indx] = jobs[i].profit;
				continue;
			}
			while(indx >= 0 && profitGantt[indx] > 0) {
				indx--;
			}
			if(indx >= 0) {
				profitGantt[indx] = jobs[i].profit;
				continue;
			}
			System.out.println("Job " + jobs[i] + " skipped");
		}
		
		return findSum(profitGantt);
	}
	
	private int findMax(int[] a) {
		int max = a[0];
		for(int i = 1 ; i < a.length ; i++) {
			if(a[i] > max)
				max = a[i];
		}
		return max;
	}
	
	private int findSum(int[] a) {
		int sum = 0;
		for(int i = 0 ; i < a.length ; i++) {
			sum += a[i];
		}
		return sum;
	}
}
