
public class Main {
	public static void main(String args[]) {
		Merge m = new Merge();
		m.mergeSinglyLinkedList();
		m.mergeDoublyLinkedList();
		
		Reverse r = new Reverse();
		r.reverseSLL();
		
		Cyclicity c = new Cyclicity();
		//c.isCyclic();
		c.overlapCheck();
		
		RemoveDuplicates rd = new RemoveDuplicates();
		rd.removeDuplicates();
		
		DeepCopy dc = new DeepCopy();
		dc.deepCopySLL();
		dc.deepCopyDLL();
	}
}
