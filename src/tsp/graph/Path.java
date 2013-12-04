package tsp.graph;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private final List<Vertex> path;

	public Path(int size) {
		path = new ArrayList<>(size);
	}

	public Path(List<Vertex> path) {
		this.path = path;
	}

	/**
	 * Returns the path as a list of vertices. The first vertex in the list
	 * represents the first vertex in the path and so on.
	 * 
	 * @return The path in the form of vertices
	 */
	public List<Vertex> getPath() {
		return path;
	}

	/**
	 * Adds a vertex to the end of the path.
	 * 
	 * @param vertex
	 *            The vertex to be appended to the path.
	 */
	public void addToPath(Vertex vertex) {
		path.add(vertex);
	}

	/**
	 * Replaces the vertex in the path at a certain index with a new vertex.
	 * 
	 * @param index
	 *            The index of the vertex to be replaced
	 * @param newVertex
	 *            The new vertex
	 */
	public void replaceVertex(int index, Vertex newVertex) {
		path.remove(index);
		path.add(index, newVertex);
	}

	/**
	 * Swaps places of two vertices at the indices.
	 * 
	 * @param firstIndex
	 *            The index of the first vertex
	 * @param secondIndex
	 *            The index of the second vertex
	 */
	public void swapVerticesAt(int firstIndex, int secondIndex) {
		Vertex first = path.get(firstIndex);
		Vertex second = path.get(secondIndex);
		path.set(secondIndex, first);
		path.set(firstIndex, second);
	}

	/**
	 * Retrieve the vertex at a certain index.
	 * 
	 * @param index
	 *            The index of the vertex
	 * @return the vertex at index <code>index</code>, or null if position is
	 *         not filled.
	 */
	public Vertex getVertex(int index) {
		if (index < 0)
			index += path.size();
		return path.get(index % path.size());
	}

	@Override
	public Path clone() {
		List<Vertex> path = new ArrayList<Vertex>(this.path.size());
		path.addAll(this.path);
		return new Path(path);
	}

	/**
	 * Returns the first vertex in the path.
	 * 
	 * @return The first vertex in the path.
	 */
	public Vertex getFirst() {
		return getVertex(0);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Vertex vertex : path) {
			sb.append(vertex.getId());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Reverses a path between the two given indices.
	 * 
	 * @param reverseFrom
	 *            (inclusive) index to reverse from.
	 * @param reverseTo
	 *            (inclusive) index to reverse to.
	 */
	public void reverseBetweenIndices(int reverseFrom, int reverseTo) {
		while (reverseTo > reverseFrom) {
			swapVerticesAt(reverseFrom, reverseTo);
			reverseFrom++;
			reverseTo--;
		}
	}
}
