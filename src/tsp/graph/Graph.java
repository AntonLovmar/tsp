package tsp.graph;

import java.util.List;

public interface Graph {
	
	/**
	 * Get all the edges of the graph.
	 * 
	 * @return the edges of the graph.
	 */
	public List<Edge> getEdges();
	
	/**
	 * Return all the vertices for a graph.
	 * 
	 * @return the graph's vertices
	 */
	public List<Vertex> getVertices();
	
	/**
	 * Get all edges connected to a certain vertex.
	 * 
	 * @param vertex A vertex for which all connected edges will be retrieved
	 * @return All edges connected to the vertex
	 */
	public List<Edge> getEdgesForVertex(Vertex vertex);
	
}
