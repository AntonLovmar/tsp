package tsp.pathfinding;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class KruskalPathfindingStrategy implements PathfindingStrategy {

	private Graph graph;
	private final TreeSet<Edge> edges = new TreeSet<Edge>(new EdgeWeightComparator());

	@Override
	public Path findPath(Graph graph) {
		this.graph = graph;
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			for (int j = 0; j < graph.getNumberOfVertices(); j++) {
				edges.add(new Edge(graph.getVertex(i), graph.getVertex(j)));
			}
		}

		Set<Edge> spanningTree = new TreeSet<Edge>();

		while (!edges.isEmpty()) {
			Edge e = edges.pollFirst();

		}

		return null;
	}

	private class EdgeWeightComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return graph.distanceBetween(o1.getVertex1(), o1.getVertex2())
					- graph.distanceBetween(o2.getVertex1(), o2.getVertex2());
		}
	}

	private class Edge {
		private final Vertex vertex1, vertex2;

		public Vertex getVertex1() {
			return vertex1;
		}

		public Vertex getVertex2() {
			return vertex2;
		}

		Edge(Vertex vertex1, Vertex vertex2) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (vertex1 == null) {
				if (other.vertex1 != null)
					return false;
			} else if (!vertex1.equals(other.vertex1))
				return false;
			if (vertex2 == null) {
				if (other.vertex2 != null)
					return false;
			} else if (!vertex2.equals(other.vertex2))
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
			result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
			return result;
		}

		private KruskalPathfindingStrategy getOuterType() {
			return KruskalPathfindingStrategy.this;
		}
	}

}
