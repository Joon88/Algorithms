
public class Reverse {
	public void reverseSLL() {
		SNode head1 = new Merge().getSLL(new int[] { 11, 3, 5, 7, 2, 1, 56, 65 });
		SNode head = recursiveReverseSLL(head1);
		SNode curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
		
		head = iterativeReverseSLL(head);
		curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
		
		head = reverse(head, 3, 7);
		curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
		
		head = reverseK(head, 8);
		curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}
	
	public SNode recursiveReverseSLL(SNode head) {
		if(head.next == null) {
			return head;
		}
		SNode h = recursiveReverseSLL(head.next);
		head.next.next = head;
		head.next = null;
		return h;
	}
	
	public SNode iterativeReverseSLL(SNode head) {
		SNode prev = null, next = null, curr = head;
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
	
	public SNode reverse(SNode head, int i, int j) {
		if(j <= i)
			return head;
		int ctr = 1;
		SNode curr = head, pre = null, prev = null, next = null, first = null;
		while(ctr++ < i) {
			pre = curr;
			curr = curr.next;
		}
		first = curr;
		//pre.next = null;
		prev = null;
		ctr--;
		while(ctr++ <= j) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		if(pre != null)
			pre.next = prev;
		else
			head = prev;
		first.next = curr;
		
		return head;
	}
	
	public SNode reverseK(SNode head, int k) {
		int size = 0;
		SNode curr = head;
		while(curr != null) {
			size++;
			curr = curr.next;
		}
		for(int i = 1; i <= size-k+1 ; i += k) {
			head = reverse(head, i, i+k-1);
		}
		return head;
	}
}
