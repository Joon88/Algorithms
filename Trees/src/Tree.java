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
	}
	 
	public void insert(int data) {
		root = insertInTree(root, data);
	}
	
	private Node insertInTree(Node root, int data) {
		if(root == null) {
			root = new Node(data);
		} else if(data <= root.data) {
			root.left = insertInTree(root.left, data);
		} else {
			root.right = insertInTree(root.right, data);
		}
		return root;
	}
	
	public void search(int data) {
		if(searchInTree(root, data))
			System.out.println("Found!");
		else
			System.out.println("Not Found!");
	}
	// O(log n) | space = O(log n)
	private boolean searchInTree(Node root, int data) {
		if(root == null)
			return false;
		else if(root.data == data) {
			return true;
		} else if(data < root.data) {
			return searchInTree(root.left ,data);
		} else {
			return searchInTree(root.right ,data);
		}
	}
	
	public void getMin() {
		if(root == null) {
			System.out.println("Empty tree");
			return;
		}
		System.out.println("The minimum number is : " + getMinInBst(root));
	}
	
	public void getMax() {
		if(root == null) {
			System.out.println("Empty tree");
			return;
		}
		System.out.println("The maximum number is : " + getMaxInBst(root));
	}
	// O(log n) | space = O(log n)
	private int getMinInBst(Node root) {
		if(root.left == null)
			return root.data;
		return getMinInBst(root.left);
	}
	// O(log n) | space = O(log n)
	private int getMaxInBst(Node root) {
		if(root.right == null)
			return root.data;
		return getMaxInBst(root.right);
	}
	
	public void findHeight() {
		System.out.println("The height is : " + getHeight(root));
	}
	// O(n) | space = O(log n)
	private int getHeight(Node root) {
		if(root == null)
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
		if(root == null)
			return;
		
		System.out.print(root.data + " ");
		
		if(root.left != null)
			q.add(root.left);
		if(root.right != null)
			q.add(root.right);
		
		while(!q.isEmpty()) {
			bfsRecursion(q.poll(), q);
		}
	}
	
	private void bfsIteration(Node root) {
		if(root == null)
			return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			Node curr = q.poll();
			System.out.print(curr.data + " ");
			if(curr.left != null)
				q.add(curr.left);
			if(curr.right != null)
				q.add(curr.right);
		}
	}
	
	private void preorder(Node root) {
		if(root == null)
			return;
		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);
	}
	
	private void inorder(Node root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}
	
	private void iterativeInorder(Node root) {
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while(root != null){
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
        while(!stack.isEmpty() || root != null){
            while(root != null){
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
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            
            Node current = stack.peek();
            if(current.right == null || current.right == tmp) {
            	tmp = stack.pop();
            	System.out.print(tmp.data + " ");
            	root = null;
            } else{
            	root = current.right;
            }
        }
    }
	
	private void postorder(Node root) {
		if(root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data + " ");
	}
	
	public void checkIfBST() {
		//root.left.right.data = 56;
		System.out.println("Is it a BST? " + isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
	
	private boolean isBST(Node root, int min, int max) {
		if(root == null)
			return true;
		if(min > root.data || max <= root.data)
			return false;
		return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
	}
	
	public void delete(int data) {
		root = deleteNode(root, data);
	}
	
	private Node deleteNode(Node root, int data) {
		if(root == null)
			return null;
		if(root.data == data) {
			System.out.println("Found and deleted : " + root.data);
			if(root.left == null && root.right == null) {
				return null;
			} else if(root.left == null) {
				return root.right;
			} else if(root.right == null) {
				return root.left;
			} else {
				Node curr = root.right;
				Node tmp = curr;
				while(curr.left != null) {
					curr = curr.left;
				}
				root.data = curr.data;
				
				if(tmp == curr) {
					root.right = deleteNode(curr, curr.data);
				} else {
					while(tmp.left != curr) {
						tmp = tmp.left;
					}
					tmp.left = deleteNode(curr, curr.data);
				}
				return root;
			}
			
		} else if(data < root.data) {
			root.left = deleteNode(root.left, data);
		} else {
			root.right = deleteNode(root.right, data);
		}
		return root;
	}
}
