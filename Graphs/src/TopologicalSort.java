import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

// O(V+E) . Its used in directed acyclic graph
public class TopologicalSort {
	private Map<Integer, List<Integer>> graph = new HashMap<>();

	private void addNode(int i) {
		graph.putIfAbsent(i, new LinkedList<Integer>());
	}

	public void addEdge(int src, int des) {
		addNode(src);
		addNode(des);

		graph.get(src).add(des);
	}

	public void topologicalSort() {
		Set<Integer> visitedSet = new HashSet<>();
		Stack<Integer> processedStack = new Stack<>();
		for (int node : graph.keySet()) {
			topSort(visitedSet, processedStack, node);
		}
		
		System.out.println("Bottom to top of stack : " + processedStack);
		System.out.println("Stack top : " + processedStack.peek());
	}

	private void topSort(Set<Integer> visitedSet, Stack<Integer> processedStack, int node) {
		if(!visitedSet.contains(node)) {
			visitedSet.add(node);
			for(int child : graph.get(node)) {
				topSort(visitedSet, processedStack, child);
				
			}
			processedStack.push(node);
		} else {
			return;
		}
		
	}
}
