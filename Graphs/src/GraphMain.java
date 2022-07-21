
public class GraphMain {
	public static void main(String args[]) {
		Prims wg = new Prims();

		Prims.Node n0 = new Prims.Node(0);
		Prims.Node n1 = new Prims.Node(1);
		Prims.Node n2 = new Prims.Node(2);
		Prims.Node n3 = new Prims.Node(3);
		Prims.Node n4 = new Prims.Node(4);
		Prims.Node n5 = new Prims.Node(5);
		Prims.Node n6 = new Prims.Node(6);
		Prims.Node n7 = new Prims.Node(7);
		Prims.Node n8 = new Prims.Node(8);

		wg.addEdge(n0, n1, 4);
		wg.addEdge(n1, n2, 8);
		wg.addEdge(n2, n3, 7);
		wg.addEdge(n3, n4, 9);
		wg.addEdge(n4, n5, 10);
		wg.addEdge(n5, n6, 2);
		wg.addEdge(n6, n7, 1);
		wg.addEdge(n7, n0, 8);
		wg.addEdge(n1, n7, 11);
		wg.addEdge(n7, n8, 7);
		wg.addEdge(n2, n8, 2);
		wg.addEdge(n6, n8, 6);
		wg.addEdge(n2, n5, 4);
		wg.addEdge(n3, n5, 14);

		wg.primsMST();

		DFSCycleDetection g = new DFSCycleDetection();

//		g.addEdge(0, 1);
//		g.addEdge(1, 2);
//		g.addEdge(2, 3);
//		g.addEdge(3, 4);
//		g.addEdge(4, 8);
//		g.addEdge(8, 9);
//		g.addEdge(9, 5);
//		g.addEdge(4, 5);
//		g.addEdge(5, 6);
//		g.addEdge(6, 7);
//		g.addEdge(7, 10);
//
//		g.addEdge(11, 12);
//		g.addEdge(12, 13);
//		g.addEdge(13, 14);

		g.addEdge(0, 7);
		g.addEdge(1, 0);
		g.addEdge(1, 7);
		g.addEdge(6, 7);
		g.addEdge(8, 7);
		g.addEdge(2, 1);
		g.addEdge(2, 8);
		g.addEdge(6, 8);
		g.addEdge(5, 6);
		g.addEdge(2, 5);
		g.addEdge(2, 3);
		g.addEdge(5, 3);
		g.addEdge(5, 4);
		g.addEdge(3, 4);

		System.out.println(g.isCyclePresentUsingColoring());



//		Graph gr = new Graph(false);
//		Graph.Node n1 = new Graph.Node("1");
//		Graph.Node n2 = new Graph.Node("2");
//		Graph.Node n3 = new Graph.Node("3");
//		Graph.Node n4 = new Graph.Node("4");
//		Graph.Node n5 = new Graph.Node("5");
//		Graph.Node n6 = new Graph.Node("6");


		//GenericGraph<Graph.Node> g1 = new GenericGraph<>(true);
		//g1.addEdge(n1,  n2);
		//g1.printGraph();

//		gr.addEdge(n1, n2);
//		gr.addEdge(n1, n3);
//		gr.addEdge(n2, n3);
//		gr.addEdge(n2, n4);
//		gr.addEdge(n2, n5);
//		gr.addEdge(n3, n4);
//		gr.addEdge(n3, n5);
//		gr.addEdge(n4, n5);
//		gr.addEdge(n4, n6);
//		gr.addEdge(n5, n6);
//		gr.addEdge(n6, n1);
//		gr.getDFSTraversal(n1);


		//gr.getBFSTraversal(n1);
		//System.out.println(gr.getAdjacentNodes(n3));

		//g.addEdge("Barpeta", "Nalbari");
//		g.addEdge("Barpeta", "Ghy");
//		g.addEdge("Barpeta", "Bongaigaon");
//		g.addEdge("Ghy", "Silchar");
//		g.addEdge("Ghy", "Nalbari");
//		g.addEdge("Kokrajhar", "Dhubri");
//		g.addEdge("Dhubri", "Bilasipara");
//		g.addEdge("Bilasipara", "Bongaigaon");
//		g.addEdge("Bongaigaon", "Kokrajhar");
//		g.addNode("Baksa");

		//g.printGraph();
		//System.out.println(g.getEdgeCount());
		//System.out.println(g.getNodeCount());
		//System.out.println(g.hasNode("Baksa"));
		//System.out.println(g.hasEdge("Bongaigaon", "Barpeta"));
		//System.out.println(g.getAdjacentNodes("Nalbari"));
		System.out.println("-----------------------------------");
		DisjointSet ds = new DisjointSet(7);
		ds.makeSet(0);
		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);
		ds.makeSet(6);

