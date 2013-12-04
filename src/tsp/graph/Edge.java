package tsp.graph;

public class Edge implements Comparable<Edge> {
	private final Vertex vertex1, vertex2;
	private final int length;

	public Edge(Vertex vertex1, Vertex vertex2, int length) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
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
		if (!(vertex1.equals(other.getVertex1()) || vertex1.equals(other.getVertex2())))
			return false;
		if (!(vertex2.equals(other.getVertex1()) || vertex2.equals(other.getVertex2())))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex1 == null || vertex2 == null) ? 0 : vertex1.hashCode() + vertex2.hashCode());
		return result;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.equals(o))
			return 0;
		int difference = getLength() - o.getLength();
		if (difference == 0) {
			return 1;
		}
		return difference;
	}

	@Override
	public String toString() {
		return vertex1.getId() + " -> " + vertex2.getId();
	}
}