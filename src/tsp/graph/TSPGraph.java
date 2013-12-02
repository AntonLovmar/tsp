package tsp.graph;

import java.util.List;

public class TSPGraph implements Graph {

	private final List<Vertex> vertices;
	private final int[][] adjacencyMatrix;

	public TSPGraph(List<Vertex> vertices) {
		this.vertices = vertices;
		adjacencyMatrix = new int[vertices.size() + 1][vertices.size() + 1];
		buildEdges();
	}

	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public int getNumberOfVertices() {
		return vertices.size();
	}

	@Override
	public int distanceBetween(Vertex vertex1, Vertex vertex2) {
		return adjacencyMatrix[vertex1.getId()][vertex2.getId()];
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

	private void buildEdges() {
		for (Vertex vertex : vertices) {
			for (Vertex otherVertex : vertices) {
				double first = vertex.getX() - otherVertex.getX();
				double second = vertex.getY() - otherVertex.getY();
				int length = (int) (Math.sqrt((first * first) + (second * second)) + 0.5);
				adjacencyMatrix[vertex.getId()][otherVertex.getId()] = length;
			}
		}
	}
}
