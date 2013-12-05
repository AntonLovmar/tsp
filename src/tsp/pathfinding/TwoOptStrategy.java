package tsp.pathfinding;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class TwoOptStrategy implements OptimizationStrategy {

	@Override
	public Path optimize(Path path, Graph graph, long deadline) {
		return twoOpt(path, graph, deadline);
	}

	private Path twoOpt(Path path, Graph graph, long deadline) {
		while (System.currentTimeMillis() < deadline) {
			boolean[] visited = new boolean[graph.getNumberOfVertices()];

			search: while (!visited[graph.getNumberOfVertices() - 1] && System.currentTimeMillis() < deadline) {
				for (int rootIndex = 0; rootIndex < graph.getNumberOfVertices(); rootIndex++) {
					if (visited[rootIndex])
						continue;
					for (Vertex neighbour : graph.getNeighbourList(path.getVertex((rootIndex)))) {
						int neighbourIndex = path.indexOf(neighbour);
						if (visited[neighbourIndex])
							continue;

						if (swapGivesLessDistanceBetweenIndex(graph, path, rootIndex, neighbourIndex)) {
							path.reverseBetweenIndices(rootIndex + 1, neighbourIndex);
							continue search;
						}
					}
					visited[rootIndex] = true;
				}
			}
		}

		return path;
	}

	private boolean swapGivesLessDistanceBetweenIndex(Graph graph, Path path, int i, int k) {
		return swapGivesLessDistanceWithVertices(graph, path, path.getVertex(i), path.getVertex(i + 1),
				path.getVertex(k), path.getVertex(k + 1));
	}

	private boolean swapGivesLessDistanceWithVertices(Graph graph, Path path, Vertex i, Vertex afterI, Vertex k,
			Vertex afterK) {
		return (graph.distanceBetween(afterK, afterI) + graph.distanceBetween(i, k)) < (graph
				.distanceBetween(afterI, i) + graph.distanceBetween(k, afterK));
	}
}
