package tsp.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Path {

	private final Map<Vertex, Vertex> nextHopPath;
	private Vertex start;

	public Path(List<Vertex> path) {
		nextHopPath = new HashMap<>();
		Vertex lastVertex = path.get(0);
		start = lastVertex;
		for (int i = 1; i < path.size(); i++) {
			Vertex next = path.get(i);
			nextHopPath.put(lastVertex, next);
			lastVertex = next;
		}
		nextHopPath.put(lastVertex, start);
	}

	public Path(Map<Vertex, Vertex> nextHopPath) {
		this.nextHopPath = nextHopPath;
		for (Vertex start : nextHopPath.keySet()) {
			this.start = start;
			break;
		}
	}

	/**
	 * Returns the path as a list of vertices. The first vertex in the list
	 * represents the first vertex in the path and so on.
	 * 
	 * @return The path in the form of vertices
	 */
	public List<Vertex> getPath() {
		List<Vertex> path = new ArrayList<>(nextHopPath.size());
		Vertex curr = nextHopPath.get(start);
		path.add(start);
		while (!curr.equals(start)) {
			path.add(curr);
			curr = nextHopPath.get(curr);
		}
		return path;
	}

	@Override
	public Path clone() {
		Map<Vertex, Vertex> nextHop = new HashMap<>();
		nextHop.putAll(nextHopPath);
		return new Path(nextHop);
	}

	/**
	 * Returns the first vertex in the path.
	 * 
	 * @return The first vertex in the path.
	 */
	public Vertex getFirst() {
		return start;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Vertex curr = nextHopPath.get(start);
		sb.append(start.getId()).append('\n');
		while (!curr.equals(start)) {
			sb.append(curr.getId()).append('\n');
			curr = nextHopPath.get(curr);
		}
		return sb.toString();
	}

	public Vertex next(Vertex before) {
		return nextHopPath.get(before);
	}

	/**
	 * Reverses a path between the two given indices. Can be thought of as
	 * creating an edge between reverseFrom and the the element before reverseTo
	 * and an edge between reverseTo and the element after reverseFrom, while
	 * removing edges between reverseFrom and the one after, and between the one
	 * before reverseTo and reverseTo.
	 * 
	 * Before this method is called: ReverseFrom -> A -> ... -> B -> ReverseTo
	 * 
	 * After this method is called: ReverseFrom -> B -> ... -> A -> ReverseTo
	 * 
	 * @param reverseFrom
	 *            (exclusive) Vertex to reverse from.
	 * @param reverseTo
	 *            (exclusive) Vertex to reverse to.
	 */
	public void reverseVertices(Vertex reverseFrom, Vertex reverseTo) {
		Vertex before = reverseFrom;
		Vertex curr = nextHopPath.get(before);
		Vertex next = nextHopPath.get(curr);
		while (!curr.equals(reverseTo)) {
			next = nextHopPath.get(curr);
			nextHopPath.put(curr, before);
			before = curr;
			curr = next;
		}
		nextHopPath.put(nextHopPath.get(reverseFrom), reverseTo);
		nextHopPath.put(reverseFrom, before);
	}

}
