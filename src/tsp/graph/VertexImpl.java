package tsp.graph;

public class VertexImpl implements Vertex {

	private double x, y;
	private int id;

	public VertexImpl(int id, double x, double y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public boolean equals(Object other) {
		Vertex otherVertex = (Vertex) other;
		if (this.getId() == otherVertex.getId() && this.getX() == otherVertex.getX()
				&& this.getY() == otherVertex.getY()) {
			return true;
		} else {
			return false;
		}
	}

}
