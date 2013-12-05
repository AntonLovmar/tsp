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
		boolean[] visited = new boolean[graph.getNumberOfVertices()];
		while (!visited[graph.getNumberOfVertices() - 1]) {
			if (System.currentTimeMillis() + 100 > deadline)
				return path;
			for (int rootIndex = 1; rootIndex < graph.getNumberOfVertices(); rootIndex++) {
				if (visited[rootIndex])
					continue;
				boolean gaveBetter = false;
				for (Vertex neighbour : graph.getNeighbourList(path.getVertex((rootIndex - 1)))) {
					int neighbourIndex = path.indexOf(neighbour);
					if (visited[neighbourIndex])
						continue;

					if (swapGivesLessDistanceWithIndex(graph, path, rootIndex, neighbourIndex)) {
						path.reverseBetweenIndices(rootIndex, neighbourIndex);
						gaveBetter = true;
					}
				}
				if (!gaveBetter) {
					visited[rootIndex] = true;
				}
			}
		}

		return path;
	}

	private boolean swapGivesLessDistanceWithIndex(Graph graph, Path path, int i, int k) {
		return swapGivesLessDistanceWithVertices(graph, path, path.getVertex(i - 1), path.getVertex(i),
				path.getVertex(k), path.getVertex(k + 1));
	}

	private boolean swapGivesLessDistanceWithVertices(Graph graph, Path path, Vertex beforeI, Vertex i, Vertex k,
			Vertex afterK) {
		return (graph.distanceBetween(beforeI, k) + graph.distanceBetween(i, afterK)) < (graph.distanceBetween(beforeI,
				i) + graph.distanceBetween(k, afterK));
	}
}
