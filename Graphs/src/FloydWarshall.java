/*
You can't use Floyd Warshall with adjacency list because when the algo works, it makes new edges.
Example :
First, your graph has 2 Edges ( 1-2, 2-3 ). So you initialize the adjacency matrix :
adj[1][2] = 1; ( means have edge between 1 and 2)
adj[2][3] = 1; ( means have edge between 3 and 2)
adj[1][3] = +oo; ( means no edge between 1 and 3 )
And when FW works, edge 1-3 wil be added because adj[1][2] + adj[2][3] < adj[1][3] => adj[1][3] = 2 ( means have edge between 1 and 3 );

So, always use Floyd Warshall only for very dense graphs, coz for dense graphs adjacency matrix
representation is better. For sparse graphs, run Dijkstra's for every vertex instead.
 */

// O(V^3) time and O(V^2) space
public class FloydWarshall {
    public static int INF = Integer.MAX_VALUE;
    public static void main(String args[]) {
        int[][] graph = { {0,   5,  INF, 10},
                          {INF,  0,  3,  INF},
                          {INF, INF, 0,   1},
                          {INF, INF, INF, 0} };
        floydWarshallFindAPSP(graph);
    }

    private static void floydWarshallFindAPSP(int[][] graph) {
        int[][] dist = new int[graph.length][graph.length];
        for(int i = 0 ; i < graph.length ; i++) {
            for(int j = 0 ; j < graph.length ; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for(int k = 0 ; k < dist.length ; k++) {
            // for a vertex k, the following 2 loops would use values in the kth row and the kth col
            // to resolve the other values. And (i,k) or (k,j) won't change, because
            // (i,k) = min((i,k), (i,k) + (k,k)) which is same as min((i,k), (i,k)) = (i,k)
            // the same goes for (k,j)
            // Thus, we can keep updating the same dist array in all the |V| iterations
            for(int i = 0 ; i < dist.length ; i++) {
                for(int j = 0 ; j < dist.length ; j++) {
                    if(dist[i][k] == INF || dist[k][j] == INF)
                        continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // Checking for -ve weight cycles
        for(int i = 0 ; i < dist.length ; i++)
            if(dist[i][i] < 0)
                return;                         // -ve weight cycles present

        for(int i = 0 ; i < dist.length ; i++) {
            for(int j = 0 ; j < dist.length ; j++) {
                System.out.printf("(%d, %d) -> %d  |  ", i, j, dist[i][j]);
            }
            System.out.println();
        }
    }
}
