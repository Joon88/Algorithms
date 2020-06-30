import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Optimally merges sorted files(here, arrays) to one single file, following the logic of huffman code, so that total movements of array elements is least
public class OptimalMerge {
	
	private class OMNode {
		private List<Integer> list;
		private int len;
	}
	
	public List<Integer> optimallyMerge(Integer[]...arr){
		
		PriorityQueue<OMNode> pq = new PriorityQueue<OMNode>(new Comparator<OMNode>() {
			public int compare(OMNode o1, OMNode o2) {
				return Integer.valueOf(o1.len).compareTo(o2.len);
			}
		});
		
		for(int i = 0 ; i < arr.length ; i++) {
			OMNode node = new OMNode();
			node.list = new ArrayList<Integer>(Arrays.asList(arr[i]));
			node.len = node.list.size();
			pq.add(node);
		}
		
		while(pq.size() > 1) {
			OMNode first = pq.poll();
			OMNode second = pq.poll();
			
			OMNode newNode = new OMNode();
			newNode.list = merge(first.list, second.list);
			newNode.len = newNode.list.size();
			
			pq.add(newNode);
		}
		
		return pq.poll().list;
	}
	
	private List<Integer> merge(List<Integer> l1, List<Integer> l2){
		List<Integer> mergedList = new ArrayList<Integer>(l1.size() + l2.size());
		int i = 0, j = 0;
		for(int k = 0 ; k < l1.size() + l2.size() ; k++) {
			if(i == l1.size()) {
				mergedList.addAll(l2.subList(j, l2.size()));
				break;
			}else if(j == l2.size()) {
				mergedList.addAll(l1.subList(i, l1.size()));
				break;
			}else if(l1.get(i) < l2.get(j)) {
				mergedList.add(l1.get(i));
				i++;
			} else {
				mergedList.add(l2.get(j));
				j++;
			}
		}
		return mergedList;
	}

}
