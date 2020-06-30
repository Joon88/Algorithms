import java.util.Arrays;

public class MinHeap {
	private int[] heap;
	private int maxHeapSize;
	private int heapSize;
	
	public MinHeap(int maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
		this.heap = new int[maxHeapSize];
		this.heapSize = 0;
	}
	
	public MinHeap(int[] heap) {
		this.heap = heap;
		this.maxHeapSize = heap.length;
		this.heapSize = 0;
		buildMinHeap();
	}
	
	public void insert(int key) {
		if(heapSize == maxHeapSize) {
			System.out.println("Heap is full");
			return;
		}
		heap[heapSize++] = Integer.MAX_VALUE;
		heapDecreaseKey(heapSize-1, key);
	}
	
	public int extractMin() {
		if(heapSize == 0) {
			System.out.println("The heap is empty");
			return Integer.MAX_VALUE;
		}
		
		int min = heap[0];
		heap[0] = heap[--heapSize];
		minHeapify(0);
		
		return min;
	}
	
	public int getMax() {
		if(heapSize == 0) {
			System.out.println("The heap is empty");
			return Integer.MIN_VALUE;
		}
		int max = heap[((heapSize-2)/2) + 1];
		for(int i = ((heapSize-2)/2) + 2 ; i < heapSize ; i++) {
			if(heap[i] > max) {
				max = heap[i];
			}
		}
		return max;
	}
	
	public int getMin() {
		if(heapSize == 0) {
			System.out.println("The heap is empty");
			return Integer.MAX_VALUE;
		}
		return heap[0];
	}
	
	public void delete(int key) {
		if(heapSize == 0) {
			System.out.println("The heap is empty");
			return;
		}
		
		int keyIdx = -1;
		for(int i = 0 ; i < heapSize ; i++) {
			if(heap[i] == key) {
				keyIdx = i;
				break;
			}
		}
		if(keyIdx == -1) {
			System.out.println("Key not found");
			return;
		}
		int lastElt = heap[--heapSize];
		if(lastElt < heap[keyIdx]) {
			heapDecreaseKey(keyIdx, lastElt);
		}else {
			heap[keyIdx] = lastElt;
			minHeapify(keyIdx);
		}
	}
	
	private void minHeapify(int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		int smallest = i;
		
		if(left < heapSize && heap[left] < heap[smallest]) {
			smallest = left;
		}
		if(right < heapSize && heap[right] < heap[smallest]) {
			smallest = right;
		}
		
		if(i != smallest) {
			heap[i] = heap[i] ^ heap[smallest];
			heap[smallest] = heap[i] ^ heap[smallest];
			heap[i] = heap[i] ^ heap[smallest];
			minHeapify(smallest);
		}
	}
	
	public void heapDecreaseKey(int pos, int newVal) {
		if(heap[pos] < newVal) {
			System.out.println("The new key should be less that the old one");
			return;
		}
		heap[pos] = newVal;
		int i = pos;
		while(i > 0 && heap[(i-1)/2] > heap[i]) {
			heap[(i-1)/2] = heap[(i-1)/2] ^ heap[i];
			heap[i] = heap[(i-1)/2] ^ heap[i];
			heap[(i-1)/2] = heap[(i-1)/2] ^ heap[i];
			i = (i-1)/2;
		}
	}
	
	private void buildMinHeap() {
		heapSize = heap.length;
		
		for(int i = (heapSize-2)/2 ; i >= 0 ; i--) {
			minHeapify(i);
		}
	}
	
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(heap, 0, heapSize));
	}
}
