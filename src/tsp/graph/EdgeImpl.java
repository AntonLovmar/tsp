package tsp.graph;

import java.util.ArrayList;
import java.util.List;

public class EdgeImpl implements Edge{
	
	private List<Vertex> vertices;
	private int length;
	
	public EdgeImpl(Vertex vertex1, Vertex vertex2) {
		vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		length = (int) Math.round(Math.sqrt(Math.pow(vertex1.getX()+vertex2.getX(), 2) + Math.pow(vertex1.getY()+vertex2.getY(), 2)));
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
		// TODO Auto-generated method stub
		return false;
	}

}