		System.out.println(ds.union(0, 1));
		System.out.println(ds.union(1, 2));
		System.out.println(ds.union(3, 4));
		System.out.println(ds.union(5, 6));
		System.out.println(ds.union(4, 5));
		System.out.println(ds.union(2, 6));
		ds.findSet(1);
		ds.findSet(2);
		System.out.println(ds.union(4, 6));

		System.out.println("--------------------------------");

		Kruskals.Graph gr = new Kruskals.Graph();

		gr.addEdge(0, 1, 4);
		gr.addEdge(1, 2, 8);
		gr.addEdge(2, 3, 7);
		gr.addEdge(3, 4, 9);
		gr.addEdge(4, 5, 10);
		gr.addEdge(5, 6, 2);
		gr.addEdge(6, 7, 1);
		gr.addEdge(7, 0, 8);
		gr.addEdge(1, 7, 11);
		gr.addEdge(7, 8, 7);
		gr.addEdge(2, 8, 2);
		gr.addEdge(6, 8, 6);
		gr.addEdge(2, 5, 4);
		gr.addEdge(3, 5, 14);

		gr.printGraph();

		Kruskals k = new Kruskals();
		k.getMST(gr);

		System.out.println("-----------------");

		DisjointSet ds1 = new DisjointSet(4);

		DisjointSet.Graph gra = ds1.new Graph();
		gra.addEdge(0, 1);
		gra.addEdge(0, 2);
		gra.addEdge(0, 3);
		gra.addEdge(2, 3);

		System.out.println(ds1.cyclePresent(gra));

		System.out.println("----------------------");

		Dijkstras d = new Dijkstras();
		Dijkstras.Graph gr2 = d.new Graph();

		Dijkstras.Node nd0 = d.new Node(0);
		Dijkstras.Node nd1 = d.new Node(1);
		Dijkstras.Node nd2 = d.new Node(2);
		Dijkstras.Node nd3 = d.new Node(3);
		Dijkstras.Node nd4 = d.new Node(4);
		Dijkstras.Node nd5 = d.new Node(5);
		Dijkstras.Node nd6 = d.new Node(6);
		Dijkstras.Node nd7 = d.new Node(7);
		Dijkstras.Node nd8 = d.new Node(8);

		gr2.addEdge(nd0, nd1, 4);
		gr2.addEdge(nd0, nd7, 8);
		gr2.addEdge(nd1, nd2, 8);
		gr2.addEdge(nd1, nd7, 11);
		gr2.addEdge(nd2, nd3, 7);
		gr2.addEdge(nd2, nd5, 4);
		gr2.addEdge(nd2, nd8, 2);
		gr2.addEdge(nd3, nd4, 9);
		gr2.addEdge(nd3, nd5, 14);
		gr2.addEdge(nd4, nd5, 10);
		gr2.addEdge(nd5, nd6, 2);
		gr2.addEdge(nd5, nd2, 4);
		gr2.addEdge(nd6, nd7, 1);
		gr2.addEdge(nd6, nd8, 6);
		gr2.addEdge(nd7, nd8, 7);

		d.getShortestPath(gr2, nd0);

		System.out.println("----------------------------------");

		BellmanFord bf = new BellmanFord();
		BellmanFord.Graph g2 = bf.new Graph();

		BellmanFord.Node n00 = bf.new Node(0);
		BellmanFord.Node n01 = bf.new Node(1);
		BellmanFord.Node n02 = bf.new Node(2);
		BellmanFord.Node n03 = bf.new Node(3);
		BellmanFord.Node n04 = bf.new Node(4);

		g2.addEdge(n00, n01, -1);
		g2.addEdge(n00, n02, 4);
		g2.addEdge(n01, n02, 3);
		g2.addEdge(n01, n03, 2);
		g2.addEdge(n01, n04, 2);
		g2.addEdge(n03, n01, 1);
		g2.addEdge(n03, n02, 5);
		g2.addEdge(n04, n03, -3);

		bf.runBellmanFord(g2, n00);

		System.out.println("---------------------------------------");

		TopologicalSort ts = new TopologicalSort();
		ts.addEdge(1, 3);
		ts.addEdge(3, 5);
		ts.addEdge(5, 6);
		ts.addEdge(5, 8);
		ts.addEdge(6, 7);
		ts.addEdge(2, 3);
		ts.addEdge(2, 4);
		ts.addEdge(4, 5);

		ts.topologicalSort();

		System.out.println("--------------------------------------------");

		new DAGShortestPath().getShortestPath(8);

	}
}
