import java.util.Arrays;

public class Sorting {
	public static void main(String args[]) {
		int[] arr = {12,54,2321,65,32,-43,0,-1,-45,67,43,54,-1};
		System.out.println(Arrays.toString(insertionSort(arr)));
		int[] arr1 = {12,54,2321,65,32,-43,0,-1,-45,67,43,54,-1};
		System.out.println(Arrays.toString(selectionSort(arr1)));
		int[] arr2 = {12,54,2321,65,32,-43,0,-1,-45,67,43,54,-1};
		System.out.println(Arrays.toString(bubbleSort(arr2)));
		int[] arr3 = {12,54,2321,65,32,-43,0,-1,-45,67,43,54,-1};
		System.out.println(Arrays.toString(mergeSort(arr3)));
		int[] arr4 = {12,54,2321,65,32,-43,0,-1,-45,67,43,54,-1};
		System.out.println(Arrays.toString(quickSort(arr4)));
	}
	
	public static int[] insertionSort(int[] a) {
		for(int i = 1 ; i < a.length ; i++) {
			int key = a[i];
			int j = i-1;
			while(j >= 0 && a[j] > key) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = key;
		}
		return a;
	}
	
	public static int[] selectionSort(int a[]) {
		for(int i = 0 ; i < a.length-1 ; i++) {
			int min = a[i];
			int minIdx = i;
			for(int j = i+1 ; j < a.length ; j++) {
				if(a[j] < min) {
					min = a[j];
					minIdx = j;
				}
			}
			a[minIdx] = a[i];
			a[i] = min;
		}
		
		return a;
	}
	
	public static int[] bubbleSort(int a[]) {
		for(int i = 0 ; i < a.length - 1 ; i++) {
			boolean swapFlag = false;
			for(int j = 1 ; j < a.length - i ; j++) {
				if(a[j] < a[j-1]) {
					a[j] = a[j] + a[j-1];
					a[j-1] = a[j] - a[j-1];
					a[j] = a[j] - a[j-1];
					swapFlag = true;
				}
			}
			if(!swapFlag)
				break;
		}
		
		return a;
	}
	
	public static int[] mergeSort(int[] a) {
		mergeSrt(a, 0, a.length-1);
		return a;
	}
	
	private static void mergeSrt(int[] a, int beg, int end) {
		if(beg < end) {
			int mid = (beg + end)/2;
			mergeSrt(a, beg, mid);
			mergeSrt(a, mid+1, end);
			merge(a, beg, mid, end);
		}
	}
	
	private static void merge(int a[], int beg, int mid, int end) {
		int left[] = new int[mid-beg+1];
		int right[] = new int[end-mid];
		
		for(int i = 0 ; i < (mid-beg+1) ; i++) {
			left[i] = a[beg+i];
		}
		for(int i = 0 ; i < (end-mid) ; i++) {
			right[i] = a[mid+1+i];
		}
		
		int i=0,j=0;
		for(int k = beg ; k <= end ; k++) {
			if(i == mid-beg+1) {
				a[k] = right[j];
				j++;
			}else if(j == end-mid) {
				a[k] = left[i];
				i++;
			}else if(left[i] < right[j]) {
				a[k] = left[i];
				i++;
			}else {
				a[k] = right[j];
				j++;
			}
		}
	}
	
	public static int[] quickSort(int[] a) {
		quickSrt(a, 0, a.length-1);
		return a;
	}
	
	private static void quickSrt(int[] a, int beg, int end) {
		if(beg < end) {
			int mid = partition(a, beg, end);
			quickSrt(a, beg, mid-1);
			quickSrt(a, mid+1, end);
		}
	}
	
	private static int partition(int[] a, int beg, int end) {
		int ptr = beg-1;
		int pivot = a[end];
		for(int i = beg; i < end ; i++) {
			if(a[i] < pivot) {
				ptr++;
				if(ptr < i) {
					a[i] = a[i] + a[ptr];
					a[ptr] = a[i] - a[ptr];
					a[i] = a[i] - a[ptr];
				}
			}
		}
		ptr++;
		a[end] = a[ptr];
		a[ptr] = pivot;
		return ptr;
	}
}
