package tsp.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Path {

	private final List<Vertex> path;
	private final Map<Vertex, List<Vertex>> nearestNeighbours;
	private final Map<Vertex, Integer> indexOfElement = new HashMap<>();

	public Path(int size) {
		this(new ArrayList<Vertex>(size));
	}

	public Path(List<Vertex> path) {
		this(path, null);
	}

	public Path(int size, Map<Vertex, List<Vertex>> nearestNeighbours) {
		this(new ArrayList<Vertex>(size), nearestNeighbours);
	}

	public Path(List<Vertex> path, Map<Vertex, List<Vertex>> nearestNeighbours) {
		this.path = path;
		this.nearestNeighbours = nearestNeighbours;
	}

	public boolean hasAdjacencyMap() {
		return nearestNeighbours != null;
	}

	public Map<Vertex, List<Vertex>> getAdjacencyMap() {
		return nearestNeighbours;
	}

	public int indexOf(Vertex vertex) {
		Integer index = indexOfElement.get(vertex);
		if (index == null) {
			index = path.indexOf(vertex);
			indexOfElement.put(vertex, index);
		}
		return index;
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
		indexOfElement.put(first, secondIndex);
		indexOfElement.put(second, firstIndex);
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
