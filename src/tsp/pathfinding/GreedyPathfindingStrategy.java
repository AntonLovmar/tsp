package tsp.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class GreedyPathfindingStrategy implements PathfindingStrategy {

	private Graph graph;

	@Override
	public Path findPath(Graph graph) {
		this.graph = graph;
		long deadline = System.currentTimeMillis() + 800;
		List<List<Vertex>> greedyPath = preComputeGreedyPath(graph);
		return greedyStartPath(graph, greedyPath, deadline);
	}

	private List<List<Vertex>> preComputeGreedyPath(Graph graph) {
		int numberOfVertices = graph.getNumberOfVertices();
		List<List<Vertex>> greedyPath = new ArrayList<List<Vertex>>(numberOfVertices);

		for (int i = 0; i < numberOfVertices; i++) {
			Vertex curr = graph.getVertex(i);
			List<Vertex> closestVertices = new ArrayList<Vertex>(numberOfVertices);
			for (int j = 0; j < numberOfVertices; j++) {
				if (i == j)
					continue;
				closestVertices.add(graph.getVertex(j));
			}
			Collections.sort(closestVertices, new NeighborComparator(curr));
			greedyPath.add(closestVertices);
		}
		return greedyPath;
	}

	private class NeighborComparator implements Comparator<Vertex> {
		Vertex root;

		public NeighborComparator(Vertex root) {
			this.root = root;
		}

		@Override
		public int compare(Vertex arg0, Vertex arg1) {
			return graph.distanceBetween(root, arg0) - graph.distanceBetween(root, arg1);
		}

	}

	private Path greedyStartPath(Graph graph, List<List<Vertex>> greedyPath, long deadline) {
		int numberOfVertices = graph.getNumberOfVertices();
		Path path = null;
		Path bestPath = new Path(graph.getVertices());
		int bestPathLength = graph.totalLength(bestPath.getPath());

		for (int i = 0; i < graph.getNumberOfVertices() && System.currentTimeMillis() < deadline; i++) {
			path = new Path(numberOfVertices);
			Set<Vertex> used = new HashSet<>();
			Vertex currPathVertex = graph.getVertex(i);
			used.add(currPathVertex);
			path.addToPath(currPathVertex);

			for (int j = 1; j < numberOfVertices; j++) {
				currPathVertex = path.getVertex(j - 1);
				for (Vertex neighbour : greedyPath.get(j)) {
					if (!used.contains(neighbour)) {
						path.addToPath(neighbour);
						used.add(neighbour);
						break;
					}
				}
			}
			int pathLength = graph.totalLength(path.getPath());
			if (pathLength < bestPathLength) {
				bestPath = path;
				bestPathLength = pathLength;
			}
		}
		return bestPath;
	}
}
