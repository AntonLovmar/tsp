package tsp.graph;

public class VertexImpl implements Vertex {
	
	private double x,y;
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
	
	public boolean equals(VertexImpl other) {
		if(this.getId() == other.getId()) {
			return true; 
		} else {
			return false;
		}
	}

}
