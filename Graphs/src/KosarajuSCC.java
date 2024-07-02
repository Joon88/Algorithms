/*
Kosaraju's Algo can be used with both DFS and BFS to detect if a directed graph is
strongly connected. For undirected graphs, this is not reqd, as a simple BFS or DFS,
if able to reach all vertices tell us if the undirected graph is strongly connected
or not.

Detecting if a directed graph is SCC (strongly connected component) :
DFS : https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
BFS : https://www.geeksforgeeks.org/check-given-directed-graph-strongly-connected-set-2-kosaraju-using-bfs

Time : O(V+E) and space O(V+E) to store the transpose graph
 */

import java.util.*;

public class KosarajuSCC {
    public static void main(String args[]) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Arrays.asList(1)));
        graph.put(1, new LinkedList<>(Arrays.asList(2)));
        graph.put(2, new LinkedList<>(Arrays.asList(0,3)));
        graph.put(3, new LinkedList<>(Arrays.asList(4)));
        graph.put(4, new LinkedList<>(Arrays.asList(5,7)));
        graph.put(5, new LinkedList<>(Arrays.asList(6)));
        graph.put(6, new LinkedList<>(Arrays.asList(4,7)));
        graph.put(7, new LinkedList<>(Arrays.asList()));
        findAllSCCs(graph);
    }

    private static void findAllSCCs(Map<Integer, List<Integer>> graph) {
        List<List<Integer>> sccs = new ArrayList<>();

        /*
        Using DFS
         */
        Stack<Integer> stack = new Stack<>();
        int visited[] = new int[graph.size()];
        // here we are not trying to find cycles, just trying to traverse, so visited[] can have only 2
        // value 0 -> not visited, 1 -> visited
        for(int src : graph.keySet())
            if(visited[src] == 0)
                DFS(src, graph, stack, visited);

        Map<Integer, List<Integer>> transposeGraph = transpose(graph);

        Arrays.fill(visited, 0);
        while(!stack.isEmpty()) {
            int src = stack.pop();
            if(visited[src] == 0) {
                List<Integer> scc = new ArrayList<>();
                DFS2(src, transposeGraph, visited, scc);
                sccs.add(scc);
            }
        }

        System.out.println(sccs);

         /*
        Using BFS
         */
        sccs = new ArrayList<>();

        Queue<Integer> orderQ = new LinkedList<>();
        Arrays.fill(visited, 0);
        // here we are not trying to find cycles, just trying to traverse, so visited[] can have only 2
        // value 0 -> not visited, 1 -> visited
        for(int src : graph.keySet())
            if(visited[src] == 0)
                BFS(src, graph, orderQ, visited);

        // Map<Integer, List<Integer>> transposeGraph = transpose(graph); // commented just coz we already have the
                                                                          // graph transposed above

        Arrays.fill(visited, 0);
        while(!orderQ.isEmpty()) {
            int src = orderQ.poll();
            if(visited[src] == 0) {
                List<Integer> scc = new ArrayList<>();
                BFS2(src, transposeGraph, visited, scc);
                sccs.add(scc);
            }
        }

        System.out.println(sccs);
    }

    private static void DFS(int src, Map<Integer, List<Integer>> graph, Stack<Integer> stack, int[] visited) {
        if(visited[src] == 1)
            return;

        visited[src] = 1;
        for(int v : graph.get(src)) {
            DFS(v, graph, stack, visited);
        }
        stack.push(src);
    }

    private static void BFS(int src, Map<Integer, List<Integer>> graph, Queue<Integer> orderQ, int[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = 1;

        while(!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if(visited[v] == 0) {
                    visited[v] = 1;
                    q.add(v);
                }
            }
            orderQ.add(u);
        }
    }

    private static Map<Integer, List<Integer>> transpose(Map<Integer, List<Integer>> graph) {
        Map<Integer, List<Integer>> gt = new HashMap<>();

        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            gt.putIfAbsent(entry.getKey(), new LinkedList<>());
            for(int v : entry.getValue()) {
                gt.putIfAbsent(v, new LinkedList<>());
                List<Integer> l = gt.get(v);
                l.add(entry.getKey());
                gt.put(v, l);
            }
        }
        return gt;
    }

    private static void DFS2(int src, Map<Integer, List<Integer>> graph, int[] visited, List<Integer> scc) {
        if(visited[src] == 1)
            return;

        visited[src] = 1;
        scc.add(src);
        for(int v : graph.get(src)) {
            DFS2(v, graph, visited, scc);
        }
    }

    private static void BFS2(int src, Map<Integer, List<Integer>> graph, int[] visited, List<Integer> scc) {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = 1;

        while(!q.isEmpty()) {
            int u = q.poll();
            scc.add(u);
            for (int v : graph.get(u)) {
                if(visited[v] == 0) {
                    visited[v] = 1;
                    q.add(v);
                }
            }
        }
    }
}
