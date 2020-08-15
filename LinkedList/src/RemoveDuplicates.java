
public class RemoveDuplicates {
	public void removeDuplicates() {
		SNode head = new Merge().getSLL(new int[] { 1,1,1,1,1,1,1,1,2,3,4,4,5,6,6,6,7,7,7,8,8,8,9,10,10,10,10,11 });
		
		
		//            2  3  4  4  5  9  11         4  4 5 6  6 6 7 7 7 8 8 8 9 10 10 10 10 11	
		//                              p                                                   t           
		
		int m = 2;
		SNode curr = head, prev = null, tmp = null;
		while(curr != null) {
			int ctr = 1;
			tmp = curr;
			while(curr.next != null && curr.data == curr.next.data) {
				ctr++;
				curr = curr.next;
			}
			if(ctr <= m) {
				if(prev == null)
					prev = head;
				else
					prev = prev.next;
				while(tmp != curr) {
					prev.data = tmp.data;
					prev = prev.next;
					tmp = tmp.next;
				}
				prev.data = tmp.data;
			}
			curr = curr.next;
		}
		prev.next = null;
		
		curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}
}
