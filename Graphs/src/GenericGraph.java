import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenericGraph<T> {
	private Map<T, List<T>> graph;
	private boolean directed;

	public GenericGraph(boolean directed) {
		this.directed = directed;
		graph = new HashMap<>();
	}

	public void addNode(T node) {
		graph.putIfAbsent(node, new LinkedList<>());
	}

	public void addEdge(T src, T destn) {
		addNode(src);
		addNode(destn);

		graph.get(src).add(destn);
		if (!directed)
			graph.get(destn).add(src);
	}

	public void removeNode(T node) {
		for (List<T> l : graph.values()) {
			l.remove(node);
		}
		graph.remove(node);
	}

	public void removeEdge(T src, T destn) {
		if (graph.get(src) != null)
			graph.get(src).remove(destn);
		if (graph.get(destn) != null)
			graph.get(destn).remove(src);
	}

	public List<T> getAdjacentNodes(T node) {
		return graph.get(node);
	}

	public int getNodeCount() {
		return graph.keySet().size();
	}

	public int getEdgeCount() {
		int total = 0;
		for (List<T> l : graph.values()) {
			total += l.size();
		}

		if (!directed)
			total /= 2;

		return total;
	}

	public boolean hasNode(T node) {
		return graph.containsKey(node);
	}

	public boolean hasEdge(T src, T destn) {
		boolean found = false;
		if (graph.get(src) != null && graph.get(src).contains(destn))
			found = true;
		return found;
	}

	public void printGraph() {
		for (T node : graph.keySet()) {
			System.out.println(node + " --> " + graph.get(node));
		}
	}
}
