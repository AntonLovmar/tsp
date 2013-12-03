package tsp.graph;

import java.util.ArrayList;
import java.util.List;

public class PathImpl implements Path {

	private final List<Vertex> path;

	public PathImpl(int length) {
		path = new ArrayList<>(length);
	}

	@Override
	public List<Vertex> getPath() {
		return path;
	}

	@Override
	public void addToPath(Vertex vertex) {
		path.add(vertex);
	}

	@Override
	public void replaceVertex(int index, Vertex newVertex) {
		path.remove(index);
		path.add(index, newVertex);
	}

	@Override
	public void swapVerticesAt(int firstIndex, int secondIndex) {
		Vertex first = path.get(firstIndex);
		Vertex second = path.get(secondIndex);
		path.set(secondIndex, first);
		path.set(firstIndex, second);
	}

	@Override
	public Vertex getVertex(int index) {
		if (index >= path.size())
			return null;
		return path.get(index);
	}

	@Override
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

	@Override
	public void reverseBetweenIndices(int reverseFrom, int reverseTo) {
		while (reverseTo > reverseFrom) {
			swapVerticesAt(reverseFrom, reverseTo);
			reverseFrom++;
			reverseTo--;
		}
	}
}
