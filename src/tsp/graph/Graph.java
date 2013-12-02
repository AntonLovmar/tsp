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
	 * Returns the number of vertices in the graph;
	 * 
	 * @return the number of vertices
	 */
	public int getNumberOfVertices();

	/**
	 * Returns the number of edges in the graph.
	 * 
	 * @return the number of edges
	 */
	public int getNumberOfEdges();

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
	 * Get all edges connected to a certain vertex.
	 * 
	 * @param vertex
	 *            A vertex for which all connected edges will be retrieved
	 * @return All edges connected to the vertex. Null if not edges are
	 *         connected
	 */
	public List<Edge> getEdgesForVertex(Vertex vertex);

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
