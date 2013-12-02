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
	 * Retrieve the vertex at a certain index. 
	 * 
	 * @param index The index of the vertex
	 * @return the vertex at index <code>index</code>
	 */
	public Vertex getVertex(int index);
	
	/**
	 * Returns the first vertex in the path.
	 * 
	 * @return The first vertex in the path.
	 */
	public Vertex getFirst();
	
	/**
	 * Adds a vertex to the end of the path.
	 * 
	 * @param vertex The vertex to be appended to the path.
	 */
	public void addToPath(Vertex vertex);
	
	/**
	 * Replaces the vertex in the path at a certain index
	 * with a new vertex.
	 * 
	 * @param index The index of the vertex to be replaced
	 * @param newVertex The new vertex
	 */
	public void replaceVertex(int index, Vertex newVertex);
	
	/**
	 * Swaps places of two vertices at the indices. 
	 * 
	 * @param firstIndex The index of the first vertex
	 * @param secondIndex The index of the second vertex
	 */
	public void swapVerticesAt(int firstIndex, int secondIndex); 
	
	/**
	 * Returns the path as a string. The path string 
	 * should be a sequence of vertex id's.
	 * 
	 * @return The path represented by a string. 
	 */
	public String toString();
}
