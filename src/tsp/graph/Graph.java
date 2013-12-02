package tsp.graph;

import java.util.List;

public interface Graph {

	/**
	 * Return all the vertices for a graph.
	 * 
	 * @return the graph's vertices
	 */
	public List<Vertex> getVertices();

	/**
	 * Returns the number of vertices in the graph;
	 * 
	 * @return the number of vertices
	 */
	public int getNumberOfVertices();

	/**
	 * Get the distance between two vertices.
	 * 
	 * @param vertex1
	 *            the first vertex
	 * @param vertex2
	 *            the second vertex
	 * @return the distance between the vertices.
	 */
	public int distanceBetween(Vertex vertex1, Vertex vertex2);

	/**
	 * Returns the vertex at a certain index
	 * 
	 * @param index
	 *            the index of the vertex
	 * @return the vertex at the index
	 */
	public Vertex getVertex(int index);

	/**
	 * Calculates the total length of the path;
	 * 
	 * @param path
	 *            an ordered list of Vertices creating the path;
	 * @return the total length;
	 */
	public int totalLength(List<Vertex> path);
}
