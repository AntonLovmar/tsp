package tsp.pathfinding;

import tsp.graph.Graph;
import tsp.graph.Path;

public class NoOpStrategy implements OptimizationStrategy, PathfindingStrategy {

	@Override
	public Path optimize(Path path, Graph graph, long deadline) {
		return path;
	}

	@Override
	public Path findPath(Graph graph) {
		return new Path(graph.getVertices());
	}

}
