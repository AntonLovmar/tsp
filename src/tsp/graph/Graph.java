package tsp.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	private final List<Vertex> vertices;
	private final int[][] adjacencyMatrix;
	private Map<Vertex, List<Vertex>> nearestNeighbours = null;

	public Graph(List<Vertex> vertices) {
		this.vertices = vertices;
		adjacencyMatrix = new int[vertices.size() + 1][vertices.size() + 1];
		buildEdges();
	}

	/**
	 * Return all the vertices for a graph.
	 * 
	 * @return the graph's vertices
	 */
	public List<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * Returns the number of vertices in the graph;
	 * 
	 * @return the number of vertices
	 */
	public int getNumberOfVertices() {
		return vertices.size();
	}

	/**
	 * Get the distance between two vertices.
	 * 
	 * @param vertex1
	 *            the first vertex
	 * @param vertex2
	 *            the second vertex
	 * @return the distance between the vertices.
	 */
	public int distanceBetween(Vertex vertex1, Vertex vertex2) {
		return adjacencyMatrix[vertex1.getId()][vertex2.getId()];
	}

	/**
	 * Calculates the total length of the path;
	 * 
	 * @param path
	 *            an ordered list of Vertices creating the path;
	 * @return the total length;
	 */
	public int totalLength(List<Vertex> path) {
		int sum = 0;
		for (int i = 1; i < path.size(); i++) {
			sum += distanceBetween(path.get(i - 1), path.get(i));
		}
		sum += distanceBetween(path.get(path.size() - 1), path.get(0));
		return sum;
	}

	public List<Vertex> getNeighbourList(Vertex v) {
		if (nearestNeighbours == null) {
			preCompumputeNearestNeighbours();
		}
		return nearestNeighbours.get(v);
	}

	private void preCompumputeNearestNeighbours() {
		int numberOfVertices = getNumberOfVertices();
		Map<Vertex, List<Vertex>> greedyPath = new HashMap<Vertex, List<Vertex>>(numberOfVertices);

		for (Vertex curr : getVertices()) {
			List<Vertex> neighbours = new ArrayList<Vertex>(numberOfVertices);
			for (Vertex neighbour : getVertices()) {
				if (curr.equals(neighbour))
					continue;
				neighbours.add(neighbour);
			}
			// Closest neighbor is first in list.
			Collections.sort(neighbours, new NeighborComparator(curr));
			greedyPath.put(curr, neighbours);
		}
		nearestNeighbours = greedyPath;
	}

	/**
	 * Returns the vertex at a certain index
	 * 
	 * @param index
	 *            the index of the vertex
	 * @return the vertex at the index
	 */
	public Vertex getVertex(int index) {
		return vertices.get(index);
	}

	private void buildEdges() {
		for (Vertex vertex : vertices) {
			for (Vertex otherVertex : vertices) {
				double first = vertex.getX() - otherVertex.getX();
				double second = vertex.getY() - otherVertex.getY();
				int length = (int) (Math.sqrt((first * first) + (second * second)) + 0.5);
				adjacencyMatrix[vertex.getId()][otherVertex.getId()] = length;
			}
		}
	}

	private class NeighborComparator implements Comparator<Vertex> {
		Vertex root;

		public NeighborComparator(Vertex root) {
			this.root = root;
		}

		@Override
		public int compare(Vertex arg0, Vertex arg1) {
			return distanceBetween(root, arg0) - distanceBetween(root, arg1);
		}

	}
}
