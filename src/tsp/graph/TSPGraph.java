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

}
