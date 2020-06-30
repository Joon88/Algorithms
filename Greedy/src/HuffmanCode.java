import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCode {
	
	private class HNode{
		private char ch;
		private int freq;
		private HNode left;
		private HNode right;
		
		public HNode(char ch, int freq) {
			this.ch = ch;
			this.freq = freq;
			left = null;
			right = null;
		}
	}
	
	public String getHuffmanCode(String str) {
		char[] charArray = str.toCharArray();
		
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		for(int i = 0 ; i < charArray.length ; i++) {
			if(charMap.containsKey(charArray[i]))
				charMap.put(charArray[i], charMap.get(charArray[i])+1);
			else
				charMap.put(charArray[i], 1);
		}
		
		PriorityQueue<HNode> pq = new PriorityQueue<HNode>(new Comparator<HNode>() {

			@Override
			public int compare(HNode o1, HNode o2) {
				return Integer.valueOf(o1.freq).compareTo(o2.freq);
			}
		});
		
		HNode node;
		for(char c : charMap.keySet()) {
			node = new HNode(c, charMap.get(c));
			pq.add(node);
		}
		
		while(pq.size() > 1) {
			HNode leftChild = pq.poll();
			HNode rightChild = pq.poll();
			
			HNode newNode = new HNode('~', leftChild.freq + rightChild.freq);
			newNode.left = leftChild;
			newNode.right = rightChild;
			
			pq.add(newNode);
		}
		
		HNode root = pq.poll();
		
		Map<Character, String> huffmanMap = new HashMap<Character, String>(charMap.size());
		getHuffmanString(root, "", huffmanMap);
		
		StringBuilder encodedString = new StringBuilder();
		for(int i = 0 ; i < charArray.length ; i++) {
			encodedString.append(huffmanMap.get(charArray[i]));
			System.out.println(huffmanMap.get(charArray[i]) + "--" + charArray[i]);
		}
		
		return encodedString.toString();
	}
	
	private void getHuffmanString(HNode root, String code, Map<Character, String> map) {
		if(root == null)
			return;
		if(root.ch != '~') {
			map.put(root.ch, code);
			return;
		}
		getHuffmanString(root.left, code + "0", map);
		getHuffmanString(root.right, code + "1", map);
		return;
	}
}
