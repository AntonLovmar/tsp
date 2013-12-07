package tsp.pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import tsp.graph.Edge;
import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class MSTPathfindingStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		Map<Vertex, Set<Edge>> tree = findMST(graph);
		int bestLength = Integer.MAX_VALUE;
		List<Vertex> bestPath = null;

		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			List<Vertex> ham = buildHamiltonCycle(tree, graph, new HashSet<Vertex>(), graph.getVertex(i));
			int length = graph.totalLength(new Path(ham));
			// System.out.println("length: " + length);
			if (length < bestLength) {
				bestPath = ham;
				bestLength = length;
			}
		}
		return new Path(bestPath);
	}

	private List<Vertex> buildHamiltonCycle(Map<Vertex, Set<Edge>> spanningTree, Graph graph, Set<Vertex> visited,
			Vertex root) {
		List<Vertex> path = new ArrayList<>();
		visited.add(root);
		path.add(root);

		for (Edge e : spanningTree.get(root)) {
			Vertex next = e.getVertex1().equals(root) ? e.getVertex2() : e.getVertex1();
			if (visited.contains(next))
				continue;
			path.addAll(buildHamiltonCycle(spanningTree, graph, visited, next));
		}
		return path;
	}

	public Map<Vertex, Set<Edge>> findMST(Graph graph) {
		long before = System.currentTimeMillis();
		Map<Vertex, Set<Edge>> verticesToEdges = new HashMap<Vertex, Set<Edge>>(graph.getNumberOfVertices());
		TreeSet<Edge> edges = new TreeSet<Edge>();
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			for (int j = 0; j < graph.getNumberOfVertices(); j++) {
				if (i != j)
					edges.add(new Edge(graph.getVertex(i), graph.getVertex(j), graph.distanceBetween(
							graph.getVertex(i), graph.getVertex(j))));
			}
		}

		List<Set<Vertex>> vSets = new ArrayList<Set<Vertex>>();
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			Set<Vertex> vertexSet = new HashSet<Vertex>();
			vertexSet.add(graph.getVertex(i));
			vSets.add(vertexSet);
		}
		Set<Edge> spanningTreeEdges = new HashSet<Edge>();
		long mergeTime = 0;
		long beforeAlg = System.currentTimeMillis();
		while (!edges.isEmpty()) {
			Edge e = edges.pollFirst();
			int u = e.getVertex1().getId();
			int v = e.getVertex2().getId();
			if (vSets.get(u).equals(vSets.get(v))) {
				continue;
			} else {
				spanningTreeEdges.add(e);
				addEdgeToMap(verticesToEdges, e);
				long beforeMerge = System.currentTimeMillis();
				mergeSets(vSets.get(v), vSets.get(u), vSets);
				mergeTime += (System.currentTimeMillis() - beforeMerge);
				if (spanningTreeEdges.size() == graph.getNumberOfVertices() - 1)
					break;

			}
		}
		System.out.println("Algorithm time: " + (System.currentTimeMillis() - beforeAlg));
		System.out.println("Time in merge: " + mergeTime);
		System.out.println("Total time: " + (System.currentTimeMillis() - before));
		return verticesToEdges;
	}

	private void mergeSets(Set<Vertex> set1, Set<Vertex> set2, List<Set<Vertex>> allSets) {
		set1.addAll(set2);
		for (Vertex v : set1) {
			allSets.get(v.getId()).addAll(set2);
		}
		set2.addAll(set1);
		for (Vertex v : set1) {
			allSets.get(v.getId()).addAll(set1);
		}
	}

	private void addEdgeToMap(Map<Vertex, Set<Edge>> verticesToEdges, Edge e) {
		Vertex v = e.getVertex1();
		Vertex u = e.getVertex2();
		if (verticesToEdges.get(v) == null)
			verticesToEdges.put(v, new HashSet<Edge>());
		if (verticesToEdges.get(u) == null)
			verticesToEdges.put(u, new HashSet<Edge>());
		verticesToEdges.get(u).add(e);
		verticesToEdges.get(v).add(e);
	}

}
