import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {
	private static Integer xyz;

	public static void main(String args[]) {
		Integer[] a = { 4, 2, 7, 6, 2, 1, -1 };
		int[] arr = { 1, 3, 4 };

		// func(a[6]);
		// Arrays.sort(a, Collections.reverseOrder());
		Collections.sort(Arrays.asList(a));
		// Collections.reverse(Arrays.asList(a));
		System.out.println(Arrays.toString(a));

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
			public int compare(Integer lhs, Integer rhs) {
				if (lhs < rhs)
					return +1;
				if (lhs.equals(rhs))
					return 0;
				return lhs.compareTo(rhs);
			}
		});

		Test t = new Test();
		t.i = 5;
		List<Test> l = new ArrayList<Test>();
		l.add(t);
		l.get(0).i++;
		System.out.println(t.i);

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Math.pow(2, 31));
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MIN_VALUE - 1);
		System.out.println(Integer.MAX_VALUE + 1);

		int[][] x = { { 1, 2, 3 }, { 3, 4, 5 }, { 3, 4, 5 }, { 3, 4, 5 }, { 3, 4, 5 }, { 3, 4, 5 } };
		System.out.println(x.length);

		System.out.println(xyz);
		int kk = 1, ll = 2;
		System.out.println((null != null));
		System.out.println(Long.toBinaryString((long) Math.pow(2, 62)));

		Stack<Integer> l2 = new Stack<>();
		StringBuilder s = new StringBuilder("rfre");
		
		String str = "fr";
		// str
		char ew = 'A' + 1;
		System.out.println(ssDecodeColID("AA"));
	}

	public static int ssDecodeColID(final String col) {
		int result = 0;
		for (int i = 0; i < col.length() ; i++) {
			char c = col.charAt(i) ;
			//result = (result+1)*26 + c - 'A';
			result = (result)*26 + c - 'A' + 1;
		}
		return result-1;
		}

	public static List<Integer> func() {
		List<Integer> l = new ArrayList<>();
		l.add(1);
		System.out.println("--" + l);
		return l;
	}
}
