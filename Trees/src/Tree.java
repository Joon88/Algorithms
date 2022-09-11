import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {
	private Node root;

	private class Node {
		private Node left;
		private int data;
		private Node right;

		private Node(int data) {
			this.left = null;
			this.data = data;
			this.right = null;
		}

		public String toString() {
			return String.valueOf(data);
		}
	}

	public void insert(int data) {
		// root = insertInTree(root, data);
		root = insertIterative(root, data);
	}

	private Node insertInTree(Node root, int data) {
		if (root == null) {
			root = new Node(data);
		} else if (data <= root.data) {
			root.left = insertInTree(root.left, data);
		} else {
			root.right = insertInTree(root.right, data);
		}
		return root;
	}

	private Node insertIterative(Node root, int data) {
		Node node = new Node(data);
		if (root == null)
			return node;

		Node parent = null, curr = root;
		while (curr != null) {
			parent = curr;
			if (data <= curr.data)
				curr = curr.left;
			else
				curr = curr.right;
		}
		if (data <= parent.data)
			parent.left = node;
		else
			parent.right = node;

		return root;
	}

	public void search(int data) {
		// boolean found = searchInTree(root, data);
		boolean found = searchIterative(root, data);
		if (found)
			System.out.println("Found!");
		else
			System.out.println("Not Found!");
	}

	// O(log n) | space = O(log n)
	private boolean searchInTree(Node root, int data) {
		if (root == null)
			return false;
		else if (root.data == data) {
			return true;
		} else if (data < root.data) {
			return searchInTree(root.left, data);
		} else {
			return searchInTree(root.right, data);
		}
	}

	private boolean searchIterative(Node root, int data) {
		while (root != null) {
			if (root.data == data) {
				return true;
			}
			if (data <= root.data)
				root = root.left;
			else
				root = root.right;
		}
		return false;
	}

	public void getMin() {
		if (root == null) {
			System.out.println("Empty tree");
			return;
		}
		System.out.println("The minimum number is : " + getMinInBst(root));
	}

	public void getMax() {
		if (root == null) {
			System.out.println("Empty tree");
			return;
		}
		System.out.println("The maximum number is : " + getMaxInBst(root));
	}

	// O(log n) | space = O(log n)
	private int getMinInBst(Node root) {
		if (root.left == null)
			return root.data;
		return getMinInBst(root.left);
	}

	// O(log n) | space = O(log n)
	private int getMaxInBst(Node root) {
		if (root.right == null)
			return root.data;
		return getMaxInBst(root.right);
	}

	public void findHeight() {
		System.out.println("The height is : " + getHeight(root));
	}

	// O(n) | space = O(log n)
	private int getHeight(Node root) {
		if (root == null)
			return -1;
		return Math.max(1 + getHeight(root.left), 1 + getHeight(root.right));
	}

	public void printLevelOrder() {
		Queue<Node> q = new LinkedList<>();
		System.out.println("BFS : ");
		bfsRecursion(root, q);
		System.out.println();
		System.out.println("BFS Iterative : ");
		bfsIteration(root);
		System.out.println();
	}

	public void printDFS() {
		System.out.println("Preorder traversal : ");
		preorder(root);
		System.out.println();
		System.out.println("Iterative Preorder traversal : ");
		iterativePreorder(root);
		System.out.println();
		System.out.println("Inorder traversal : ");
		inorder(root);
		System.out.println();
		System.out.println("Iterative Inorder traversal : ");
		iterativeInorder(root);
		System.out.println();
		System.out.println("Postorder traversal : ");
		postorder(root);
		System.out.println();
		System.out.println("Iterative Postorder traversal : ");
		iterativePostorder(root);
		System.out.println();
	}

	private void bfsRecursion(Node root, Queue<Node> q) {
		if (root == null)
			return;

		System.out.print(root.data + " ");

		if (root.left != null)
			q.add(root.left);
		if (root.right != null)
			q.add(root.right);

		while (!q.isEmpty()) {
			bfsRecursion(q.poll(), q);
		}
	}

	private void bfsIteration(Node root) {
		if (root == null)
			return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			Node curr = q.poll();
			System.out.print(curr.data + " ");
			if (curr.left != null)
				q.add(curr.left);
			if (curr.right != null)
				q.add(curr.right);
		}
	}

	private void preorder(Node root) {
		if (root == null)
			return;
		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);
	}

	private void inorder(Node root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}

	private void iterativeInorder(Node root) {
		Stack<Node> stack = new Stack<>();
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			Node current = stack.pop();
			System.out.print(current.data + " ");
			root = current.right;
		}
	}

	private void iterativePreorder(Node root) {
		Stack<Node> stack = new Stack<>();
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				System.out.print(root.data + " ");
				root = root.left;
			}
			Node current = stack.pop();
			root = current.right;
		}
	}

	private void iterativePostorder(Node root) {
		Stack<Node> stack = new Stack<>();
		Node tmp = null;
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}

			Node current = stack.peek();
			if (current.right == null || current.right == tmp) {
				tmp = stack.pop();
				System.out.print(tmp.data + " ");
				root = null;
			} else {
				root = current.right;
			}
		}
	}

	private void postorder(Node root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data + " ");
	}

	public void checkIfBST() {
		// root.left.right.data = 56;
		System.out.println("Is it a BST? " + isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	private boolean isBST(Node root, int min, int max) {
		if (root == null)
			return true;
		if (min > root.data || max <= root.data)
			return false;
		return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
	}

	public void delete(int data) {
		root = deleteNode(root, data);
	}

	private Node deleteNode(Node root, int data) {
		if (root == null)
			return null;
		if (root.data == data) {
			System.out.println("Found and deleted : " + root.data);
			if (root.left == null && root.right == null) {
				return null;
			} else if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else {
				Node curr = root.right;
				Node tmp = curr;
				while (curr.left != null) {
					curr = curr.left;
				}
				root.data = curr.data;

				if (tmp == curr) {
					root.right = deleteNode(curr, curr.data);
				} else {
					while (tmp.left != curr) {
						tmp = tmp.left;
					}
					tmp.left = deleteNode(curr, curr.data);
				}
				return root;
			}

		} else if (data < root.data) {
			root.left = deleteNode(root.left, data);
		} else {
			root.right = deleteNode(root.right, data);
		}
		return root;
	}

	public boolean checkSimilarTrees(Node r1, Node r2) {
		if (r1 == r2) // null == null is true in Java
			return true;
		if (r1 == null || r2 == null)
			return false;
		return (r1.data == r2.data) && checkSimilarTrees(r1.left, r2.left) && checkSimilarTrees(r1.right, r2.right);
	}

	public void getSize() {
		System.out.println(getNumNodes(root));
	}

	private int getNumNodes(Node root) {
		if (root == null)
			return 0;
		return 1 + getNumNodes(root.left) + getNumNodes(root.right);
	}

	public void checkRootToLeafSum(int sum) {
		Stack<Integer> stack = new Stack<>();
		if (checkSum(root, sum, stack)) {
			System.out.println(stack);
		} else {
			System.out.println("Not found!!");
		}
	}

	private boolean checkSum(Node root, int sum, Stack<Integer> stack) {
		if (root == null)
			return false;
		if (root.left == null && root.right == null) {
			if (root.data == sum) {
				stack.push(root.data);
				return true;
			}
			return false;
		}

		boolean rslt = checkSum(root.left, sum - root.data, stack) || checkSum(root.right, sum - root.data, stack);
		if (rslt)
			stack.push(root.data);
		return rslt;
	}

	public void levelByLevel() {
		levelByLevelPrint(root);
		lByl(root);
	}

	private void levelByLevelPrint(Node root) {
		if (root == null)
			return;
		Queue<Node> q1 = new LinkedList<>();
		Queue<Node> q2 = new LinkedList<>();
		q1.add(root);

		while (!q1.isEmpty() || !q2.isEmpty()) {
			// if(!q1.isEmpty())
			// System.out.println(q1);
			System.out.println();
			while (!q1.isEmpty()) {
				Node n = q1.poll();
				System.out.print(n.data + " ");
				if (n.left != null)
					q2.add(n.left);
				if (n.right != null)
					q2.add(n.right);
			}

			// if(!q2.isEmpty())
			// System.out.println(q2);
			System.out.println();
			while (!q2.isEmpty()) {
				Node n = q2.poll();
				System.out.print(n.data + " ");
				if (n.left != null)
					q1.add(n.left);
				if (n.right != null)
					q1.add(n.right);
			}
		}
	}

	private void lByl(Node root) {
		if (root == null)
			return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		System.out.println();

		while (true) {
			Node n = q.poll();
			if (n == null && q.isEmpty()) {
				break;
			}
			if (n == null) {
				q.add(null);
				System.out.println();
				continue;
			}
			System.out.print(n.data + " ");
			if (n.left != null)
				q.add(n.left);
			if (n.right != null)
				q.add(n.right);
		}
		System.out.println();
	}

	public void reverseLevelOrderTraversal() {
		reverseLOT(root);
	}

	private void reverseLOT(Node root) {
		if (root == null)
			return;

		Queue<Node> q = new LinkedList<>();
		Stack<Node> s = new Stack<>();
		q.add(root);

		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.right != null)
				q.add(n.right);
			if (n.left != null)
				q.add(n.left);
			s.push(n);
		}
		Collections.reverse(s);
		System.out.println(s);
	}

	public void zigZagTraversal() {
		spiral(root);
	}

	private void spiral(Node root) {
		if (root == null)
			return;

		// if I use LinkedList here, I can use null as my delimeter, instead of
		// Integer.MAX_VALUE node
		Deque<Node> dq = new ArrayDeque<>();
		Node delimeter = new Node(Integer.MAX_VALUE);
		dq.add(delimeter);
		dq.addFirst(root);
		System.out.println();

		while (dq.peekFirst() != delimeter) {
			while (dq.peekFirst() != delimeter) {
				Node n = dq.pollFirst();
				System.out.print(n.data + " ");
				if (n.left != null)
					dq.addLast(n.left);
				if (n.right != null)
					dq.addLast(n.right);
			}

			while (dq.peekLast() != delimeter) {
				Node n = dq.pollLast();
				System.out.print(n.data + " ");
				if (n.right != null)
					dq.addFirst(n.right);
				if (n.left != null)
					dq.addFirst(n.left);
			}
		}
		System.out.println();
	}

	public void lowestCommonAncestorBST(int x, int y) {
		System.out.println("The lowest common ancestor of BST is : " + lcaBST(root, x, y).data);
	}

	private Node lcaBST(Node root, int x, int y) {
		if (root == null)
			return null;
		if (root.data > Math.max(x, y))
			return lcaBST(root.left, x, y);
		if (root.data < Math.min(x, y))
			return lcaBST(root.right, x, y);
		return root;
	}

	public void printAllRootToLeafPaths() {
		Stack<Node> s = new Stack<>();
		printAllPaths(root, s);
	}

	private void printAllPaths(Node root, Stack<Node> s) {
		if (root == null)
			return;
		s.push(root);
		if (root.left == null && root.right == null) {
			System.out.println(s);
			s.pop();
			return;
		}
		printAllPaths(root.left, s);
		printAllPaths(root.right, s);
		s.pop();
	}

	public void lowestCommonAncestor(int x, int y) {
		System.out.println("The lowest common ancestor of binary tree is : " + lca(root, x, y).data);
	}

	private Node lca(Node root, int x, int y) {
		if (root == null)
			return null;
		if (root.data == x || root.data == y) {
			return root;
		}
		Node fromLeft = lca(root.left, x, y);
		Node fromRight = lca(root.right, x, y);
		if (fromLeft != null && fromRight != null)
			return root;
		else if (fromLeft != null)
			return fromLeft;
		else
			return fromRight;

	}

	private class Info {
		private boolean isBST;
		private int size;
		private int min;
		private int max;

		public Info(boolean isBST, int size, int min, int max) {
			this.isBST = isBST;
			this.size = size;
			this.min = min;
			this.max = max;
		}
	}

	public void getMaxBSTSubtreeSize() {
		System.out.println(getMaxST(root).size);
	}

	private Info getMaxST(Node root) {
		if (root == null)
			return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

		Info leftInfo = getMaxST(root.left);
		Info rightInfo = getMaxST(root.right);
		Info rootInfo = new Info(false, 0, 0, 0);

		if (leftInfo.isBST && rightInfo.isBST && leftInfo.max <= root.data && rightInfo.min > root.data) {
			rootInfo.isBST = true;
			rootInfo.size = 1 + leftInfo.size + rightInfo.size;
			rootInfo.min = Math.min(root.data, leftInfo.min);
			rootInfo.max = Math.max(root.data, rightInfo.max);
		} else {
			rootInfo.isBST = false;
			rootInfo.size = Math.max(leftInfo.size, rightInfo.size);
			rootInfo.min = 0;
			rootInfo.max = 0;
		}
		return rootInfo;
	}

	public int getInOrderSuccessor(int x) {
		if (root == null)
			return Integer.MIN_VALUE;
		Node anc = null, curr = root;
		while (curr != null) {
			if (x == curr.data)
				break;
			if (x <= curr.data) {
				anc = curr;
				curr = curr.left;
			} else
				curr = curr.right;
		}
		if (curr == null)
			return Integer.MIN_VALUE;
		if (curr.right != null) {
			Node tmp = curr.right;
			while (tmp.left != null)
				tmp = tmp.left;
			return tmp.data;
		} else {
			return anc == null ? Integer.MIN_VALUE : anc.data;
		}
	}

	// Morris Inorder takes space complexity O(1) as it doesn't use stack, better
	// that normal approach
	public void morrisInorderTraversal() {
		System.out.println("Morris inorder traversal : ");
		morrisInorder(root);
		System.out.println();
	}

	// Morris Preorder takes space complexity O(1) as it doesn't use stack, better
	// that normal approach
	public void morrisPreorderTraversal() {
		System.out.println("Morris preorder traversal : ");
		morrisPreorder(root);
		System.out.println();
	}

	private void morrisInorder(Node root) {
		while (root != null) {
			if (root.left == null) {
				System.out.print(root.data + " ");
				root = root.right;
			} else {
				Node inorderPredecessor = getInorderPredecessor(root);
				if (inorderPredecessor.right == null) {
					inorderPredecessor.right = root;
					root = root.left;
				} else {
					inorderPredecessor.right = null;
					System.out.print(root.data + " ");
					root = root.right;
				}
			}
		}
	}

	private void morrisPreorder(Node root) {
		while (root != null) {
			if (root.left == null) {
				System.out.print(root.data + " ");
				root = root.right;
			} else {
				Node inorderPredecessor = getInorderPredecessor(root);
				if (inorderPredecessor.right == null) {
					inorderPredecessor.right = root;
					System.out.print(root.data + " ");
					root = root.left;
				} else {
					inorderPredecessor.right = null;
					root = root.right;
				}
			}
		}
	}

	private Node getInorderPredecessor(Node root) {
		Node curr = root.left;
		while (curr.right != null && curr.right != root) {
			curr = curr.right;
		}
		return curr;
	}
}
