package tsp.graph;

import java.util.ArrayList;
import java.util.List;

public class PathImpl implements Path {

	private List<Vertex> path;

	public PathImpl() {
		path = new ArrayList<Vertex>();
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
		path.remove(firstIndex);
		path.add(firstIndex, second);
		path.remove(secondIndex);
		path.add(secondIndex, first);
	}

	@Override
	public Vertex getVertex(int index) {
		return path.get(index);
	}

	@Override
	public Vertex getFirst() {
		return path.get(0);
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
}
