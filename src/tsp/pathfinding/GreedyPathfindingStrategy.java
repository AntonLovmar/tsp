package tsp.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
		Map<Vertex, List<Vertex>> greedyPath = preComputeGreedyPath(graph);
		return greedyStartPath(graph, greedyPath, deadline);
	}

	private Map<Vertex, List<Vertex>> preComputeGreedyPath(Graph graph) {
		int numberOfVertices = graph.getNumberOfVertices();
		Map<Vertex, List<Vertex>> greedyPath = new HashMap<Vertex, List<Vertex>>(numberOfVertices);

		for (Vertex curr : graph.getVertices()) {
			List<Vertex> neighbours = new ArrayList<Vertex>(numberOfVertices);
			for (Vertex neighbour : graph.getVertices()) {
				if (curr.equals(neighbour))
					continue;
				neighbours.add(neighbour);
			}
			// Closest neighbor is first in list.
			Collections.sort(neighbours, new NeighborComparator(curr));
			greedyPath.put(curr, neighbours);
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

	private Path greedyStartPath(Graph graph, Map<Vertex, List<Vertex>> greedyPath, long deadline) {
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
				for (Vertex neighbour : greedyPath.get(currPathVertex)) {
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
