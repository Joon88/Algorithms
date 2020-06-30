import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MaxHeapPQ {
	
	private PriorityQueue<Integer> heap;
	
	public MaxHeapPQ() {
		this.heap = new PriorityQueue<Integer>(Collections.reverseOrder());
	}
	
	public MaxHeapPQ(List<Integer> heap) {
		this.heap = new PriorityQueue<Integer>(Collections.reverseOrder());
		this.heap.addAll(heap);
	}
	
	public void insert(int key) {
		heap.add(key);
	}
	
	public int getMax() {
		return heap.peek();
	}
	
	public int extractMax() {
		return heap.poll();
	}
	
	public void delete(int key) {
		heap.remove(key);
	}
	
	public String toString() {
		return "Current MaxHeap :" + heap;
	}
}
