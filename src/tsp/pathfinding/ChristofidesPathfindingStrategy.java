package tsp.pathfinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import tsp.graph.Edge;
import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class ChristofidesPathfindingStrategy implements PathfindingStrategy {

	private Graph graph;

	@Override
	public Path findPath(Graph graph) {
		return null;
	}

	public SpanningTree findMST(Graph graph) {
		TreeSet<Edge> edges = new TreeSet<Edge>();
		this.graph = graph;
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
		Set<Vertex> spanningTreeVertices = new HashSet<Vertex>();
		System.out.println(edges);
		while (!edges.isEmpty()) {
			Edge e = edges.pollFirst();
			int u = e.getVertex1().getId();
			int v = e.getVertex2().getId();
			if (vSets.get(u).containsAll(vSets.get(v)) || vSets.get(v).containsAll(vSets.get(u))) {
				continue;
			} else {
				spanningTreeEdges.add(e);

				spanningTreeVertices.addAll(vSets.get(v));
				spanningTreeVertices.addAll(vSets.get(u));

				vSets.get(u).addAll(vSets.get(v));
				vSets.set(v, vSets.get(u));

				if (spanningTreeEdges.size() == graph.getNumberOfVertices() - 1)
					break;

			}
		}
		return new SpanningTree(spanningTreeVertices, spanningTreeEdges);
	}

	// TODO AHHHHH, FULKOD!
	public class SpanningTree {
		private final Set<Vertex> vertices;
		private final Set<Edge> edges;

		public SpanningTree(Set<Vertex> vertices, Set<Edge> edges) {
			this.vertices = vertices;
			this.edges = edges;
		}

		public Set<Edge> getEdges() {
			return edges;
		}

		public Set<Vertex> getVertices() {
			return vertices;
		}
	}

}
