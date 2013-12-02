package tsp.graph;

import java.util.ArrayList;
import java.util.List;

public class TSPGraph implements Graph {
	
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	public TSPGraph(List<Vertex> vertices, List<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}
	
	@Override
	public List<Edge> getEdges() {
		return edges;
	}

	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public List<Edge> getEdgesForVertex(Vertex vertex) {
		List<Edge> edgesForVertex = new ArrayList<Edge>();
		if(!vertices.contains(vertex)) {
			return null;
		} else {
			for(Edge edge : getEdges()) {
				if(edge.getVertices().contains(vertex)) {
					edgesForVertex.add(edge);
				}
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
		List<Edge> edges = getEdgesForVertex(vertex1);
		for(Edge edge : edges) {
			if(edge.getVertices().contains(vertex2)) {
				return edge.length();
			}
		}
		return 0;
	}

	@Override
	public Vertex getVertex(int index) {
		return vertices.get(index);
	}

}
