
public class DeepCopy {
	public void deepCopySLL() {
		SNode head = new Merge().getSLL(new int[] { 3,2,6,4,2,9,6,45 });
		
		head = copySLL(head);
		SNode curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}
	
	public void deepCopyDLL() {
		DNode head = new Merge().getDLL(new int[] { 3,2,6,4,2,9,6,45 });
		
		head = copyDLL(head);
		DNode curr = head;
		DNode last = null;
		while(curr != null) {
			System.out.print(curr.data + " ");
			last = curr;
			curr = curr.right;
		}
		System.out.println();
		
		while(last != null) {
			System.out.print(last.data + " ");
			last = last.left;
		}
		System.out.println();
	}
	
	private SNode copySLL(SNode head) {
		if(head == null) {
			return null;
		}
		SNode node = new SNode(head.data);
		node.next = copySLL(head.next);
		return node;
	}
	
	private DNode copyDLL(DNode head) {
		if(head.right == null) {
			return new DNode(head.data);
		}
		DNode node = new DNode(head.data);
		DNode next = copyDLL(head.right);
		node.right = next;
		next.left = node;
		return node;
	}
}
