package tsp.graph;

import java.util.List;

/**
 * This class represents an edge in a graph.
 * 
 * @author anton
 *
 */
public interface Edge {
	
	/**
	 * Returns the length of the edge
	 * 
	 * @return the edge's length
	 */
	public int length();
	
	/**
	 * Returns a list of the two vertices this edge is connected to. 
	 * 
	 * @return a list of vertices
	 */
	public List<Vertex> getVertices();
	
	/**
	 * Replaces one of the vertices the edge is connected to with a new one.
	 * 
	 * @param oldVertex The vertex to be replaced
	 * @param newVertex The replacement vertex
	 * @return True if the vertex could be replaced, false if oldVertex was not found
	 */
	public boolean updateEdge(Vertex oldVertex, Vertex newVertex);
	
	
}
