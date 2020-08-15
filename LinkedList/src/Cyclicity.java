
public class Cyclicity {
	public void isCyclic() {
		SNode head = new Merge().getSLL(new int[] { 11, 3, 5, 7, 2, 1, 56, 65 });
		SNode curr = head;
		while(curr.next != null)
			curr = curr.next;
		curr.next = head;
		SNode rslt = checkCycle(head);
		System.out.println("Cyclicity result : " + (rslt == null ? "false" : "true at : " + rslt.data));
	}
	
	private SNode checkCycle(SNode head) {
		SNode slow = head, fast = head;
		while(fast != null && fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast) {
				slow = head;
				while(slow != fast) {
					slow = slow.next;
					fast = fast.next;
				}
				//System.out.println("Cycle found at : " + fast.data);
				return fast;
			}
		}
		//System.out.println("Cycle not found");
		return null;
	}
	
	public void overlapCheck() {
		SNode head = new Merge().getSLL(new int[] { 11, 3, 5, 7, 2, 1, 56, 65 });
		SNode head1 = new Merge().getSLL(new int[] { 11, 3, 5, 7, 2, 1, 56 });
		SNode curr1 = head1;
		while(curr1.next != null)
			curr1 = curr1.next;
		curr1.next = head.next.next.next;
		
		SNode rslt = checkNonCyclicOverlap(head, head1);
		
		System.out.println("Overlap result : " + (rslt == null ? "false" : "true at : " + rslt.data));
		
		// Overlap of linked lists with/without cycles
		SNode curr = head;
		while(curr.next != null)
			curr = curr.next;

		curr.next = head.next;	
		
		rslt = checkCyclicOverlap(head, head1);
		System.out.println("Overlap with cycles result : " + (rslt == null ? "false" : "true at : " + rslt.data));
	}
	
	private SNode checkNonCyclicOverlap(SNode head1, SNode head2) {
		SNode curr1 = head1, curr2 = head2, end = null;
		while(true) {
			if(curr1 == curr2) {
				//System.out.println("Overlap at : " + curr1.data);
				return curr1;
			}
			if(curr1.next == null && end == null) {
				end = curr1;
				curr1 = head2;
			} else if(curr1.next == null && end != curr1) {
				return null;
			} else if(curr1.next == null && end == curr1) {
				curr1 = head2;
				continue;
			} else {
				curr1 = curr1.next;
			}
			
			if(curr2.next == null && end == null) {
				end = curr2;
				curr2 = head1;
				continue;
			} else if(curr2.next == null && end != curr2) {
				return null;
			} else if(curr2.next == null && end == curr2) {
				curr2 = head1;
			} else {
				curr2 = curr2.next;
			}
		}
	}
	
	private SNode checkCyclicOverlap(SNode h1, SNode h2) {
		SNode croot1 = checkCycle(h1);
		SNode croot2 = checkCycle(h2);
		if((croot1 == null && croot2 != null) || (croot2 == null && croot1 != null))
			return null;
		else if(croot1 == null && croot2 == null) {
			return checkNonCyclicOverlap(h1, h2);
		}
		int distTillCycleBeg1 = getDistance(h1, croot1);
		int distTillCycleBeg2 = getDistance(h2, croot2);
		SNode curr1 = h1, curr2 = h2;
		if(distTillCycleBeg1 > distTillCycleBeg2) {
			int diff = distTillCycleBeg1 - distTillCycleBeg2;
			while(diff-- > 0) {
				curr1 = curr1.next;
			}
		} else {
			int diff = distTillCycleBeg2 - distTillCycleBeg1;
			while(diff-- > 0) {
				curr2 = curr2.next;
			}
		}
		while(curr1 != croot1 && curr2 != croot2 && curr1 != curr2) {
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		
		return curr1 == curr2 ? curr1 : croot2;
		// or
		// return curr1 == curr2 ? curr1 : croot1;
	}
	
	private int getDistance(SNode h, SNode root) {
		int size = 0;
		while(h != root) {
			size++;
			h = h.next;
		}
		return size;
	}
}
