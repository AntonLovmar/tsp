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

	private int numEdges;

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
		for (Edge edge : spanningTree.get(start)) {
			nextStep = edge.getVertex1().equals(nextStep) ? edge.getVertex2() : edge.getVertex1();
			path.add(nextStep);
			visitedEdges.add(edge);
			break;
		}

		while (visitedEdges.size() < numEdges) {
			for (Edge edge : spanningTree.get(nextStep)) {
				if (!visitedEdges.contains(edge)) {
					visitedEdges.add(edge);
					nextStep = edge.getVertex1().equals(nextStep) ? edge.getVertex2() : edge.getVertex1();
					path.add(nextStep);
				}
			}
		}

		List<Vertex> filteredPath = new ArrayList<>();
		Set<Vertex> visited = new HashSet<>();
		for (Vertex v : path) {
			if (visited.contains(v))
				continue;
			visited.add(v);
			filteredPath.add(v);
		}

		return new Path(filteredPath);
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
			if (vSets.get(u).containsAll(vSets.get(v)) || vSets.get(v).containsAll(vSets.get(u))) {
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
		numEdges = spanningTreeEdges.size();
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
