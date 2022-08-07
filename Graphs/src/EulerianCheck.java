import java.util.*;

public class EulerianCheck {
    public static void main(String args[]) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Arrays.asList(1)));
        graph.put(1, new LinkedList<>(Arrays.asList(0,2)));
        graph.put(2, new LinkedList<>(Arrays.asList(1,3,4)));
        graph.put(3, new LinkedList<>(Arrays.asList(2,4)));
        graph.put(4, new LinkedList<>(Arrays.asList(2,3)));

        switch(checkEuler(graph)) {
            case 0:
                System.out.println("Non-Eulerian graph");
                break;
            case 1:
                System.out.println("Eulerian Graph");
                break;
            case 2:
                System.out.println("Semi-Eulerian Graph");
                break;
        }
    }

    private static int checkEuler(Map<Integer, List<Integer>> graph) {
        // 1. Check if there are edges in the graph
        int node = -1;
        for(int u : graph.keySet()) {
            if(graph.get(u).size() > 0) {
                node = u;
                break;
            }
        }
        if(node == -1) {
            return 1; // It's an Euler graph (graph with no edges at all)
        }

        // 2a. Run DFS from any node with > 0 degree
        boolean[] visited = new boolean[graph.size()];
        DFS(node, graph, visited);

        // 2b. Check if any non-visited node has > 0 degree, if so means not all edges in one component
        for(int i = 0 ; i < graph.size() ; i++){
            if(!visited[i] && graph.get(i).size() > 0)
                return 0; // It's a non-Eulerian graph
        }

        // 3. Check the number of nodes with odd degree, if 0 then Euler, if 2 then Semi-Eulerian else Non-Eulerian
        int oddDegreeVertexCount = 0;
        for(int u : graph.keySet()) {
            if((graph.get(u).size() & 1) == 1)
                oddDegreeVertexCount++;
        }
        if(oddDegreeVertexCount == 2)
            return 2; // Semi-Eulerian graph
        else if(oddDegreeVertexCount == 0)
            return 1; // Euler graph
        else
            return 0; // Non-Euler graph
    }
    private static void DFS(int u, Map<Integer, List<Integer>> graph, boolean[] visited) {
        if(visited[u])
            return;

        visited[u] = true;
        for(int v : graph.get(u)) {
            DFS(v, graph, visited);
        }
    }
}
