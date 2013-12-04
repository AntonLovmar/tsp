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

public class ChristofidesPathfindingStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		return buildHamiltonCycle(matchUneven(findMST(graph), graph), graph);
	}

	private Path buildHamiltonCycle(Map<Vertex, Set<Edge>> spanningTree, Graph graph) {
		List<Vertex> path = new ArrayList<Vertex>(graph.getNumberOfVertices());
		Set<Edge> visitedEdges = new HashSet<Edge>();

		Vertex start = graph.getVertex(0);
		path.add(start);
		Vertex nextStep = null;

		// Ugly as fuck, but how could we do this otherwise?
		for (Edge e : spanningTree.get(start)) {
			nextStep = e.getVertex2();
			break;
		}

		while (nextStep != start) {
			for (Edge edge : spanningTree.get(nextStep)) {
				if (!visitedEdges.contains(edge)
						|| (path.size() < graph.getNumberOfVertices() && (edge.getVertex1().equals(start) || edge
								.getVertex2().equals(start)))) {
					visitedEdges.add(edge);
					if (edge.getVertex1().equals(nextStep)) {
						path.add(edge.getVertex2());
						nextStep = edge.getVertex2();
					} else {
						path.add(edge.getVertex1());
						nextStep = edge.getVertex1();
					}
				}
			}
		}

		return new Path(path);
	}

	Map<Vertex, Set<Edge>> matchUneven(Map<Vertex, Set<Edge>> verticesToEdges, Graph graph) {
		Set<Vertex> unevenVertices = new HashSet<Vertex>();
		for (Vertex v : verticesToEdges.keySet()) {
			if (verticesToEdges.get(v).size() % 2 != 0) {
				unevenVertices.add(v);
			}
		}
		Set<Vertex> claimedNodes = new HashSet<Vertex>();
		for (Vertex v : unevenVertices) {
			if (claimedNodes.contains(v)) {
				continue;
			}
			claimedNodes.add(v);
			int bestDistance = Integer.MAX_VALUE;
			Vertex bestDistanceVertex = null;

			for (Vertex u : unevenVertices) {
				if (claimedNodes.contains(u))
					continue;
				int currentDistance = graph.distanceBetween(v, u);
				if (currentDistance < bestDistance) {
					bestDistance = currentDistance;
					bestDistanceVertex = u;
				}
			}
			claimedNodes.add(bestDistanceVertex);
			Edge e = new Edge(v, bestDistanceVertex, bestDistance);
			verticesToEdges.get(v).add(e);
			verticesToEdges.get(bestDistanceVertex).add(e);
		}
		return verticesToEdges;
	}

	public Map<Vertex, Set<Edge>> findMST(Graph graph) {
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
		while (!edges.isEmpty()) {
			Edge e = edges.pollFirst();
			int u = e.getVertex1().getId();
			int v = e.getVertex2().getId();
			if (vSets.get(u).equals(vSets.get(v))) {
				continue;
			} else {
				spanningTreeEdges.add(e);
				addEdgeToMap(verticesToEdges, e);

				vSets.get(u).addAll(vSets.get(v));
				vSets.set(v, vSets.get(u));

				if (spanningTreeEdges.size() == graph.getNumberOfVertices() - 1)
					break;

			}
		}
		return verticesToEdges;
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
