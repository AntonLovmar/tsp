package tsp.pathfinding;

import tsp.graph.Graph;
import tsp.graph.Path;

public class NoOptStrategy implements OptimizationStrategy {

	@Override
	public Path optimize(Path path, Graph graph, long deadline) {
		return path;
	}

}
