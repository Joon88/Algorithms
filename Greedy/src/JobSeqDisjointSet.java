import java.util.Arrays;
import java.util.Comparator;

// Job sequencing using disjoint sets brings down the time of job sequencing with deadlines to about O(nlogn)
public class JobSeqDisjointSet {
	private static class Job{
		private int id;
		private int profit;
		private int deadline;
		
		public Job(int id, int prof, int dead) {
			this.id = id;
			profit = prof;
			deadline = dead;
		}
		
		public String toString() {
			return id + "--" + profit;
		}
	}
	private static class DisjointSet{
		private int[] parent;
		
		public DisjointSet(int size) {
			parent = new int[size];
		}
		
		public void makeSet(int i) {
			parent[i] = i;
		}
		
		public int findSet(int i) {
			if(parent[i] == i)
				return i;
			
			return (parent[i] = findSet(parent[i]));
		}
		
		public void merge(int i, int j) {
			int set1 = findSet(i);
			int set2 = findSet(j);
			parent[set2] = set1;
		}
	}
	
	public int getMaxProfit(int[] profit, int[] deadline) {
		int maxDeadline = Integer.MIN_VALUE;
		for(int i : deadline) {
			maxDeadline = Math.max(maxDeadline, i);
		}
		
		DisjointSet ds = new DisjointSet(maxDeadline + 1);
		
		for(int i = 0 ; i <= maxDeadline ; i++) {
			ds.makeSet(i);
		}
		
		Job[] jobs = new Job[profit.length];
		for(int i = 0 ; i < profit.length ; i++) {
			jobs[i] = new Job(i, profit[i], deadline[i]);
		}
		
		Arrays.sort(jobs, new Comparator<Job>() {
			public int compare(Job o1, Job o2) {
				return Integer.valueOf(o2.profit).compareTo(o1.profit);
			}
		});
		
		int totalProfit = 0;
		for(Job job : jobs) {
			int availableSlot = ds.findSet(job.deadline);
			if(availableSlot > 0) {
				ds.merge(availableSlot-1, availableSlot);
				totalProfit += job.profit;
			}
		}
		return totalProfit;
	}
}
