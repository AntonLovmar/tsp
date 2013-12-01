package tsp.graph;

public class VertexImpl implements Vertex {
	
	
	public VertexImpl(int id, double x, double y) {
		
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean equals(VertexImpl other) {
		if(this.getId() == other.getId()) {
			return true; 
		} else {
			return false;
		}
	}

}
