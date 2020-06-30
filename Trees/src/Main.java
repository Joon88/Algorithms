
public class Main {
	public static void main(String args[]) {
		Tree t = new Tree();
		t.insert(54);
		t.insert(37);
		t.insert(61);
		t.insert(-7);
		t.insert(40);
		t.insert(55);
		t.insert(67);
		t.insert(63);
		t.insert(0);
		t.insert(-10);
		
		t.search(40);
		
		t.getMax();
		t.getMin();
		
		t.findHeight();
		
		t.printLevelOrder();
		t.printDFS();
		
		t.checkIfBST();
		
		t.delete(54);
		t.delete(37);
		t.delete(61);
		t.delete(-7);
		t.delete(40);
		t.delete(55);
		t.delete(67);
		t.delete(63);
		t.delete(0);
		t.delete(-10);
		t.printLevelOrder();
	}
}
