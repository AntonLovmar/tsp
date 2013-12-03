package tsp.pathfinding;

import tsp.graph.Graph;
import tsp.graph.Path;

public class TwoOptStrategy implements OptimizationStrategy {

	@Override
	public Path optimize(Path path, Graph graph, long deadline) {
		return twoOpt(path, graph, deadline);
	}

	private Path twoOpt(Path path, Graph graph, long deadline) {
		while (System.currentTimeMillis() < deadline) {
			for (int i = 0; i < graph.getNumberOfVertices() - 1; i++) {
				for (int k = i + 1; k < graph.getNumberOfVertices(); k++) {
					if (System.currentTimeMillis() > deadline) {
						return path;
					}
					if (swapGivesLessDistance(graph, path, i, k)) {
						path.reverseBetweenIndices(i, k);
					}
				}
			}
		}
		return path;
	}

	private boolean swapGivesLessDistance(Graph graph, Path path, int i, int k) {
		return (graph.distanceBetween(path.getVertex(i - 1), path.getVertex(k)) + graph.distanceBetween(
				path.getVertex(k + 1), path.getVertex(i))) < (graph.distanceBetween(path.getVertex(i - 1),
				path.getVertex(i)) + graph.distanceBetween(path.getVertex(k), path.getVertex(k + 1)));
	}
}
