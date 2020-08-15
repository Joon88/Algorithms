import java.util.Deque;
import java.util.LinkedList;

public class MaxInStack {
	// MyStack stack = new MyStack();
	public void solve() {
		MyStack stack = new MyStack();
		stack.push(2);
		stack.push(4);
		stack.push(4);
		stack.push(1);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.getMax());
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
