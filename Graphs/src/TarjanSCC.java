import java.util.*;

/*
Tarjan's Algo : to find all strongly connected components in a graph or also to find
if a directed graph is strongly connected.

Time : O(V+E) and space : O(V+E) to store the graph
 */
public class TarjanSCC {
    private static int UNVISITED = -1;
    private static int time = 0;
    private static List<List<Integer>> sccs = new ArrayList<>();
    public static void main(String args[]) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Arrays.asList(1)));
        graph.put(1, new LinkedList<>(Arrays.asList(2,3)));
        graph.put(2, new LinkedList<>(Arrays.asList()));
        graph.put(3, new LinkedList<>(Arrays.asList(4)));
        graph.put(4, new LinkedList<>(Arrays.asList(0,5,6)));
        graph.put(5, new LinkedList<>(Arrays.asList(2,6)));
        graph.put(6, new LinkedList<>(Arrays.asList(5)));
        findAllSCCs(graph);
    }

    private static void findAllSCCs(Map<Integer, List<Integer>> graph) {
        int[] low = new int[graph.size()];
        int[] disc = new int[graph.size()];
        boolean[] inStack = new boolean[graph.size()];

        Arrays.fill(low, -1);
        Arrays.fill(disc, -1);

        Stack<Integer> stack = new Stack<>();

        for(int u : graph.keySet()) {
            if(disc[u] == UNVISITED) {
                DFS(u, graph, low, disc, inStack, stack);
            }
        }
        System.out.println(sccs);
    }

    private static void DFS(int u, Map<Integer, List<Integer>> graph, int[] low, int[] disc, boolean[] inStack, Stack<Integer> stack) {

        disc[u] = low[u] = time;
        time++;

        stack.push(u);
        inStack[u] = true;

        for(int v : graph.get(u)) {
            if(inStack[v]) // this is a back edge
                low[u] = Math.min(low[u], disc[v]);
            else if(disc[v] == UNVISITED) { // this is a tree edge
                DFS(v, graph, low, disc, inStack, stack);
                low[u] = Math.min(low[u], low[v]);
            } else { // this is a cross edge
                continue;
            }
        }

        if(low[u] == disc[u]) { // this is a head of a SCC
            List<Integer> scc = new ArrayList<>();
            while(stack.peek() != u) { // popping all elements until we get to the head of SCC
                int popped = stack.pop();
                scc.add(popped);
                inStack[popped] = false;
            }
            int popped = stack.pop();
            scc.add(popped);
            inStack[popped] = false;
            sccs.add(scc);
        }
    }
}
