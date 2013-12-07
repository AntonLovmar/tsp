package tsp;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.pathfinding.BestNNPathfindingStrategy;
import tsp.pathfinding.OptimizationStrategy;
import tsp.pathfinding.PathfindingStrategy;
import tsp.pathfinding.TwoOptStrategy;
import tsp.reader.GraphReader;

public class Solver {

	public static void main(String[] args) {
		long deadline = System.currentTimeMillis() + 1400;
		Solver solver = new Solver();
		GraphReader reader = new GraphReader();
		Graph graph = reader.readGraph();
		Path path = solver.solveWithStrategy(new BestNNPathfindingStrategy(), new TwoOptStrategy(), graph,
				deadline);
		System.out.println(path);
		// System.out.println(graph.totalLength(path));
	}

	public Path solveWithStrategy(PathfindingStrategy pathFinder, OptimizationStrategy optimizer, Graph graph,
			long deadline) {
		return optimizer.optimize(pathFinder.findPath(graph), graph, deadline);
	}

}
