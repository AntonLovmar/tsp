package tsp.graph;

import java.util.List;

public interface Path {

	/**
	 * Returns the path as a list of vertices. The first
	 * vertex in the list represents the first vertex in the path
	 * and so on.
	 * 
	 * @return The path in the form of vertices
	 */
	public List<Vertex> getPath();
	
	
	/**
	 * Adds a vertex to the end of the path.
	 * 
	 * @param vertex The vertex to be appended to the path.
	 */
	public void addToPath(Vertex vertex);
	
	/**
	 * 
	 * 
	 * @param oldVertex
	 * @param newVertex
	 * @return
	 */
	public boolean replaceVertex(Vertex oldVertex, Vertex newVertex);
	
	/**
	 * Returns the path as a string. The path string 
	 * should be a sequence of vertex id's.
	 * 
	 * @return The path represented by a string. 
	 */
	public String toString();
}
