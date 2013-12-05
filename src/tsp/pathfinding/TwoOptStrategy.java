package tsp.pathfinding;

import java.util.Collections;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class TwoOptStrategy implements OptimizationStrategy {

	@Override
	public Path optimize(Path path, Graph graph, long deadline) {
		return twoOpt(path, graph, deadline);
	}

	private Path bestPath = null;
	private int bestLength = Integer.MAX_VALUE;

	private Path twoOpt(Path path, Graph graph, long deadline) {
		bestPath = path;
		while (System.currentTimeMillis() < deadline) {
			boolean[] visited = new boolean[graph.getNumberOfVertices()];

			search: while (!visited[graph.getNumberOfVertices() - 1]) {
				if (System.currentTimeMillis() + 50 > deadline)
					return bestPath;

				for (int rootIndex = 0; rootIndex < graph.getNumberOfVertices(); rootIndex++) {
					if (visited[rootIndex])
						continue;
					for (Vertex neighbour : graph.getNeighbourList(path.getVertex((rootIndex)))) {
						int neighbourIndex = path.indexOf(neighbour);
						if (visited[neighbourIndex])
							continue;

						if (swapGivesLessDistanceBetweenIndex(graph, path, rootIndex + 1, neighbourIndex)) {
							path.reverseBetweenIndices(rootIndex + 1, neighbourIndex);
							continue search;
						}
					}
					visited[rootIndex] = true;
				}
			}
			int length = graph.totalLength(path.getPath());
			if (length < bestLength) {
				if (System.currentTimeMillis() > deadline)
					return path;
				bestLength = length;
				bestPath = path.clone();
			}
			Collections.shuffle(path.getPath());
		}

		return bestPath;
	}

	private boolean swapGivesLessDistanceBetweenIndex(Graph graph, Path path, int i, int k) {
		return swapGivesLessDistanceWithVertices(graph, path, path.getVertex(i - 1), path.getVertex(i),
				path.getVertex(k), path.getVertex(k + 1));
	}

	private boolean swapGivesLessDistanceWithVertices(Graph graph, Path path, Vertex i, Vertex afterI, Vertex k,
			Vertex afterK) {
		return (graph.distanceBetween(afterK, afterI) + graph.distanceBetween(i, k)) < (graph
				.distanceBetween(afterI, i) + graph.distanceBetween(k, afterK));
	}
}
