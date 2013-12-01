package tsp.graph;
/**
 * This class represents a vertex in a graph.
 * 
 * @author anton
 *
 */
public interface Vertex {
	
	/**
	 * Returns a unique identifier for this vertex.
	 * 
	 * @return the id of the vertex
	 */
	public int getId();
	
	/**
	 * Returns the x-coordinate of the vertex.
	 * 
	 * @return a double representing the vertex's x-coordinate
	 */
	public double getX();
	
	/**
	 * Returns the y-coordinate of the vertex.
	 * 
	 * @return a double representing the vertex's y-coordinate
	 */
	public double getY();

}
