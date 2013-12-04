package tsp.graph;

public class Edge {
	private final Vertex vertex1, vertex2;

	public Vertex getVertex1() {
		return vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public Edge(Vertex vertex1, Vertex vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (!(vertex1.getId() == other.getVertex1().getId() || vertex1.getId() != other.getVertex2().getId()))
			return false;
		if (!(vertex2.getId() == other.getVertex1().getId() || vertex2.getId() != other.getVertex2().getId()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
		result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return vertex1.getId() + " -> " + vertex2.getId();
	}
}