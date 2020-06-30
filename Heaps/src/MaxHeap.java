import java.util.Arrays;

public class MaxHeap {
	private int[] heap;
	private int maxHeapSize;
	private int heapSize;
	
	public MaxHeap(int maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
		this.heap = new int[maxHeapSize];
		this.heapSize = 0;
	}
	
	public MaxHeap(int[] heap) {
		this.heap = heap;
		this.maxHeapSize = heap.length;
		this.heapSize = 0;
		buildMaxHeap();
	}
	
	public void insert(int key) {
		if(heapSize == maxHeapSize) {
			System.out.println("Heap is full");
			return;
		}
		heap[heapSize++] = Integer.MIN_VALUE;
		heapIncreaseKey(heapSize-1, key);
	}
	
	public int extractMax() {
		if(heapSize == 0) {
			System.out.println("The heap is empty");
			return Integer.MIN_VALUE;
		}
		
		int max = heap[0];
		heap[0] = heap[--heapSize];
		maxHeapify(0);
		
		return max;
	}
	
	public int getMin() {
		if(heapSize == 0) {
			System.out.println("The heap is empty");
			return Integer.MAX_VALUE;
		}
		int min = heap[((heapSize-2)/2) + 1];
		for(int i = ((heapSize-2)/2) + 2 ; i < heapSize ; i++) {
			if(heap[i] < min) {
				min = heap[i];
			}
		}
		return min;
	}
	
	public int getMax() {
		if(heapSize == 0) {
			System.out.println("The heap is empty");
			return Integer.MIN_VALUE;
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
		if(lastElt > heap[keyIdx]) {
			heapIncreaseKey(keyIdx, lastElt);
		}else {
			heap[keyIdx] = lastElt;
			maxHeapify(keyIdx);
		}
	}
	
	private void maxHeapify(int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		int largest = i;
		
		if(left < heapSize && heap[left] > heap[largest]) {
			largest = left;
		}
		if(right < heapSize && heap[right] > heap[largest]) {
			largest = right;
		}
		
		if(i != largest) {
			heap[i] = heap[i] ^ heap[largest];
			heap[largest] = heap[i] ^ heap[largest];
			heap[i] = heap[i] ^ heap[largest];
			maxHeapify(largest);
		}
	}
	
	public void heapIncreaseKey(int pos, int newVal) {
		if(heap[pos] > newVal) {
			System.out.println("The new key should be more that the old one");
			return;
		}
		heap[pos] = newVal;
		int i = pos;
		while(i > 0 && heap[(i-1)/2] < heap[i]) {
			heap[(i-1)/2] = heap[(i-1)/2] ^ heap[i];
			heap[i] = heap[(i-1)/2] ^ heap[i];
			heap[(i-1)/2] = heap[(i-1)/2] ^ heap[i];
			i = (i-1)/2;
		}
	}
	
	private void buildMaxHeap() {
		heapSize = heap.length;
		
		for(int i = (heapSize-2)/2 ; i >= 0 ; i--) {
			maxHeapify(i);
		}
	}
	
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(heap, 0, heapSize));
	}
}
