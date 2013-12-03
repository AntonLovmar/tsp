package tsp.graph;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private final List<Vertex> path;

	public Path(int length) {
		path = new ArrayList<>(length);
	}

	public List<Vertex> getPath() {
		return path;
	}

	public void addToPath(Vertex vertex) {
		path.add(vertex);
	}

	public void replaceVertex(int index, Vertex newVertex) {
		path.remove(index);
		path.add(index, newVertex);
	}

	public void swapVerticesAt(int firstIndex, int secondIndex) {
		Vertex first = path.get(firstIndex);
		Vertex second = path.get(secondIndex);
		path.set(secondIndex, first);
		path.set(firstIndex, second);
	}

	public Vertex getVertex(int index) {
		if (index >= path.size() || index < 0)
			return null;
		return path.get(index);
	}

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

	public void reverseBetweenIndices(int reverseFrom, int reverseTo) {
		while (reverseTo > reverseFrom) {
			swapVerticesAt(reverseFrom, reverseTo);
			reverseFrom++;
			reverseTo--;
		}
	}
}
