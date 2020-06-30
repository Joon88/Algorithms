import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// O(V+E) time and O(V) space (for detecting cycle in directed graphs, supports self loops too)
public class DFSCycleDetection {
	private Map<Integer, List<Integer>> graph = new HashMap<>();

	private void addNode(int node) {
		graph.putIfAbsent(node, new LinkedList<Integer>());
	}

	public void addEdge(int src, int dest) {
		addNode(src);
		addNode(dest);

		graph.get(src).add(dest);
	}

	public void printGraph() {
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			for (int i : entry.getValue())
				System.out.print(entry.getKey() + " --> " + i);
		}
	}

	public boolean isCyclePresent() {
		// int src = graph.keySet().iterator().next();
		boolean isCycle = false;
		Map<Integer, Boolean> visited = new HashMap<>();
		Map<Integer, Boolean> inStack = new HashMap<>();
		for (int i : graph.keySet()) {
			visited.put(i, false);
			inStack.put(i, false);
		}

		// this loop is run, coz the graph might be unconnected so there maybe a forest,
		// and we need to checks cycles in all those forests
		for (int i : graph.keySet())
			if (checkCycle(i, visited, inStack)) {
				isCycle = true;
				break;
			}

		return isCycle;
	}

	private boolean checkCycle(int root, Map<Integer, Boolean> visited, Map<Integer, Boolean> inStack) {
		if (inStack.get(root))
			return true;
		if (visited.get(root))
			return false;

		visited.put(root, true);
		inStack.put(root, true);
		System.out.println(root);
		boolean isCycle = false;
		for (int i = 0; i < graph.get(root).size(); i++) {
			if (checkCycle(graph.get(root).get(i), visited, inStack)) {
				isCycle = true;
				break;
			}
		}
		inStack.put(root, false);
		return isCycle;
	}

	public boolean isCyclePresentUsingColoring() {
		Set<Integer> white = new HashSet<>(); // unvisited
		Set<Integer> grey = new HashSet<>(); // in stack
		Set<Integer> black = new HashSet<>(); // processed (visited + out of stack)

		for (int i : graph.keySet()) {
			white.add(i);
		}
		
		boolean rslt = false;
		for(int i : graph.keySet())
			if(checkCycleUsingColoring(i, white, grey, black)) {
				rslt = true;
				break;
		}
		System.out.println(white);
		System.out.println(grey);
		System.out.println(black);
		return rslt;
	}

	private boolean checkCycleUsingColoring(int root, Set<Integer> white, Set<Integer> grey, Set<Integer> black) {
		if (black.contains(root))
			return false;
		if (grey.contains(root))
			return true;
		
		white.remove(root);
		grey.add(root);
		boolean isCycle = false;
		for(int i : graph.get(root)) {
			if(checkCycleUsingColoring(i, white, grey, black)) {
				isCycle = true;
				break;
			}
		}
		grey.remove(root);
		black.add(root);
		
		return isCycle;
	}
}
