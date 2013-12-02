package tsp.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TSPGraph implements Graph {

	private final List<Vertex> vertices;
	private final Set<Edge> edges;
	private final Edge[][] adjacencyMatrix;

	public TSPGraph(List<Vertex> vertices, Set<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
		adjacencyMatrix = new Edge[vertices.size() + 1][vertices.size() + 1];
		for (Edge edge : edges) {
			int vertex1Id = edge.getVertices().get(0).getId();
			int vertex2Id = edge.getVertices().get(1).getId();
			adjacencyMatrix[vertex1Id][vertex2Id] = edge;
			adjacencyMatrix[vertex2Id][vertex1Id] = edge;
		}
	}

	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public List<Edge> getEdgesForVertex(Vertex vertex) {
		List<Edge> edgesForVertex = new ArrayList<Edge>();
		if (!vertices.contains(vertex)) {
			return null;
		} else {
			for (int i = 1; i < adjacencyMatrix.length; i++) {
				if (adjacencyMatrix[vertex.getId()][i] != null)
					edgesForVertex.add(adjacencyMatrix[vertex.getId()][i]);
			}
		}
		return edgesForVertex;
	}

	@Override
	public int getNumberOfVertices() {
		return vertices.size();
	}

	@Override
	public int getNumberOfEdges() {
		return edges.size();
	}

	@Override
	public int distanceBetween(Vertex vertex1, Vertex vertex2) {
		return adjacencyMatrix[vertex1.getId()][vertex2.getId()].length();
	}

	@Override
	public int totalLength(List<Vertex> path) {
		int sum = 0;
		for (int i = 1; i < path.size(); i++) {
			sum += distanceBetween(path.get(i - 1), path.get(i));
		}
		sum += distanceBetween(path.get(path.size() - 1), path.get(0));
		return sum;
	}

	@Override
	public Vertex getVertex(int index) {
		return vertices.get(index);
	}

}
