package tsp.graph;

import java.util.ArrayList;
import java.util.List;

public class EdgeImpl implements Edge {

	private final List<Vertex> vertices;
	private final int length;

	public EdgeImpl(Vertex vertex1, Vertex vertex2) {
		vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		length = (int) Math.round(Math.sqrt(Math.pow(vertex1.getX() - vertex2.getX(), 2)
				+ Math.pow(vertex1.getY() - vertex2.getY(), 2)));
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public boolean updateEdge(Vertex oldVertex, Vertex newVertex) {
		if (vertices.remove(oldVertex)) {
			vertices.add(newVertex);
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		Edge otherEdge = (Edge) other;
		if (vertices.contains(otherEdge.getVertices().get(0)) && vertices.contains(otherEdge.getVertices().get(1))) {
			return true;
		}
		return false;
	}

}
