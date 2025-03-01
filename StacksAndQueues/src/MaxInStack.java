import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class MaxInStack {
	// MyStack stack = new MyStack();
	public void solve() {
		MyStack1 stack = new MyStack1();
		stack.push(2);
		stack.push(4);
		stack.push(4);
		stack.push(1);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.getMax());
	}

	public class MyStack1 {
		private static Stack<Integer> stack = new Stack<>();
		private static int max = Integer.MIN_VALUE;

		public void push(int x) {
			if(stack.isEmpty()) {
				stack.push(x);
				max = x;
			} else if(x <= max) {
				stack.push(x);
			} else {
				// max is changing
				int val = max + x;
				stack.push(val);
				max = x;
			}
		}

		public Integer pop() {
			if (stack.isEmpty())
				return null;
			
			if (max >= stack.peek())
				return stack.pop();
			
			int oldMax = max;
			int newMax = stack.pop() - oldMax;
			max = newMax;
			return oldMax;
		}

		public Integer getMax() {
			return max;
		}
	}

	public class MyStack {
		private Deque<Integer> mainStack = new LinkedList<>();
		private Deque<Integer> maxStack = new LinkedList<>();

		public void push(int x) {
			if (maxStack.isEmpty()) {
				maxStack.addFirst(x);
				mainStack.addFirst(x);
			} else {
				int max = Math.max(maxStack.peek(), x);
				maxStack.addFirst(max);
				mainStack.addFirst(x);
			}
		}

		public Integer pop() {
			if (mainStack.isEmpty()) {
				return null;
			}
			maxStack.pop();
			return mainStack.pop();
		}

		public Integer getMax() {
			return maxStack.peek();
		}
	}

}
