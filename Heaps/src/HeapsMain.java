import java.util.Arrays;

// Max-heap(using arrays)
public class HeapsMain {
	public static void main(String args[]) {
		Integer[] arr1 = {-6, 121, 544, 43, 56, 90, -4, 12, 23, 32, 21};
		//int[] arr = {12,54,2321,65,32,-43,0,-1,-45,67,43,54,-1};
		//buildMaxHeap(arr);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(heapExtractMax(arr));
		//System.out.println(Arrays.toString(arr));
		//heapIncreaseKey(arr, 4, -1);
		//heapDecreaseKey(arr, 1, -100);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(Arrays.toString(new int[0]));
		
		//MaxHeap heap1 = new MaxHeap(arr);
		//MinHeap heap = new MinHeap(arr1);
		MaxHeapPQ maxPQ = new MaxHeapPQ(Arrays.asList(arr1));
		maxPQ.insert(12);
		maxPQ.insert(54);
		maxPQ.insert(2323);
		maxPQ.insert(65);
		maxPQ.insert(32);
		maxPQ.insert(-43);
		maxPQ.insert(0);
		maxPQ.insert(-1);
		maxPQ.insert(-45);
		maxPQ.insert(67);
		maxPQ.insert(43);
		maxPQ.insert(54);
		maxPQ.insert(-1);
		
		
		System.out.println(maxPQ.getMax());
		System.out.println(maxPQ.extractMax());
		maxPQ.insert(111);
		System.out.println(maxPQ.toString());
		Arrays.asList(arr1).forEach(key -> maxPQ.delete(key));
		maxPQ.delete(12);
		maxPQ.delete(54);
		maxPQ.delete(111);
		maxPQ.delete(65);
		maxPQ.delete(32);
		maxPQ.delete(-43);
		maxPQ.delete(0);
		maxPQ.delete(-1);
		maxPQ.delete(-45);
		maxPQ.delete(67);
		maxPQ.delete(43);
		maxPQ.delete(54);
		maxPQ.delete(-1);
		System.out.println(maxPQ.toString());
		//System.out.println(heap1.toString());
//		System.out.println(heap1.getMax());
//		System.out.println(heap1.getMin());
	//	System.out.println(heap1.extractMax());
	//	System.out.println(heap.toString());
	//	heap1.insert(12);
//		heap.insert(54);
//		heap.insert(2321);
//		heap.insert(65);
//		heap.insert(32);
//		heap.insert(-43);
	//	System.out.println(heap1.toString());
//		System.out.println(heap1.getMax());
//		System.out.println(heap1.getMin());
//		System.out.println(heap1.extractMin());
//		System.out.println(heap1.extractMin());
//		System.out.println(heap1.extractMin());
//		System.out.println(heap1.extractMin());
//		System.out.println(heap1.extractMin());
//		System.out.println(heap1.extractMin());
//		heap.insert(65);
//		heap.insert(32);
//		heap.insert(-43);
//		System.out.println(heap1.toString());
//		System.out.println(heap1.getMax());
//		System.out.println(heap1.getMin());
	//	heap1.delete(0);
	//	heap.delete(12);
	//	System.out.println(heap1.toString());
	//	System.out.println(heap.toString());
		
	}
	
	public static void heapSort(int[] a) {
		int heapSize = a.length;
		buildMaxHeap(a);
		for(int i = heapSize-1 ; i > 0 ; i--) {
			a[i] = a[i] ^ a[0];
			a[0] = a[i] ^ a[0];
			a[i] = a[i] ^ a[0];
			heapSize--;
			maxHeapify(a, 0, heapSize);
		}
	}
	
	private static void maxHeapify(int[] a, int i, int heapSize) {
		int left = 2*i+1;
		int right = 2*i+2;
		int largest = i;
		
		if(left < heapSize && a[left] > a[largest]) {
			largest = left;
		}
		if(right < heapSize && a[right] > a[largest]) {
			largest = right;
		}
		
		if(i != largest) {
			a[i] = a[i] ^ a[largest];
			a[largest] = a[i] ^ a[largest];
			a[i] = a[i] ^ a[largest];
			maxHeapify(a, largest, heapSize);
		}
	}
	
	private static void buildMaxHeap(int[] a) {
		int heapSize = a.length;
		
		for(int i = (heapSize-2)/2 ; i >= 0 ; i--) {
			maxHeapify(a, i, heapSize);
		}
	}
	
	public static int heapExtractMax(int[] heap) {
		if(heap == null) {
			System.out.println("The heap is empty");
			return Integer.MIN_VALUE;
		}
		int heapSize = heap.length;
		int max = heap[0];
		heap[0] = heap[heapSize-1];
		heapSize--;
		maxHeapify(heap, 0, heapSize);
		return max;
	}
	
	public static void heapIncreaseKey(int[] heap, int pos, int newVal) {
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
	
	public static void heapDecreaseKey(int[] heap, int pos, int newVal) {
		if(heap[pos] < newVal) {
			System.out.println("New val should be less than old one");
			return;
		}
		int heapSize = heap.length;
		heap[pos] = newVal;
		maxHeapify(heap, pos, heapSize);
	}
}

/*
 *                 2122
 *                 /   \
 *               121   544
 *               / \   /  \ 
 *              43  56 90 -4
 *             /\    /\ 
 *           12 23 32 21
 * 
 *               
 * 
 *            2321
 *          /      \
 *       67          54
 *     /    \        /   \
 *    65     54      12    0
 *   /  \    /   \   /  \
 *  -1   -45 32  43 -43 -1
 * 
 * 
 */
