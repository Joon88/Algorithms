
public class Merge {
	public void mergeSinglyLinkedList() {
		SNode head1 = getSLL(new int[] { 2, 5, 7 });
		SNode head2 = getSLL(new int[] { 3, 11 });
		SNode newHead = mergeSLL(head1, head2);
		SNode newCurr = newHead;
		while(newCurr != null) {
			System.out.print(newCurr.data + " ");
			newCurr = newCurr.next;
		}
		System.out.println();
	}
	
	public void mergeDoublyLinkedList() {
		DNode h1 = getDLL(new int[] {2, 5, 7});
		DNode h2 = getDLL(new int[] {3, 11});
		DNode newHead = mergeDLL(h1, h2);
		DNode newCurr = newHead;
		while(newCurr != null) {
			System.out.print(newCurr.data + " ");
			newCurr = newCurr.right;
		}
		System.out.println();
	}

	private SNode mergeSLL(SNode head1, SNode head2) {
		SNode newHead = null, newCurr = null;

		while (head1 != null && head2 != null) {
			if (head1.data <= head2.data) {
				if (newHead == null) {
					newHead = head1;
					newCurr = newHead;
				} else {
					newCurr.next = head1;
					newCurr = newCurr.next;
				}
				head1 = head1.next;
				newCurr.next = null;
			} else {
				if (newHead == null) {
					newHead = head2;
					newCurr = newHead;
				} else {
					newCurr.next = head2;
					newCurr = newCurr.next;
				}
				head2 = head2.next;
				newCurr.next = null;
			}
		}
		if (head1 != null) {
			newCurr.next = head1;
		} else {
			newCurr.next = head2;
		}
		return newHead;
	}

	public SNode getSLL(int[] x) {
		SNode head = null, curr = null;
		for (int a : x) {
			if (head == null) {
				head = new SNode(a);
				curr = head;
			} else {
				curr.next = new SNode(a);
				curr = curr.next;
			}
		}
		return head;
	}
	
	public DNode getDLL(int[] x) {
		DNode head = null, curr = null, tmp = null;
		for(int a : x) {
			if(head == null) {
				head = new DNode(a);
				curr = head;
			} else {
				curr.right = new DNode(a);
				tmp = curr;
				curr = curr.right;
				curr.left = tmp;
			}
		}
		return head;
	}
	
	private DNode mergeDLL(DNode h1, DNode h2) {
		DNode newHead = null, newCurr = null;
		while(h1 != null && h2 != null) {
			if(h1.data <= h2.data) {
				if(newHead == null) {
					newHead = h1;
					newCurr = newHead;
				} else {
					newCurr.right = h1;
					h1.left = newCurr;
					newCurr = newCurr.right;
				}
				h1 = h1.right;
				newCurr.right = null;
			} else{
				if(newHead == null) {
					newHead = h2;
					newCurr = newHead;
				} else {
					newCurr.right = h2;
					h2.left = newCurr;
					newCurr = newCurr.right;
				}
				h2 = h2.right;
				newCurr.right = null;
			}
		}
		if(h1 != null) {
			newCurr.right = h1;
			h1.left = newCurr;
		} else {
			newCurr.right = h2;
			h2.left = newCurr;
		}
		return newHead;
	}
}
