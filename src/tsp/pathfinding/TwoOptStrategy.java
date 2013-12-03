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
			int bestDistance = graph.totalLength(path.getPath());
			for (int i = 0; i < graph.getNumberOfVertices() - 1; i++) {
				for (int k = i + 1; k < graph.getNumberOfVertices(); k++) {
					if (System.currentTimeMillis() > deadline) {
						return path;
					}
					path.reverseBetweenIndices(i, k);
					int newDistance = graph.totalLength(path.getPath());
					if (newDistance <= bestDistance) {
						bestDistance = newDistance;
					} else {
						path.reverseBetweenIndices(i, k);
					}
				}
			}
		}
		return path;
	}

}
