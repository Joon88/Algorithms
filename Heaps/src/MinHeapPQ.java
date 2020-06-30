import java.util.List;
import java.util.PriorityQueue;

public class MinHeapPQ {
	
	private PriorityQueue<Integer> heap;
	
	public MinHeapPQ() {
		this.heap = new PriorityQueue<Integer>();
	}
	
	public MinHeapPQ(List<Integer> heap) {
		this.heap = new PriorityQueue<Integer>(heap);
	}
	
	public void insert(int key) {
		heap.add(key);
	}
	
	public int getMin() {
		return heap.peek();
	}
	
	public int extractMin() {
		return heap.poll();
	}
	
	public void delete(int key) {
		heap.remove(key);
	}

	public String toString() {
		return "Current MinHeap :" + heap;
	}	
}
